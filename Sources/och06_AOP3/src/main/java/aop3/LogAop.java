package aop3;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect  // aop 쓰겠다는 의미
public class LogAop {
	
	// aop3.buz 패키지 안에 있는 모든 메소드에 적용
	@Pointcut("within(aop3.buz.*)")
	private void pointcutMethod() {
	}
	
	@Around("pointcutMethod()")    // 주석풀면 아래를 주석처리, 아래보다 이렇게 많이 사용함
//	@Around("within(aop3.buz.*)")  // 이렇게 하려면 위에를 주석처리
	public Object loggerAop(ProceedingJoinPoint joinPoint) throws Throwable {
		// 핵심 관심자의 method
		String signatureStr = joinPoint.getSignature().toShortString();
		System.out.println(signatureStr + " is start...");
		
		// 성능 측정(시작시간)
		long startTime = System.currentTimeMillis();
		
		Object obj;
		
		try {
			// 핵심관심사 Method 수행
			obj = joinPoint.proceed();  // getStudentInfo, getWorkerInfo 실헹시킴
			return obj;
		} finally {
			// 핵심관심사의 종료 시간
			long endTime = System.currentTimeMillis();
			System.out.println(signatureStr + " is finished.");
			System.out.println(signatureStr + " 성능 경과시간 " + (endTime - startTime));
		}
	}

	@Before("within(aop3.buz.*)")
	public void beforeAdvice() {
		System.out.println("beforeAdvice()");
	}
	
	@After("within(aop3.buz.*)")
	public void afterAdvice() {
		System.out.println("afterAdvice()");
	}
	
	
	
}
