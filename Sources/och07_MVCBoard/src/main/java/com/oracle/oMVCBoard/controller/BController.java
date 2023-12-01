package com.oracle.oMVCBoard.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oracle.oMVCBoard.command.BCommand;
import com.oracle.oMVCBoard.command.BContentCommand;
import com.oracle.oMVCBoard.command.BDeleteCommand;
import com.oracle.oMVCBoard.command.BListCommand;
import com.oracle.oMVCBoard.command.BModifyCommand;
import com.oracle.oMVCBoard.command.BReplyCommand;
import com.oracle.oMVCBoard.command.BReplyViewCommand;
import com.oracle.oMVCBoard.command.BWriteCommand;

@Controller
public class BController {
	private static final Logger logger = LoggerFactory.getLogger(BController.class);
	
	// controller에서 service를 호출하니까, 반드시 service 객체를 선언해야 한다 
	// 1. service를 호출하기 위해 맴버변수로 BCommand 선언
	BCommand command = null; 
	
	// @RequestMapping(메소드를 따로 선택해야 해서 get과 post 각각 선언 가능) = @GetMapping (get방식) = @PostMapping (post방식)
	
	@RequestMapping("list")
	public String list(Model model) {
		logger.info("BController list start");
		
		// 2. service 객체를 new로 선언 , 이 선언을 해주면 BCommand 인터페이스를 상속받는다 : 어떤 service를 하더라도 실행은 execute() 메소드가 실행된다 
		command = new BListCommand();  
		command.execute(model);        // model을 담고 있어서 service와 controller와 데이터 주고 받기 가능 
			
		return "list";
	}
	
	@GetMapping("/content_view")
	public String content_view(HttpServletRequest request, Model model) {  // request를  model에 담아서(39) map 방식으로 변경하고(set같은 역할) get으로 값 갖고 옴
		System.out.println("BController content_view");
		
		//               key             value
		//          String의 reqest    객체의 request
		model.addAttribute("request", request);
		command = new BContentCommand();
		command.execute(model);     	// model은 값을 보낼 수도 있고 받을 수도 있음 
		System.out.println("content_view End");
	
		return "content_view";
	}
	
	@PostMapping("/modify")
	public String modify(HttpServletRequest request, Model model) {
		logger.info("BController modify Start");
		
		model.addAttribute("request", request);
		command = new BModifyCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/write_view")
	public String write_view(Model model) {
		logger.info("BController write_view Start");
		
		return "write_view";  // model2이므로  view에서 다른 view를 부르지 않기 위해
	}
	
	@RequestMapping(value = "/write")
	public String write(HttpServletRequest request, Model model) {
		logger.info("BController write Start");
		
		model.addAttribute("request", request);
		command = new BWriteCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/reply_view")  // 댓글 달기 
	public String reply_view(HttpServletRequest request, Model model) {
		logger.info("BController reply_view Start");
		
		model.addAttribute("request", request);  // request에 bId 담겨져 있음
		command = new BReplyViewCommand();      
		command.execute(model);
		
		return "reply_view";
	}
	
	@RequestMapping(value = "/reply", method = RequestMethod.POST)
	public String reply(HttpServletRequest request, Model model) {
		System.out.println("BController reply Start");
		
		model.addAttribute("request", request);
		command = new BReplyCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		System.out.println("BController delete Start");
		
		model.addAttribute("request", request);
		command = new BDeleteCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	
}
