package com.oracle.mvc05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.mvc05.dto.Student;

@Controller
public class StudentController {
	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	@RequestMapping("/studentForm")
	public String studentForm() {
		logger.info("studnetForm start...");
		
		return "studentForm";
	}
	
	// BindingResult: Errors 객체의 값들을 갖고 오기 위해 사용
	@RequestMapping("/student/create")
	public String studentCreate(Student student, BindingResult result, Model model) {
		String page = "studentDonePage";
		logger.info("/student/create Start...");
		
		studentValidator validator = new studentValidator(); 
		validator.validate(student, result);  // result 값을 통해 에러가 있다면, 아래를 통해 보여지게 한다
		logger.info("result Message-> {}", result.toString());
		System.out.println("result Message getFileFrror-> " + result.getFieldErrors("name"));
		
		String name = "";
		String id = "";	
		
		if (result.hasErrors()) {
			if(result.hasFieldErrors("name")) {  // name = Errors객체의 에러 이름
				System.out.println("result.hasErrors1" + result.getFieldError("name"));
				
				FieldError fieldError1 = result.getFieldError("name");
				name = fieldError1.getCode();
				System.out.println("fieldError1.getCode name" + name);
				
				model.addAttribute("nameErr", name);
			}
			if(result.hasFieldErrors("strId")) {  // strId = Errors객체의 에러 이름
				System.out.println("result.hasErrors2" + result.getFieldError("strId"));
				
				FieldError fieldError2 = result.getFieldError("strId");
				id = fieldError2.getCode();
				System.out.println("fieldError2.getCode id" + id);
				
				model.addAttribute("idErr", id);
			}
			page = "studentForm";
			
		// 정상일 경우
		} else {
			model.addAttribute("student", student);
		}
		
		System.out.println("result Message -> End");
		logger.info("result page-> {}", page);
		logger.info("result name-> {}", name);
		
		return page;
	}
	
	
	
	
}
