package com.springbook.biz.commom;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component("afterThrowing")
public class AfterThrowingAdvice {
	@AfterThrowing("PointCommon.deletePointcut()") 
	public void afterException() {
		System.out.println("[afterExcept사후 처리] 비즈니스 로직 수행 중 예외 발생");
	}
}
