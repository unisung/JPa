package com.springbook.biz.commom;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/* POJO 클래스 - 일반클래스 */
@Component("log")
@Aspect
public class LogAdvice {
	//advice
   @Pointcut("execution(* com.springbook.biz..*Impl.*(..))")
   public void allPointCut() {}
   
   @Before("allPointCut()")
   public void printLog() {
	   System.out.println("[allPointCut-get공통로그]비즈니스 로직 수행 전 동작");
   }
}
