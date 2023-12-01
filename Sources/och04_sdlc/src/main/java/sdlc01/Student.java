package sdlc01;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Student implements InitializingBean, DisposableBean {
	private String name;
	private int age;

	// 소멸자 소멸 전 수행
	public void destroy() throws Exception {
		System.out.println("Studnet의  destory() --> 소멸자가 소멸되기 전 수행 ..");
	}

	// 생성자 생성 이후 수행
	public void afterPropertiesSet() throws Exception {
		System.out.println("Studnet afterPropertiesSet() --> 생성자 생성 이후 수행 ..");
	}
	
	// 생성자
	public Student(String name, int age) {
		this.name = name;
		this.age = age;
		System.out.println("Student 생성자 ..");
	}
	
	
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	
	

}
