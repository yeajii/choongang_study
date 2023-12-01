package DI07;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// annotation
@Configuration
public class ApplicationConfig {
	
	// bean으로 설정
	@Bean
	public Student student1() {
		ArrayList<String> hobbys = new ArrayList<String>();
		hobbys.add("팝뮤직");
		hobbys.add("피아노");
		
		Student student = new Student("안예은", 24, hobbys);
		student.setHeight(160);
		student.setWeight(50);
		
		return student;
	}
	
	@Bean
	public Student student2() {
		ArrayList<String> hobbys = new ArrayList<String>();
		hobbys.add("뮤지컬");
		hobbys.add("음악감상");
		
		Student student = new Student("김준수", 35, hobbys);
		student.setHeight(178);
		student.setWeight(76);
		
		return student;
	}
}
