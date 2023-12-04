package com.oracle.oBootMybatis01.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.oBootMybatis01.model.Dept;
import com.oracle.oBootMybatis01.model.Emp;
import com.oracle.oBootMybatis01.model.SampleVO;
import com.oracle.oBootMybatis01.service.EmpService;

import lombok.RequiredArgsConstructor;

// @Controller + @ResponseBody
@RestController
@RequiredArgsConstructor
public class EmpRestController {
	
	private final EmpService es;
	
	@RequestMapping("helloText")  //   /helloText 해도 가능 
	public String helloText() {
		System.out.println("EmpRestController Start...");
		String hello = "안녕";
		// String이면 --> StringConverter 
		return hello;   // 10:11
	}
	
	// http://jsonviewer.stack.hu
	@RequestMapping("/sample/sendVO2")  //    
	public SampleVO sendVO2(int deptno) {
		System.out.println("@RestController deptno-> " + deptno);  // 123
		SampleVO vo = new SampleVO();
		vo.setFirstName("길동");
		vo.setLastName("홍");;
		vo.setMno(deptno);
		// 객체면 --> JsonConverter 
		return vo;  // 10:22 @RestController이므로 views 파일명 대신 String이든 객체든 상관없다 
	}
	
	
	@RequestMapping("sendVO3")
	public List<Dept> sendVO3(){
		System.out.println("@RestController sendVO3 Start...");
		List<Dept> deptList = es.deptSelect();
		
		return deptList;  // [] --> Json에서 배열 의미 
	}
	
	
	@RequestMapping("/empnoDelete")
	public String empnoDelete(Emp emp) {  // 파라미터 2개 이상 받아야 해서 dto , 실제로는 하나만 사용됨 
		System.out.println("@RestController empnoDelete Start..");
		System.out.println("@RestController empnoDelete emp.getEname()-> " + emp.getEname());
		
		int    delStatus    = es.deleteEmp(emp.getEmpno());
		String delStatusStr = Integer.toString(delStatus);
		
		return delStatusStr;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
