package com.oracle.oBootMybatis01.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.oracle.oBootMybatis01.domain.Member;
import com.oracle.oBootMybatis01.service.MemberJpaService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oracle.net.aso.m;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberJpaController {

	private final MemberJpaService memberJpaService;

	@GetMapping(value = "/memberJpa/new")
	public String createForm() {
		System.out.println("MemberJpaController /memberJpa/new Start...");
		
		return "memberJpa/createMemberForm";
	}
	
	// 회원가입
	@PostMapping(value = "/memberJpa/save")
	public String create(Member member) {
		System.out.println("MemberJpaController /memberJpa/save Start...");
		System.out.println("member.getId()-> " + member.getId());
		System.out.println("member.getName()-> " + member.getName());
		memberJpaService.join(member);
		
		return "memberJpa/createMemberForm";
	}
	
	// 회원목록 
	@GetMapping(value = "/members")
	public String listMember(Model model) {
		System.out.println("MemberJpaController listMember Start...");
		List<Member> memberList = memberJpaService.getListAllMember();
		model.addAttribute("members", memberList);
		
		return "memberJpa/memberList";
	}
	
// ----------------------------------------------------------------------------	

	// update 하기 전 객체가  null check
	@GetMapping(value = "/memberJpa/memberUpdateForm")
	public String memberUpdateForm(Long id, Model model) {
		Member member = null;
		String rntJsp = "";
		System.out.println("MemberJpaController memberUpdateForm id-> " + id);
		
		// 목적: 객체가 null check 용이
		Optional<Member> maybeMember = memberJpaService.findById(id);
		
		// isPresent : Boolean 타입 / Optional 객체가 값을 가지고 있다면 true, 값이 없다면 false 리턴
		if(maybeMember.isPresent()) {  // null값 아니다 
			System.out.println("MemberJpaController memberUpdateForm maybeMember is not null");
			member = maybeMember.get();
			model.addAttribute("member", member);
			rntJsp = "memberJpa/memberModify";
		} else {                      // null 값이다 
			System.out.println("MemberJpaController memberUpdateForm maybeMember is null");
			model.addAttribute("message", "member가 존재하지 않으니 입력부터 수행해 주세요");
			rntJsp = "forward:/members";
		}
		return rntJsp;
	}
	
	// update
	@GetMapping(value = "memberJpa/memberUpdate")
	public String memberUpdate(Member member, Model model) {
		System.out.println("MemberJpaController memberUpdate member.getId()-> " + member.getId());
		System.out.println("MemberJpaController memberUpdate member.getName()-> " + member.getName());
		memberJpaService.memberUpdate(member);
		
		return "redirect:/members";
	}
	

	
}
