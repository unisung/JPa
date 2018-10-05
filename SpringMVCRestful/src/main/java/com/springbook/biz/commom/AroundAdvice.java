package com.springbook.biz.commom;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component("around")
public class AroundAdvice {
	@Around("PointCommon.allPointcut()")
	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable{
		String method = pjp.getSignature().getName();
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		//사전
		System.out.println("[Around-BEFORE]:비즈니스 메소드 수행 전 처리할 내용:");
		Object returnObj = pjp.proceed();
		
		//사후
		System.out.println("[Around-AFTER]:비즈니스 메소드 수행 후 처리할 내용:");
		stopWatch.stop();
		System.out.println(method+"() 메소드 수행에 걸린 시간 :"+stopWatch.getLastTaskTimeMillis()+"(ms)초");

		return returnObj;
	}

}
