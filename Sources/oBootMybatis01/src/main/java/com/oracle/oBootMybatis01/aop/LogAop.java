package com.oracle.oBootMybatis01.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component 		// @Aspect은 component 등록이 안되어 있어서 걸어줌 
public class LogAop {
	
	// @Pointcut(핵심관심사 지정) = aop2.buz 패키지 안에 있는 모든 메소드 
	@Pointcut("within(com.oracle.oBootMybatis01.dao.EmpDao*)")
	private void pointcutMethod() {
	}
	
	// dao에 있는 모든 메소드에 해당 
	@Around("pointcutMethod()")
	public Object loggerAop(ProceedingJoinPoint joinpoint) throws Throwable{
		String signatureStr = joinpoint.getSignature().toShortString();
		System.out.println(signatureStr + "is Start...");
		
		long st = System.currentTimeMillis();
		
		try {
			Object obj = joinpoint.proceed();
			return obj;
		} finally {
			long et = System.currentTimeMillis();
			System.out.println(signatureStr + " is finished.");
			System.out.println(signatureStr + " 경과시간 : " + (et - st));
		} 
	}
	


}
