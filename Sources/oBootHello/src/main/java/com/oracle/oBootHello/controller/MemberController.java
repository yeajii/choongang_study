package com.oracle.oBootHello.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.oracle.oBootHello.domain.Member1;
import com.oracle.oBootHello.service.MemberService;

@Controller
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	// 전통적
	// MemberService memberService = new MemberService();
	
	// DI 방식 (setter, 생성자) 생성자 사용
	// 생성자를 통해 service 연결하면 거의 바꾸지 않아서 final 선언, stter는 바뀔 수 있어서 선언 X
	private final MemberService memberService; // 생성자에서 만들어준 memberService
	
	@Autowired   
	public MemberController(MemberService memberService) {  // 생성자, memberService = 인스턴스 된 상태 
		this.memberService = memberService;
	}
	
	// 회원등록
	@GetMapping(value = "members/memberForm")
	public String memberForm() {
		System.out.println("MemberController / members/memberForm Start..");
		
		// prefix -> templates
		// suffix -> .html
		return "members/memberForm";
	}
	
	// 값 저장
	@PostMapping(value = "/members/save")  // name을 갖고 옴
	public String save(Member1 member1) {
		System.out.println("MemberController /members/save Start..");
		System.out.println("MemberController /members/save member1.getName()->" + member1.getName());
		
		Long id = memberService.memberSave(member1);
		System.out.println("MemberController /members/save id-> "+ id);
		
		return "redirect:/";
	}

	// 회원조회
	@GetMapping(value = "/members/memberList")
	public String memberList(Model model) {
		logger.info("memberList Start");
		
		List<Member1> memberLists = memberService.allMembers();
		model.addAttribute("memberLists", memberLists);
		logger.info("memberList.size()-> {}", memberLists.size());
		
		return "members/memberList";
	}
	
	
	
	
	
	
	
	
	
	
}
