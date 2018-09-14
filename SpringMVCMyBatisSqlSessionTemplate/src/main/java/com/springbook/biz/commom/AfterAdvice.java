package com.springbook.biz.commom;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component("AfterFinal")
public class AfterAdvice {
	@After("PointCommon.allPointcut()")
	public void finallyLog() {
		System.out.println("[finally사후 처리] 비즈니스 로직 수행 후 무조건 동작");
	}
}
