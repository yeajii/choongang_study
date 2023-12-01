package com.oracle.oBootHello.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootHello.domain.Member1;
import com.oracle.oBootHello.repository.MemberRepository;
import com.oracle.oBootHello.repository.MemoryMemberRepository;

// component로 되어져서 인스턴스 만들 수 있게 되어서 controller에서 만들어진 인스턴스 사용 가능
@Service  
public class MemberService {
	// 전통적인 방식(service에서 dao 연결), 인터페이스 선언
	// MemberRepository memberRepository = new MemoryMemberRepository();
	
	// DI 방식
	private final MemberRepository memberRepository;
	
	@Autowired // 생성자에 @Autowired 가 있으면 스프링이 연관된 객체(bean)를 스프링 컨테이너에서 찾아서 넣어줌
	public MemberService(MemberRepository memberRepository) { // 생성자
		this.memberRepository = memberRepository;
	}
	
	
	// 회원가입
	public Long memberSave(Member1 member1) {
		System.out.println("MemberService memberSave Start");
		
		memberRepository.save(member1); 
		
		return member1.getId();
	}
	
	
	// 회원조회 
	public List<Member1> allMembers(){
		System.out.println("MemberService allMembers Start");
		
		List<Member1> memList = null;
		memList = memberRepository.findAll();
		System.out.println("memList.size()-> " + memList.size());
		
		return memList;
	}
	
	
	
	
	
	

}
