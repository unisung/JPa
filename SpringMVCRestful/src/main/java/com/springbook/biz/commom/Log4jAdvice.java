package com.springbook.biz.commom;

import org.springframework.stereotype.Component;

/*@Component("logAdvice")*/
public class Log4jAdvice {
	//advice
  public void printLogging() {
	  System.out.println("[공통 로그-Log4j] 비즈니스 로직 수행 전 동작");
  }
}

