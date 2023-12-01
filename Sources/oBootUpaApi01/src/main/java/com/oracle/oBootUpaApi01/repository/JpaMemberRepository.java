package com.oracle.oBootUpaApi01.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.oracle.oBootUpaApi01.domain.Member;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor  // 생성자를 만든 것과 동일 
public class JpaMemberRepository implements MemberRepository {
	
	private final EntityManager em;  // 생성자 안 만들어도 됨 

	@Override
	public Long save(Member member) {
		System.out.println("JpaMemberRepository save before..");
		em.persist(member);   // @Entity 있는 것에서 insert 쿼리 만든다 
		return member.getId();
	}

	@Override
	public List<Member> findAll() {
		List<Member> memberList = em.createQuery("select m from Member m", Member.class).getResultList();
		System.out.println("JpaMemberRepository findAll memberList.size()-> " + memberList.size());
		return memberList;
	}

	@Override
	public int updateByMember(Member member) {
		int result = 0;    //      class이름                PK            em.find(entity.class, id)
		Member member3 = em.find(Member.class, member.getId());  // find : 영속성 관리해준다 
		
		if(member3 != null) {
			// 회원 저장 
			member3.setName(member.getName());
			member3.setSal(member.getSal());
			result = 1;
			System.out.println("JpaMemberRepository updateByMember Update");
		} else {
			result = 0;
			System.out.println("JpaMemberRepository updateByMember No Exist");
		}
		return result;
	}

	@Override
	public Member findByMember(Long memberId) {
		Member member = em.find(Member.class, memberId);
		return member;
	}
	
	
	
	
	
	
	
	

}
