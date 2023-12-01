package com.oracle.mvc03;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.oracle.mvc03.dto.Member;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	// Parameter를 갖고 controller에 들어옴 (get 방식)
	@RequestMapping("board/confirmId")  //    ("/board/confirmId") 이렇게 해도 됨
	public String confirmId(HttpServletRequest request, Model model) {
		
		logger.info("confirmId Start...");
		String id = request.getParameter("id");  // 입력한 파라미터 값 갖고 옴
		String pw = request.getParameter("pw");
		System.out.println("board/confirmId id-> " + id);
		System.out.println("board/confirmId pw-> " + pw);
		
		// view에 입력한 Parameter 값 전달 : view에 보여짐
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		
		return "board/confirmId";
	}
	
	// Parameter Force 
	// @RequestParam : request.getParameter의 기능과 동일
	@RequestMapping("board/checkId")
	public String checkId(@RequestParam("id") String idd,
						  @RequestParam("pw") int    passwd,
						  Model model 
						  ) {
		
		logger.info("checkId Start...");
		System.out.println("board/checkId idd-> " + idd);
		System.out.println("board/checkId passwd-> " + passwd);
		
		// view에 입력한 Parameter 값 전달 : 이름 다르게 해서 보내도 가능
		model.addAttribute("identify", idd);
		model.addAttribute("password", passwd);
		
		return "board/checkId";
	}
	
	// Parameter Force 
	@RequestMapping("member/join")
	public String member(
							@RequestParam("name") String name, 
							@RequestParam("id") String id, 
							@RequestParam("pw") String pw, 
							@RequestParam("email") String email, 
							Model model
							){
		
		System.out.println("member/join name-> " + name);						
		System.out.println("member/join id-> " + id);						
		System.out.println("member/join pw-> " + pw);						
		System.out.println("member/join email-> " + email);
		
		Member member = new Member();  // dto 생성
		member.setName(name);          // dto에  값 세팅
		member.setId(id);
		member.setPw(pw);
		member.setEmail(email);
		
		// view에 Parameter 값 보내기
		model.addAttribute("member", member);
		
		return "member/join";
	}
	
	// 위와 100프로 같지 않고 거의 비슷하다, dto의 맴버들로 이루어진 파라메타라면, dto 자체를 받아서 값을 지정해도 됨
	// Parameter Force
		@RequestMapping("member/join/dto")
		public String memberDto(
								Member member, 
								Model model
								){
			
			System.out.println("member/join/dto getName-> " + member.getName());						
			System.out.println("member/join/dto getId-> " + member.getId());						
			System.out.println("member/join/dto getPw-> " + member.getPw());						
			System.out.println("member/join/dto getEmail-> " + member.getEmail());
			
			// view에 Parameter 값 보내기
			model.addAttribute("member", member);
			
			return "member/join";
		}
	

	
}
