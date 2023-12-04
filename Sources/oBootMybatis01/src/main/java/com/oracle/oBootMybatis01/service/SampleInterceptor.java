package com.oracle.oBootMybatis01.service;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class SampleInterceptor implements HandlerInterceptor {
	
	public SampleInterceptor() {
	}
	
	// 3번 
	// modelAndView 에서 getModel()을 해주면 model에 담겨있는 걸 갖고 올 수 있다 -> id, memCnt
	@Override
	public void postHandle(HttpServletRequest request, 
						   HttpServletResponse response, 
						   Object handler, 
						   ModelAndView modelAndView) throws Exception {
		System.out.println("post handle.......................");
		
		String ID  = (String) modelAndView.getModel().get("id");
		int memCnt = (Integer) modelAndView.getModel().get("memCnt");
		System.out.println("SampleInterceptor postHandle memCnt-> " + memCnt);
		
		if(memCnt < 1) {  // 존재하지 않다 
			System.out.println("memCnt Not exists");
			request.getSession().setAttribute("ID", ID);  // 사용자가 입력한 ID를 갖고 controller의 doMemberWrite로 이동 
			// user가 존재하지 않으면 user interceptor page(회원등록) 이용
			response.sendRedirect("doMemberWrite");
		} else {  // 존재한다          
			System.out.println("memCnt exists");
			request.getSession().setAttribute("ID", ID);
			// user가 존재하면 user interceptor page(회원리스트) 이용
			response.sendRedirect("doMemberList");
		}
	}
	
	// 1번
	@Override
	public boolean preHandle(HttpServletRequest request, 
               				 HttpServletResponse response, 
               				 Object handler) throws Exception {
		System.out.println("pre handle.......................");
		
		HandlerMethod method = (HandlerMethod) handler;
		Method methodObj = method.getMethod();
		System.out.println("Bean : " + method.getBean());
		System.out.println("Method : " + methodObj);
		
		return true;
	}
	


}
