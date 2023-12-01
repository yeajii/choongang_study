package sdlc02;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass02 {

	public static void main(String[] args) {
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX02.xml");
		
		// singleton(디폴트) : 같은 빈은 인스턴스가 같다 , prototype : 같은 빈의 인스턴스를 다르게 뽑으려면
		Student student1 = ctx.getBean("student", Student.class);
		System.out.println("student1 이름: " + student1.getName());  // applicationCTX02.xml에 있는 값이 출력
		System.out.println("student1 나이: " + student1.getAge());
		
		System.out.println("------------------------------");
		Student student2 = ctx.getBean("student", Student.class);
		student2.setName("강유");
		student2.setAge(55);
		
		System.out.println("student2 이름: " + student2.getName());
		System.out.println("student2 나이: " + student2.getAge());
		System.out.println("------------------------------");
		System.out.println("student1 이름: " + student1.getName());  
		System.out.println("student1 나이: " + student1.getAge());
		
		if(student1.equals(student2)) {
			System.out.println("student1 == student2");
		}else {
			System.out.println("student1 != student2");
		}
		ctx.close();
	}

}
