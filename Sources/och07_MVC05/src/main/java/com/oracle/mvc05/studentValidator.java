package com.oracle.mvc05;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.oracle.mvc05.dto.Student;

public class studentValidator implements Validator {

	// supports : 검증할 객체의 클래스 타입 정보
	@Override
	public boolean supports(Class<?> student) {
		// TODO Auto-generated method stub
		return Student.class.isAssignableFrom(student);  // Student 클래스를 검증
	}

	// Validate 함수
	// Validator를 상속받아서 만들어진 메소드이므로, model이 아닌 Errors을 통해서만 가지고 올 수 있다 
	@Override
	public void validate(Object target, Errors errors) {  
		// TODO Auto-generated method stub
		System.out.println("studentValidator validate() start");
		
		Student student = (Student) target;
		
		String studentName = student.getName();
		if(studentName == null || studentName.trim().isEmpty()) {
			System.out.println("studentName is null or empty");
			System.out.println("validate -> 회원 이름을 입력하세요");
			errors.rejectValue("name", "회원 이름 또는 공백 null 오류");  // 값 세팅, name = Errors객체의 에러 이름 / 콤마 뒤가  Code값이다 
		}
		
		String studentId = student.getStrId();
		if(studentId == null || studentId.trim().isEmpty()) {
			System.out.println("studentId is null or empty");
			System.out.println("validate -> strId을 입력하세요");
			errors.rejectValue("strId", "strId 또는 공백 null 오류");  // 값 세팅, strId = Errors객체의 에러 이름
		}

	}

}
