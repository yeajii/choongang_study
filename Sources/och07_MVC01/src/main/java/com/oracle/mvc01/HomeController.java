package com.oracle.mvc01;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	                                                         // 컨트롤러 이름이 달라지면 같이 변경하기
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	// value = "/"  -> / 는 기본폴더를 가르킴(com.oracle.mvc01)
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {					// Model : spring framework에서 데이터 전달하는 기본 객체
		logger.info("Welcome home! The client locale is {}.", locale);  // logger : 위치까지 상세하게 나옴
		logger.info("context / start");
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		System.out.println("formattedDate-> " + formattedDate);  // 불필요한 정보 제거되고 출력
		
		model.addAttribute("serverTime", formattedDate );        // addAttribute = request.setAttribute
		
		// view 지정 = home.jsp
		// /WEB-INF/views/ + home + .jsp
		return "home";
	}
	
}
