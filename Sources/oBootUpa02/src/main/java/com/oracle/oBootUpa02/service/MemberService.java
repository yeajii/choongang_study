package com.oracle.oBootUpa02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.oBootUpa02.domain.Member;
import com.oracle.oBootUpa02.repository.MemberRepository;
// @Transactional 
// jpa는 트렌젝션 단위로 작업하므로, @Service에 걸어주거나 @Repository에 걸어주어야 하는데
// 서비스 안에서 동시에 롤백되거나 커밋되야 하므로 권장은 @Service이다 

@Service
@Transactional  
public class MemberService {
	private final MemberRepository memberRepository;
	
	@Autowired
	public MemberService(MemberRepository memberRepository) {  // service에서 Repository(dao) 연결
		this.memberRepository = memberRepository;
	}
	
	public Member memberSave(Member member) {
		System.out.println("MemberService join member.getName()-> " + member.getName());
		
		memberRepository.memberSave(member);
		System.out.println("MemberService memberSave End..");
		return member;
	}

	// 전체 회원 조회
	public List<Member> getListAllMember() {
		List<Member> listmember = memberRepository.findAll();
		System.out.println("MemberService getListAllMember listmember.size()-> " + listmember.size());
		return listmember;
	}

	public List<Member> getListSearchMember(String searchName) {
		System.out.println("MemberService getListSearchMember Start");
		System.out.println("MemberService getListSearchMember SearchName" + searchName);
		
		List<Member> listMember = memberRepository.findByNames(searchName);
		System.out.println("MemberService getListSearchMember listMember.size()" + listMember.size());
		return listMember;
	}

	public List<Member> getListFindByMembers(Member member) {
		List<Member> listMember = memberRepository.findByMembers(member.getId(), member.getSal());
		System.out.println("MemberService getListFindByMembers listMember.size()-> " + listMember.size());
		return listMember;
	}

	public Member findByMember(Long memberId) {
		Member member1 = memberRepository.findByMember(memberId);
		System.out.println("MemberService member1.getId()-> " + member1.getId());
		System.out.println("MemberService member1.getName()-> " + member1.getName());
		System.out.println("MemberService member1.getTeam().getName()-> "+ member1.getTeam().getName());
		return member1;
	}

	public void memberUpdate(Member member) {
		System.out.println("MemberService member.getName()-> " + member.getName());
		System.out.println("MemberService member.getTeamname()-> " + member.getTeamname());
		memberRepository.updateByMember(member);
		System.out.println("MemberService memberUpdate memberRepository.updateByMember Atfer");
		return;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
