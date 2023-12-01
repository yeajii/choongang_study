package sdlc01;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class OtherStudent {
	private String name;
	private int age;
	
	// 인터페이스를 상속받지 않고 annotation으로 설정 
	// 생성자 생성 이후
	@PostConstruct 
	public void initMethod() {
		System.out.println("OtherStudent의 initMethod() 생성자 생성 이후 수행 ..");
	}
	
	// 소멸자 소멸 전
	@PreDestroy
	public void destroyMethod() {
		System.out.println("OtherStudent의 destroyMethod() 소멸자가 소멸되기 전 수행 ..");
	}
	
	// 생성자
	public OtherStudent(String name, int age) {
		this.name = name;
		this.age = age;
		System.out.println("OtherStudent 생성자...");
	}

	
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	
	

}
