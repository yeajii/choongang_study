package com.oracle.mvc042;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oracle.mvc042.dto.studentInformation;

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
	
	@RequestMapping("/index")
	public String index() {
		System.out.println("HomeController index Start...");
		
		return "index";
	}
	
	
	@RequestMapping("/studentView1")
	//                          파라미터만 한 방에 처리
	public String studentView1(studentInformation studentInformation, Model model) {
		logger.info("studentView1 Start...");
		
		System.out.println("1. studentInformation.getName()-> " + studentInformation.getName());
		System.out.println("1. studentInformation.getAge()-> " + studentInformation.getAge());
		System.out.println("1. studentInformation.getClassNum()-> " + studentInformation.getClassNum());
		System.out.println("1. studentInformation.getGradeNum()-> " + studentInformation.getGradeNum());
		
		model.addAttribute("studentInfo", studentInformation);
		
		return "studentView";
	}
	
	// 파라미터와 model 한방처리(파라미터를 가공하지 않아도 될 때 사용) : model 따로 선언 X 
	// model.addAttribute("studentInfo", studentInformation); = @ModelAttribute("studentInfo")
	@RequestMapping("/studentView2")
	public String studentView2(@ModelAttribute("studentInfo") studentInformation studentInformation) {
		logger.info("studentView2 Start...");
		
		System.out.println("2. studentInformation.getName()-> " + studentInformation.getName());
		System.out.println("2. studentInformation.getAge()-> " + studentInformation.getAge());
		System.out.println("2. studentInformation.getClassNum()-> " + studentInformation.getClassNum());
		System.out.println("2. studentInformation.getGradeNum()-> " + studentInformation.getGradeNum());
		
		return "studentView";
	}
	
	
	
	
}
