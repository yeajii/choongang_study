package com.oracle.oBootUpaApi01.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.oBootUpaApi01.domain.Member;
import com.oracle.oBootUpaApi01.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// @RestController = @Controller + @ResponseBody : 리턴값을 몸통 안에 넣음
// @ResponseBody에 의해 리턴값이 String이면 stringConverter 발동, 객체면 JsonConverter 발동된다  
// key를 주면 value 값을 주기 편해서 데이터가 한 건이라도 객체 사용 
// 9:32

@RestController
@RequiredArgsConstructor
@Slf4j   				// 별도로 LOGGING 선언하지 않아도 사용 가능
public class JpaRestApiController {
	private final MemberService memberService;  
	// final이므로 생성자를 만들어서 넣어주어야 하는데(DI방식), @RequiredArgsConstructor로 해결된다 
	
	// @RequestBody: 어노테이션을 명시함으로써 MessageConverter를 통한 데이터 변환 과정 적용 
	// postman ---> Body --> raw---> JSON	 
    // 예시    {	    "name" : "kkk222"	    }
	// @RequestBody : Json(member)으로 온것을  --> Member member Setting
	
	
	// member5 테이블에 있는 컬럼 중 내가 넣고 싶은 값을 넣으면 되는데, id는 시퀸스이므로 제외하고 입력한다  
	@PostMapping("/restApi/v1/memberSave")
	public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {  // 9:38 
		System.out.println("JpaRestApiController Api/v1/memberSave member.getId()-> " + member.getId());
		log.info("member.getName-> {}.", member.getName());
		log.info("member.getSal-> {}.", member.getSal());
		
		Long id = memberService.saveMember(member);  // 만들어진 시퀸스 = pk  
		return new CreateMemberResponse(id);  		 // API로 개발할 때 객체로 돌려주어야 한다, JsonConveter가 작동해서 json 형태로 돌려준다 
	}
	
	@Data
	static class CreateMemberRequest{  // static 내장 클래스 
		@NotEmpty
		private String name;
		private Long sal;
	}
	
	@Data
	class CreateMemberResponse{      // 내장 클래스 만드는 이유: 다른 데서 불러 쓸 일이 없을 때
		private final Long id;
//		public CreateMemberResponse(Long id) {   -->  @Data에  @RequiredArgsConstructor 포함돼서 안 써도 됨
//			this.id = id;
//		}
	}
	
	// Bad API = v1 
	@GetMapping("/restApi/v1/members")
	public List<Member> membersVer1(){
		System.out.println("JpaRestApiController restApi/v1/members Start..");
		List<Member> listMember = memberService.getListAllMember();
		return listMember;
	}
	
// --------------------------------------------------------------------------------------------	
	
	// T : 인스턴스 생성할 때 구체적인 타입으로 변경 --> 유연성 
	// 원래는 T data에 MemberRtnDto 객체에 있는 name과 sal만 보내주기로 했는데, 갑자기 member에도 없는 총 인원수를 달라고 한다면 여기에 추가하면 된다 
	@Data
	@AllArgsConstructor
	class Result<T>{
		private final int totCount;  // (추가) 총 인원수 
		private final T data;
	}
	
	@Data
	@AllArgsConstructor
	class MemberRtnDto{  // 이름과 급여만 보내기 위해 내부 클래스 만듦, 외부에서 쓸 일이 없어서 내부 클래스
		private String name;  
		private Long   sal;
	}
	
	// Good API  Easy Version
	// 목표 : 이름 & 급여 만 전송
	@GetMapping("/restApi/v21/members")
	public Result memberVer21() {
		List<Member> findMembers = memberService.getListAllMember();
		System.out.println("JpaRestApiController /restApi/v21/members findMembers.size()-> " + findMembers.size());
		List<MemberRtnDto> resultList = new ArrayList<MemberRtnDto>();
		// List<Member> findMembers를 --> List<Member> resultList 이전
		// 이전 목적 : 반드시 필요한 data 만 보여준다 (외부 노출 최대한 금지)
		
		for(Member member : findMembers) {
			MemberRtnDto memberRtnDto = new MemberRtnDto(member.getName(), member.getSal());
			System.out.println("/restApi/v21/members memberRtnDto.getName() -> " + memberRtnDto.getName());
			System.out.println("/restApi/v21/members memberRtnDto.getSal() -> " + memberRtnDto.getSal());
			resultList.add(memberRtnDto);
		}
		System.out.println("/restApi/v21/members resultList.size()" + resultList.size());
		// return new(resultList);     // private final int totCount; 이게 주석처리 되었을 때 
		return new Result(resultList.size(), resultList);
	}                   //     총 인원수                   T data               
	
	
	// Good API  Easy Version
	// 목표 : 이름 & 급여 만 전송
	@GetMapping("/restApi/v22/members")
	public Result memberVer22() {
	List<Member> findMembers = memberService.getListAllMember();
	System.out.println("JpaRestApiController /restApi/v22/members findMembers.size()-> " + findMembers.size());
//			List<MemberRtnDto> resultList = new ArrayList<MemberRtnDto>();
//			// List<Member> findMembers를 --> List<Member> resultList 이전
//			// 이전 목적 : 반드시 필요한 data 만 보여준다 (외부 노출 최대한 금지)
//			
//			for(Member m : findMembers) {
//				MemberRtnDto memberRtnDto = new MemberRtnDto(m.getName(), m.getSal());
//				System.out.println("/restApi/v22/members m.getName() -> " + m.getName());
//				System.out.println("/restApi/v22/members m.getSal() -> " + m.getSal());
//				resultList.add(memberRtnDto);
//			}
		
	    // 자바 8에서 추가한 스트림(Streams)을 걸면 람다를 활용할 수 있는 기술 중 하나 --> 위에 있는 코딩과 같은 것
		// Member member의 member = m
		List<MemberRtnDto> memberCollect = findMembers.stream()
													  .map(m->new MemberRtnDto(m.getName(), m.getSal()))   // 향상형 for문
													  .collect(Collectors.toList());
		
		System.out.println("/restApi/v22/members resultList.size()" + memberCollect.size());
		return new Result(memberCollect.size(), memberCollect);
	}
	
// ------------------------------------------------------------------------------	
	
	@Data
	static class UpdateMemberRequest{
		private String name;
		private Long sal;
	}
	
	@Data
	@AllArgsConstructor
	class UpdateMemberResponse{   // update할 객체 이름 
		private Long id;
		private String name;
		private Long sal;
	}
	
	/*
	 *   수정 API
	 *   PUT 방식을사용했는데, PUT은 전체 업데이트를 할 때 사용
	 *   URI 상에서 '{ }' 로 감싸여있는 부분과 동일한 변수명을 사용하는 방법
	 *   해당 데이터가 있으면 업데이트를 하기에 
	 *   PUT요청이 여러번 실행되어도 해당 데이터는 같은 상태이기에 멱등  --> 멱등 
	 */
	
	// update(수정)
	@PutMapping("/restApi/v21/members/{id}")  	// url 밑에 어떤 컬럼을 지정할 건지 정한다(엔티티 이름) 
	public UpdateMemberResponse updateMemberV21(@PathVariable("id") Long id,
												@RequestBody @Valid UpdateMemberRequest uMember) {
		memberService.updateMember(id, uMember.getName(), uMember.getSal());  // update
		Member findMember = memberService.findByMember(id); 				  // 조회 
		return new UpdateMemberResponse(findMember.getId(), findMember.getName(), findMember.getSal());
	}
		
	

	

}
