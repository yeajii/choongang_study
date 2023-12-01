package com.oracle.oBootHello.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.oracle.oBootHello.domain.Member1;

@Repository
public class MemoryMemberRepository implements MemberRepository {
	
	// 메모리에 저장 = 맵 사용
	//                  key   value
	private static Map<Long, Member1> store = new HashMap<Long, Member1>();
	private static Long sequence = 0L;
	

	@Override
	public Member1 save(Member1 member1) {
		member1.setId(++sequence);
		store.put(member1.getId(), member1);
		
		System.out.println("MemoryMemberRepository sequence-> " + sequence);
		System.out.println("MemoryMemberRepository member1.getName()-> " + member1.getName());
		
		return member1;
	}

	@Override
	public List<Member1> findAll() {
		System.out.println("MemoryMemberRepository findAll start");
		
		// store.values() : value인 Member1 에 있는 요소들을 리스트가 됨, 그 리스트 변수명이 listMember
		List<Member1> listMember = new ArrayList<>(store.values());  
		System.out.println("MemoryMemberRepository findAll listMember.size()-> " + listMember.size());
		
		return listMember;
	}

	
}
