package com.cloud.notification.service.impl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.cloud.notification.model.Sms;
import com.cloud.notification.model.VerificationCode;
import com.cloud.notification.service.SmsService;
import com.cloud.notification.service.VerificationCodeService;
import com.cloud.util.Util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {

	/**
	 * 短信验证码有效期（单位：分钟）
	 */
	@Value("${sms.expire-minute:15}")
	private Integer expireMinute;
	//@Autowired
	//private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	private SmsService smsService;

	@Transactional
	@Override
	public VerificationCode generateCode(String phone) {
		String uuid = UUID.randomUUID().toString();
		String code = Util.randomCode(6);

		Map<String, String> map = new HashMap<>(2);
		map.put("code", code);
		map.put("phone", phone);

	//	stringRedisTemplate.opsForValue().set(smsRedisKey(uuid), JSONObject.toJSONString(map), expireMinute,
	//			TimeUnit.MINUTES);
		System.out.println("我去掉了短信1");
		log.info("缓存验证码：{}", map);

		saveSmsAndSendCode(phone, code);

		VerificationCode verificationCode = new VerificationCode();
		verificationCode.setKey(uuid);
		return verificationCode;
	}

	/**
	 * 保存短信记录，并发送短信
	 * 
	 * @param phone
	 * @param code
	 */
	private void saveSmsAndSendCode(String phone, String code) {
		checkTodaySendCount(phone);

		Sms sms = new Sms();
		sms.setPhone(phone);

		Map<String, String> params = new HashMap<>();
		params.put("code", code);
		smsService.save(sms, params);

		smsService.sendSmsMsg(sms);

		// 当天发送验证码次数+1
		String countKey = countKey(phone);
		//stringRedisTemplate.opsForValue().increment(countKey, 1L);
		System.out.println("我去掉了短信2");
		//stringRedisTemplate.expire(countKey, 1, TimeUnit.DAYS);
		System.out.println("我去掉了短信3");
	}

	@Value("${sms.day-count:30}")
	private Integer dayCount;

	/**
	 * 获取当天发送验证码次数
	 * 
	 * @param phone
	 * @return
	 */
	private void checkTodaySendCount(String phone) {
		//String value = stringRedisTemplate.opsForValue().get(countKey(phone));
		String value = "29";
		System.out.println("我去掉短信4");
		if (value != null) {
			Integer count = Integer.parseInt(value);
			if (count > dayCount) {
				throw new IllegalArgumentException("已超过当天最大次数");
			}
		}

	}

	private String countKey(String phone) {
		return "sms:count:" + LocalDate.now().toString() + ":" + phone;
	}

	/**
	 * redis中key加上前缀
	 *
	 * @param str
	 * @return
	 */
	private String smsRedisKey(String str) {
		return "sms:" + str;
	}

	@Override
	public String matcheCodeAndGetPhone(String key, String code, Boolean delete, Integer second) {
		key = smsRedisKey(key);

		//String value = stringRedisTemplate.opsForValue().get(key);
		String value = "{'code':'123456'}";
		if (value != null) {
			JSONObject json = JSONObject.parseObject(value);
			if (code != null && code.equals(json.getString("code"))) {
				log.info("验证码校验成功：{}", value);
				System.out.println("我去掉了短信7");

				if (delete == null || delete) {
					System.out.println("我去掉了短信8");

					//stringRedisTemplate.delete(key);
				}

				if (delete == Boolean.FALSE && second != null && second > 0) {
					System.out.println("我去掉了短信9");
					//stringRedisTemplate.expire(key, second, TimeUnit.SECONDS);
				}

				return json.getString("phone");
			}

		}

		return null;
	}
}
