package com.oracle.oBootUpa02.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.oracle.oBootUpa02.domain.Member;
import com.oracle.oBootUpa02.service.MemberService;


@Controller
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	private final MemberService memberService;
	@Autowired
	public MemberController(MemberService memberService) {  // controller에서 service 연결
		this.memberService = memberService;
	}
	
	// Member 신규 생성
	@GetMapping(value = "/members/new")  
	public String createForm() {
		System.out.println("MemberController /members/new Start...");
		return "members/createMemberForm";
	}
	
	// JPA 회원 등록2 (회원이름, 팀이름 기입)
	@PostMapping(value = "/memberSave")  
	public String memberSave(Member member) {
		System.out.println("MemberController create Start...");
		System.out.println("member.getName()" + member.getName());
		System.out.println("member.getTeam()" + member.getTeam());
		
		memberService.memberSave(member);
		System.out.println("member-> "+ member);
		return "redirect:/";
	}
	
	// Member List 조회
	@GetMapping(value = "/members")
	public String listMember(Model model) {
		List<Member> memberList = memberService.getListAllMember();
		System.out.println("memberList.get(0).getName()-> " + memberList.get(0).getName());
		System.out.println("memberList.get(0).getTeam().getName()->" + memberList.get(0).getTeam().getName());
		
		model.addAttribute("memberList", memberList);
		return "members/memberList";
	}
	
	// JPA2 회원 조회 (회원 이름 검색)
	@GetMapping(value = "/members/search")
	public String search(Member member, Model model) {
		System.out.println("/members/search member.getName()-> " + member.getName());
		List<Member> memberList = memberService.getListSearchMember(member.getName());
		System.out.println("MemberController /members/search memberList.size()-> " + memberList.size());
		
		model.addAttribute("memberList", memberList);
		return "members/memberList";
	}
	
	// JPA2 회원 조회 (회원 검색 Id 큰 것: / 회원 검색 급여 큰 것:)
	@GetMapping(value = "findByListMembers")
	public String findByListMembers(Member member, Model model) {
		List<Member> memberList = memberService.getListFindByMembers(member);
		System.out.println("memberList.get(0).getName()-> " + memberList.get(0).getName());
		System.out.println("memberList.get(0).getTeam().getName()-> " + memberList.get(0).getTeam().getName());
		
		model.addAttribute("memberList", memberList);
		return "members/memberList";
	}
	
	// JPA 회원수정 (회원이름, 팀이름 수정)
	@GetMapping(value = "memberModifyForm")
	public String memberModify(Long id, Model model) {
		System.out.println("MemberController memberModify id-> " + id);
		
		Member member = memberService.findByMember(id);
		System.out.println("member.getId()-> " + member.getId());
		System.out.println("member.getName()-> " + member.getName());
		System.out.println("member.getTeam().getName()-> " + member.getTeam().getName());
		
		model.addAttribute("member", member);
		return "members/memberModify";
	}
	
	// 회원수정 
	@PostMapping(value = "/members/memberUpdate")
	public String memberUpdate(Member member, Model model) {
		System.out.println("MemberController member.getId()-> " + member.getId());
		System.out.println("MemberController member.getName()-> " + member.getName());
		System.out.println("MemberController member.getTeamname()-> " + member.getTeamname());
		
		memberService.memberUpdate(member);
		System.out.println("MemberController memberUpdate memberService.memberUpdate After");
		return "redirect:/members";
	}
	
	

	
}
