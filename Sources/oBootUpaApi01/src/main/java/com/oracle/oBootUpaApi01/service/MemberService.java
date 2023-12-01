package com.oracle.oBootUpaApi01.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.oBootUpaApi01.domain.Member;
import com.oracle.oBootUpaApi01.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional			 // jpa를 쓸 경우 걸어주어야 한다. 서비스 단위로 커밋과 롤백하게 한다 
public class MemberService {
	private final MemberRepository memberRepository;  // DI로 자동연동하기 위해 
	
	// 전체회원 조회 API
	public List<Member> getListAllMember(){
		List<Member> listMember = memberRepository.findAll();
		System.out.println("MemberService getListAllMember listMember.size()-> " + listMember.size());
		return listMember;
	}
	
	// 회원가입 API
	public Long saveMember(@Valid Member member) {
		System.out.println("MemberService join member.getName()-> " + member.getName());
		Long id = memberRepository.save(member);
		return id;
	}

	// 수정은 persist 안해도 된다 
	public void updateMember(Long id, String name, Long sal) {
		Member member = new Member();
		member.setId(id);
		member.setName(name);
		member.setSal(sal);
		System.out.println("MemberService updateMember member.getName()-> " + member.getName());
		System.out.println("MemberService updateMember member.getSal()-> " + member.getSal());
		memberRepository.updateByMember(member);  
		return;
	}

	public Member findByMember(Long memberId) {
		Member member = memberRepository.findByMember(memberId);
		System.out.println("MemberService findByMember member.getId()-> " + member.getId());
		System.out.println("MemberService findByMember member.getName()-> " + member.getName());
		return member;
	}
	
}
