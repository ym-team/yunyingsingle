/*package com.cloud.util;


*//**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 *//*

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.common.util.concurrent.RateLimiter;
import com.itmayiedu.annotation.ExtRateLimiter;

*//**
 *//*
@Aspect
@Component
public class RateLimiterAop {
	// 存放接口是否已经存在
	private static ConcurrentHashMap<String, RateLimiter> rateLimiterMap = new ConcurrentHashMap<String, RateLimiter>();

	@Pointcut("execution(public * com.itmayeidu.api.*.*(..))")
	public void rlAop() {
	}

	@Around("rlAop()")
	public Object doBefore(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
		// 使用Java反射技术获取方法上是否有@ExtRateLimiter注解类
		ExtRateLimiter extRateLimiter = signature.getMethod().getDeclaredAnnotation(ExtRateLimiter.class);
		if (extRateLimiter == null) {
			// 正常执行方法
			Object proceed = proceedingJoinPoint.proceed();
			return proceed;
		}
		// ############获取注解上的参数 配置固定速率 ###############
		// 获取配置的速率
		double value = extRateLimiter.value();
		// 获取等待令牌等待时间
		long timeOut = extRateLimiter.timeOut();
		RateLimiter rateLimiter = getRateLimiter(value, timeOut);
		// 判断令牌桶获取token 是否超时
		boolean tryAcquire = rateLimiter.tryAcquire(timeOut, TimeUnit.MILLISECONDS);
		if (!tryAcquire) {
			serviceDowng();
			return null;
		}
		// 获取到令牌,直接执行..
		Object proceed = proceedingJoinPoint.proceed();
		return proceed;

	}

	// 获取RateLimiter对象
	private RateLimiter getRateLimiter(double value, long timeOut) {
		// 获取当前URL
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		String requestURI = request.getRequestURI();
		RateLimiter rateLimiter = null;
		if (!rateLimiterMap.containsKey(requestURI)) {
			// 开启令牌通限流
			rateLimiter = RateLimiter.create(value); // 独立线程
			rateLimiterMap.put(requestURI, rateLimiter);
		} else {
			rateLimiter = rateLimiterMap.get(requestURI);
		}
		return rateLimiter;
	}

	// 服务降级
	private void serviceDowng() throws IOException {
		// 执行服务降级处理
		System.out.println("执行降级方法,亲,服务器忙！请稍后重试!");
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletResponse response = attributes.getResponse();
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		try {
			writer.println("执行降级方法,亲,服务器忙！请稍后重试!");
		} catch (Exception e) {

		} finally {
			writer.close();
		}

	}

	public static void main(String[] args) {
		// 使用Java反射技术获取方法上是否有@ExtRateLimiter注解类
		ExtRateLimiter extRateLimiter = IndexController.class.getClass().getAnnotation(ExtRateLimiter.class);
		System.out.println(extRateLimiter);
	}

}
*/