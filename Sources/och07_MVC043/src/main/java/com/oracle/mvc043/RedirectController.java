package com.oracle.mvc043;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller   // 이걸 해줘야 스프링이 컨트롤러로 인정됨, 컨트롤러는 여러 개 있어도 됨, 컨트롤러가 이름이 같으면 출동되므로 이름 다르게 하기
public class RedirectController {
	
	private static final Logger logger = LoggerFactory.getLogger(RedirectController.class);
	
	@RequestMapping("studentConfirm")
	public String studentRedirect(HttpServletRequest httpServletRequest, Model model) {
		logger.info("studentConfirm start...");
		
		String id = httpServletRequest.getParameter("id");
		logger.info("studentConfirm id-> {}", id);
		
		String pw = "1234";
		
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		
		// 성공이라고 가정 -> 같은 Controller 내의  method로 이동
		if(id.equals("abc")) {
			return "redirect:studentSuccess";  // view 이름 대신 "redirect" 사용하면 해당 주소로 리다이렉트 시켜준다 
		}
		// 아니면 실패 
		return "redirect:studentError";
	}
	
	@RequestMapping("studentSuccess")
	public String studentSuccess(HttpServletRequest request, Model model) {
		logger.info("studentSuccess start...");
		
		String id = request.getParameter("id");
		String password = request.getParameter("pw");
		logger.info("studentSuccess id-> {}", id);
		logger.info("studentSuccess passwd-> {}", password);
		
		model.addAttribute("id", id);
		model.addAttribute("password", password);
		
		return "student/studentSuccess";
	}
	
	   @RequestMapping("studentError")
	   public String studentError(HttpServletRequest request, Model model) {
	      logger.info("studentError Start...");
	      String id = request.getParameter("id");
	      model.addAttribute("id", id);
	      
	      return "student/studentError";
	   }
	
	


}
