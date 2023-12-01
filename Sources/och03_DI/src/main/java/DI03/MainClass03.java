package DI03;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass03 {

	public static void main(String[] args) {
		String configLocation = "classpath:applicationCTX03.xml";
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation);
		System.out.println("AbstractApplicationContext after...");
		StudentInfo studentInfo = ctx.getBean("studentInfo", StudentInfo.class);
		System.out.println("studentInfo getBean after...");
		studentInfo.getStudentInfo();
		System.out.println("studentInfo.getStudentInfo() after...");

		// bean 호출
		Student student2 = ctx.getBean("student2", Student.class);
		System.out.println("getBean student2 after...");
		studentInfo.setStudent(student2);  
		System.out.println("studentInfo setStudnet after...");
		
		// 연개소문이 김유신으로 변경
		studentInfo.getStudentInfo();
		
		System.out.println("student2.getName()-> " + student2.getName());
		System.out.println("student2.getAge()-> " + student2.getAge());
		System.out.println(student2.getAge()+5);
		
		ctx.close();
	}

}
