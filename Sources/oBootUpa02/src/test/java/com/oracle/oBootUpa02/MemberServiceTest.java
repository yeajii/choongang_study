package com.oracle.oBootUpa02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.oBootUpa02.domain.Member;
import com.oracle.oBootUpa02.repository.MemberRepository;
import com.oracle.oBootUpa02.service.MemberService;

// @SpringBootTest : 스프링 부트 띄우고 테스트(이게 없으면 @Autowired 다 실패)
// 반복 가능한 테스트 지원, 각각의 테스트를 실행할 때마다 트랜잭션을 시작하고 
// 테스트가 끝나면 트랜잭션을 강제로 롤백

@SpringBootTest 
@Transactional   // 12:18
public class MemberServiceTest {
	@Autowired
	MemberService    memberService;  
	
	@Autowired
	MemberRepository memberRepository;
	
	
	@BeforeEach  // 12:36 
	public void before1() {
		System.out.println("Test @BeforeEach...");
	}
	
	
	@Test
	// @Rollback(value = false)  // 테스트의 rollback은 디폴트, 필요하다면 rollback 가능 
	public void memberSave() {
		// 조건
		Member member = new Member();
		member.setTeamname("고구려");
		member.setName("강이식");
		
		// 행위(Action)
		Member member3 = memberService.memberSave(member);	
		
		// 결과 
		System.out.println("MemberServiceTest member3.getId()-> " + member3.getId());
		System.out.println("MemberServiceTest member3.getName()-> " + member3.getName());
		System.out.println("MemberServiceTest member3.getTeam().getName()-> " + member3.getTeam().getName());
	}
	
	@Test
	public void memberFind() {
		// 조건
		// 회원 조회 --> 이순신
		Long findId = 1L;
		
		// 수행
		Member member = memberService.findByMember(findId);
		
		// 결과
		System.out.println("MemberServiceTest memberFind member3.getName()-> " + member.getName());
	}
	
	
	
	
	
	
}
