package aop1;

import org.aspectj.lang.ProceedingJoinPoint;

public class LogAop {
	//                      핵심관심사에 연결하는 객체
	public Object loggerAop(ProceedingJoinPoint joinPoint) throws Throwable {
		// 핵심 관심자의 method
		String signatureStr = joinPoint.getSignature().toShortString();
		System.out.println(signatureStr + " is start...");
		
		// 성능 측정(시작시간)
		long startTime = System.currentTimeMillis();  // 1000분의 1초 단위
		
		Object obj;
		
		try {
			// 핵심관심사 Method 수행
			obj = joinPoint.proceed();  // getStudentInfo(), getWorkerInfo() 실헹시킴
			return obj;
		} finally {
			// 핵심관심사의 종료 시간
			long endTime = System.currentTimeMillis();
			System.out.println(signatureStr + " is finished.");
			System.out.println(signatureStr + " 성능 경과시간 " + (endTime - startTime));
		}
	}
}
