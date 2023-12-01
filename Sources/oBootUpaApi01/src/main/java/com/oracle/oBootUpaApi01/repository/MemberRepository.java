package com.oracle.oBootUpaApi01.repository;

import java.util.List;

import com.oracle.oBootUpaApi01.domain.Member;

public interface MemberRepository {
	Long         save(Member member);
	List<Member> findAll();
	int          updateByMember(Member member);
	Member       findByMember(Long memberId);

}
