package com.oracle.oBootMybatis01.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.oracle.oBootMybatis01.model.Member1;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class Member1DaoImpl implements Member1Dao {

	// mybatis DB 연동
	private final SqlSession session;
	
//	LomBok 안 쓸 때 @RequiredArgsConstructor 사용 못하므로 생성자 만들어서 사용
//	public Member1DaoImpl(SqlSession session) {
//		this.session = session;
//	}
	
	@Override
	public int memCount(String id) {
		int result = 0;
		System.out.println(id);
		
		try {
			result = session.selectOne("memCount", id);
		} catch (Exception e) {
			System.out.println("Member1DaoImpl memCount Exception-> " + e.getMessage());
		}
		return result;
	}

	// ID 존재
	@Override
	public List<Member1> listMem(Member1 member1) {
		System.out.println("Member1DaoImpl listMem Start");
		List<Member1> listMember1 = null;
		try {
			listMember1 = session.selectList("listMember1", member1);
		} catch (Exception e) {
			System.out.println("Member1DaoImpl listMem Exception-> " + e.getMessage());
		}
		return listMember1;
	}
	
	

	

}
