package aop2;

import org.aspectj.lang.ProceedingJoinPoint;

public class LogAop {
	//                      핵심관심사에 연결하는 객체(around 방식)
	public Object loggerAop(ProceedingJoinPoint joinPoint) throws Throwable {
		// 핵심 관심자의 method
		String signatureStr = joinPoint.getSignature().toShortString();
		System.out.println(signatureStr + " is start...");
		
		// 성능 측정(시작시간)
		long startTime = System.currentTimeMillis();
		
		Object obj;
		
		try {
			// 핵심관심사 Method 수행
			obj = joinPoint.proceed();  // getStudentInfo(), getWorkerInfo() 실헹시킴
			return obj;
		} finally {
			// 핵심관심사의 종료 시간
			long endTime = System.currentTimeMillis();
			System.out.println(signatureStr + "is finished.");
			System.out.println(signatureStr + " 성능 경과시간 " + (endTime - startTime));
		}
	}
	
	// 핵심관심사와 엮어서 돌아갈 메소드 = advice
	public void beforeAdvice() {
		System.out.println("beforeAdvice()");
	}
	
	public void afterReturningAdvice() {
		System.out.println("afterReturningAdvice()");
	}
	
	public void afterThrowingAdvice() {
		System.out.println("afterThrowingAdvice()");
	}
	
	public void afterAdvice() {
		System.out.println("afterAdvice()");
	}
	
}


