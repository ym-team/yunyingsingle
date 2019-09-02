package com.cloud.util;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class CacheUtils {

    private static Map<String, Object> localCache = new ConcurrentHashMap<>();

    private static DelayQueue<DelayedItem> queue = new DelayQueue<>();

    static {
        Thread thread = new Thread(CacheUtils::expireTimeoutKey);
        thread.setDaemon(true);
        thread.start();
    }

    public static void save(String key, Object value) {
        save(key, value, null);
    }

    public static void save(String key, Object value, Long timeOutMills) {
        if (key != null && value != null) {
            Object old = localCache.put(key, value);
            DelayedItem item;
            if (old != null) {
                item = new DelayedItem(key, 1L);
                queue.remove(item);
            }
            if (timeOutMills != null && timeOutMills > 0) {
                item = new DelayedItem(key, timeOutMills);
                queue.put(item);
            }
        }
    }

    public static boolean saveNX(String key, Object value) {
        return saveNX(key, value, null);
    }

    public static boolean saveNX(String key, Object value, Long timeOutMills) {
        boolean flag = false;
        if (key != null && value != null) {
            Object v = localCache.get(key);
            if (v == null) {
                flag = true;
                localCache.put(key, value);
            }
            if (flag && timeOutMills != null && timeOutMills > 0) {
                DelayedItem item = new DelayedItem(key, timeOutMills);
                queue.put(item);
            }
        }
        return flag;
    }

    public static Object load(String key) {
        Object value = null;
        if (key != null) {
            value = localCache.get(key);
        }
        return value;
    }

    public static Object delete(String key) {
        Object value = null;
        if (key != null) {
            value = localCache.remove(key);
            if (value != null) {
                queue.remove(new DelayedItem(key, 1L));
            }
        }
        return value;
    }

    private static void expireTimeoutKey() {
        while (true) {
            try {
                DelayedItem item = queue.poll();
                if (item != null) {
                    localCache.remove(item.getKey());
                }
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }
    }

    public static void main(String[] args) throws Exception {
        CacheUtils.saveNX("1", "1");
        CacheUtils.saveNX("2", "2", 3000L);
        CacheUtils.saveNX("3", "3", 5000L);

        CacheUtils.save("4", "4");
        CacheUtils.save("5", "5", 3000L);
        CacheUtils.save("6", "6", 5000L);

        while (true) {
            System.out.println(CacheUtils.load("1"));
            System.out.println(CacheUtils.load("2"));
            System.out.println(CacheUtils.load("3"));
            System.out.println(CacheUtils.load("4"));
            System.out.println(CacheUtils.load("5"));
            System.out.println(CacheUtils.load("6"));
            Thread.sleep(1000L);
        }
    }

}

class DelayedItem implements Delayed {

    private String key;

    private long time;

    public DelayedItem() {
    }

    public DelayedItem(String key, Long timeOutMills) {
        this.key = key;
        this.time = TimeUnit.NANOSECONDS.convert(timeOutMills, TimeUnit.MILLISECONDS) + System.nanoTime();
    }

    @Override
    public int compareTo(Delayed o) {
        if (o == null) {
            return 1;
        }
        if (o == this) {
            return 0;
        }
        if (o instanceof DelayedItem) {
            DelayedItem that = (DelayedItem) o;
            if (this.time > that.time) {
                return 1;
            } else if (this.time < that.time) {
                return -1;
            }
        }
        return 0;
    }

    // 剩余的延迟时间
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(time - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    public String getKey() {
        return key;
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DelayedItem) {
            DelayedItem that = (DelayedItem) obj;
            return key.equals(that.key);
        }
        return false;
    }

}
