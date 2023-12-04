package com.oracle.oBootMybatis01.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.oracle.oBootMybatis01.domain.Member;

import lombok.RequiredArgsConstructor;

@Repository  
@RequiredArgsConstructor   
public class MemberJpaRepositoryImpl implements MemberJpaRepository {

	private final EntityManager em;
	
	@Override
	public Member save(Member member) {
		System.out.println("MemberJpaRepositoryImpl save Start..");
		em.persist(member);
		return member;
	}

	@Override
	public List<Member> findAll() {
		System.out.println("MemberJpaRepositoryImpl findAll Start..");
		List<Member> memberList = em.createQuery("select m from Member m", Member.class)
									.getResultList();
		return memberList;
	}

// -------------------------------------------------------------------------------	

	// update 하기 전 객체가 null인지 조회  
	@Override
	public Optional<Member> findById(Long memberId) {
		Member member = em.find(Member.class, memberId);
		
		return Optional.ofNullable(member);
		// memberId가 pk가 아니면 member가 null일 것이다. 
		// null일지라도 Optional.ofNullable 해주면 Optional<Member> 형태로 된다  
		// -> member가 null이 담겨져서 넘어갈 수 있다 
	}

	// update -> merge
	@Override
	public void updateByMember(Member member) {
		// merge : 현재 Setting 된 것만 수정, 나머지는 null
		// em.merge(member); 
		
		Member member3 = em.find(Member.class, member.getId());  // pk, 영속성
		member3.setName(member.getName());  // 수정할 것만 setter로 건다 
		// member3.setPassword(member.getPassword());
		em.persist(member3);
		return;	
	}
	
	
	
	
	
	
	
	
	

}
