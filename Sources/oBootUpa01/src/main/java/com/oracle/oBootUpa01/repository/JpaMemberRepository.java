package com.oracle.oBootUpa01.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.oracle.oBootUpa01.domain.Member;

@Repository
public class JpaMemberRepository implements MemberRepository {
	// JPA DML --> EntityManager 필수  --> DBCP 역할
	private final EntityManager em;
	public JpaMemberRepository(EntityManager em) {  // 생성자 주입
		this.em = em;  
	}
	
	@Override
	public Member memberSave(Member member) {
		// 저장  method
		em.persist(member);
		System.out.println("JpaMemberRepository memberSave member After");
		return member;
	}

	@Override
	public List<Member> findAllMember() {                   //  객체명 -> jpa는 객체 중심(테이블 아님)
		List<Member> memberList = em.createQuery("select m from Member m", Member.class).getResultList(); // getResultList(): 리스트 형태로 돌려줌
		System.out.println("JpaMemberRepository findAllMember memberList.size()-> " + memberList.size());
		return memberList;
	}

	@Override
	public List<Member> findByNames(String searchName) {
		String pname = searchName + '%';
		System.out.println("JpaMemberRepository findByNames pname-> " + pname);
		List<Member> memberList = em.createQuery("select m from Member m where name Like : name", Member.class)
								    .setParameter("name", pname)
								    .getResultList();
		System.out.println("JpaMemberRepository memberList.size()-> " + memberList.size());
		return memberList;
	}

}
