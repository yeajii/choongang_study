package DI08;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
	
	@Bean
	public Student student1() {
		ArrayList<String> hobbys = new ArrayList<String>();
		hobbys.add("수영");
		hobbys.add("물내리기");
		
		Student student = new Student("을지문덕", 55, hobbys);
		student.setHeight(170);
		student.setWeight(70);
		
		return student;
	}
	
	

}
