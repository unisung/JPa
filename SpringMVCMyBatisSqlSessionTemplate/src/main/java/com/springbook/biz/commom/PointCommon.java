package com.springbook.biz.commom;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointCommon {
	@Pointcut("execution(* com.springbook.biz..*Impl.*(..))")
	public void allPointcut() {}
	
	@Pointcut("execution(* com.springbook.biz..*Impl.delete*(..))" )
	public void deletePointcut() {}
	
	@Pointcut("execution(* com.springbook.biz..*Impl.get*(..))" )
	public void getPointcut() {}
}
