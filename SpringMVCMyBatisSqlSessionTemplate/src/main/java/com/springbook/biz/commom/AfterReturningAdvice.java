package com.springbook.biz.commom;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component("after")
public class AfterReturningAdvice {
	@AfterReturning("PointCommon.getPointcut()")
	public void afterLog() {
		System.out.println("[after 사후 처리] 비즈니스 로직 수행 후 동작");
	}
}
