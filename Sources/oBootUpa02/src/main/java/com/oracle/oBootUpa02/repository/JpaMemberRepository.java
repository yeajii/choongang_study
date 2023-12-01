package com.oracle.oBootUpa02.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootUpa02.domain.Member;
import com.oracle.oBootUpa02.domain.Team;

@Repository
public class JpaMemberRepository implements MemberRepository {
	
	private final EntityManager em;
	@Autowired
	public JpaMemberRepository(EntityManager em) {  // 생성자 통해 주입(생성자 DI)
		this.em = em;
	}

	@Override
	public Member memberSave(Member member) {
		// 팀 저장
		Team team = new Team();
		team.setName(member.getTeamname());  // jpa에서 객체로 취급할 때는 name 사용, 시퀸스를 통해 team_id 자동 생성, @Entity된 것에 해당    // 1. setter 이용해서 어떠한 값을 가지고
		em.persist(team);                                                                          // 2. 그 값 안에 pk가 없으면 insert 수행
		System.out.println("JpaMemberRepository memberSave em.persist(team) End..");
		
		// 회원 저장 -> FK 맞추는 과정
		member.setTeam(team);
		em.persist(member);     // team 테이블에 들어간 team_id와 같게 쓰임
		System.out.println("JpaMemberRepository memberSave em.persist(member) End..");
		return member;
	}

	@Override
	public List<Member> findAll() {
		List<Member> memberList = em.createQuery("select m from Member m", Member.class).getResultList(); // getResultList(): 리스트 형태로 돌려줌
		System.out.println("JpaMemberRepository findAllMember memberList.size()-> " + memberList.size());
		return memberList;
	}

	@Override
	public Member findByMember(Long memberId) {
		//                         Entity      PK    9:45
		Member member = em.find(Member.class, memberId);
		return member;
	}

	@Override
	public List<Member> findByNames(String searchName) { 
		String pname = searchName + '%';
		System.out.println("JpaMemberRepository findByNames pname-> " + pname);
		
		List<Member> memberList = em.createQuery("select k from Member k where name Like : name", Member.class) 
								.setParameter("name", pname)
								.getResultList();
		System.out.println("JpaMemberRepository memberList.size()-> " + memberList.size());
		return memberList;
	}
	
	// 검색조건으로 입력한 id 큰 거, sal 큰  거 
	@Override
	public List<Member> findByMembers(Long pid, Long psal) {
		System.out.println(pid);
		System.out.println(psal);
		
		List<Member> memberList = em.createQuery("select m from Member m where id > :id and sal > :sal", Member.class)
								.setParameter("id", pid)                         // :을 쓰면 파라미터 의미
								.setParameter("sal", psal)
								.getResultList();
		System.out.println("JpaMemberRepository findByMembers memberList.size()-> " + memberList.size());
		return memberList;
	}

	@Override
	public int updateByMember(Member member) {
		int result = 0;
		System.out.println("JpaMemberRepository updateByMember member.getId()-> " + member.getId());
		
		Member member3 = em.find(Member.class, member.getId());
		
		if(member3 != null) {
			// 팀 저장
			System.out.println("JpaMemberRepository updateByMember member.getTeamid()-> " + member.getTeamid());
			Team team = em.find(Team.class, member.getTeamid());
			
			if(team != null) {
				System.out.println("JpaMemberRepository updateByMember member.getTeamname()-> " + member.getTeamname());
				team.setName(member.getTeamname());  // 1. setter 이용해서 어떠한 값을 가지고
				em.persist(team);    				 // 2. 그 값 안에 pk가 있으면  update 수행
			}
			// 회원 저장
			System.out.println("JpaMemberRepository updateByMember member.getName()-> " + member.getName());
			member3.setTeam(team);				// 단방향 연관관계 설정, 참조 저장
			member3.setName(member.getName());  // 단방향 연관관계 설정, 참조 저장
			em.persist(member3);
			result = 1;
		} else {
			result = 0;
			System.out.println("JpaMemberRepository updateByMember No Exist..");
		}
		return result;	
	}
	
	

	

}
