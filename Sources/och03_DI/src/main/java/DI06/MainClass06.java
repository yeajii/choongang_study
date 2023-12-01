package DI06;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass06 {

	public static void main(String[] args) {
		String configLocation1 = "classpath:applicationCTX610.xml";
		String configLocation2 = "classpath:applicationCTX611.xml";
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation1, configLocation2);
		Student student1 = ctx.getBean("student1", Student.class);
		System.out.println(student1.getName());    // 선덕여왕
		System.out.println(student1.getHobbys());  // [승마, 정지]
		
		StudentInfo studentInfo = ctx.getBean("studentInfo1", StudentInfo.class);
		Student student2 = studentInfo.getStudent(); 
		System.out.println(student2.getName());    // 선덕여왕
		System.out.println(student2.getHobbys());  // [승마, 정지]
		
		// 1과 2의 주소값은 같다
		if(student1.equals(student2)) {
			System.out.println("student1 == student2");
		}
		
		// xml의 (scope="singleton") -> 인스턴스 명은 달라도 같다고 본다 : 디폴트 
		// xml의 (scope="prototype") -> 인스턴스 주소가 달라짐
		Student student3 = ctx.getBean("student3", Student.class);
		Student student4 = ctx.getBean("student3", Student.class);
		System.out.println(student3.getName());
		System.out.println(student4.getName());
		
		if(student3.equals(student4)) {
			System.out.println("student3 == student4");
		}else {
			System.out.println("student3 != student4");
		}
	}
}
