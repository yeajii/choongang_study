package com.oracle.oBootUpa02.repository;

import java.util.List;

import com.oracle.oBootUpa02.domain.Member;

public interface MemberRepository {
	Member        memberSave(Member member);
	List<Member>  findAll();
	Member        findByMember(Long memberId);
	List<Member>  findByNames(String searchName);
	List<Member>  findByMembers(Long id, Long sal);
	int           updateByMember(Member member);

}
