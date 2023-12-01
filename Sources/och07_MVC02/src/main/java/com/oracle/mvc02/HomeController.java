package com.oracle.mvc02;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
	
	// @RequestMapping : 들어온 요청을 특정 메소드와 매핑하기 위한 어노테이션
	@RequestMapping(value = "/board/veiw")
	public String view() {
		logger.info("welcome {} start...", "board/veiw");  // board/veiw가 {}안에 들어감 : 문법
		
		return "board/view";  // views 폴더 내에 board 폴더 내에 view.jsp가 있다
	}
	
	// value = 생략 가능 -> 바로 url 작성
	@RequestMapping("/board/content")
	public String content(Model model) {
		System.out.println("homeController content start...");
		model.addAttribute("id", 365);
		
		return "board/content";
	}
	
	// ModelAndView : model과 view를 섞어서 사용하는 객체, 객체로 리턴을 해주어야 하므로 return형으로 해야됨
	@RequestMapping("/board/reply")
	public ModelAndView reply() {
		System.out.println("HomeController reply start...");
		
		// 목적 : parameter와 View를 한방에 처리
		ModelAndView mav = new ModelAndView(); 
		mav.addObject("id", 927);         // 파라미터 담고
		mav.setViewName("board/reply");   // view 이름을 담아서
		
		return mav;  					  // 객체를 return 한다
	}
	
	
	
	
}
