package com.oracle.oBootMybatis01.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.oracle.oBootMybatis01.model.Dept;
import com.oracle.oBootMybatis01.model.DeptVO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class DeptDaoImpl implements DeptDao {
	
	// Mybatis DB 연동 = SqlSession
	private final SqlSession session;

	// 새 글 작성 -> 부서(코드, 부서명)
	@Override
	public List<Dept> deptSelect() {
		System.out.println("DeptDaoImpl deptSelect Start...");
		
		List<Dept> deptList = null;
		try {
			deptList = session.selectList("tkSelectDept");
		}catch (Exception e) {
			System.out.println("DeptDaoImpl deptSelect Exception-> " +  e.getMessage());
		}
		return deptList;
	}

// -------------------------------------------------------------------

	// Procedure 통한 Dept 입력 후 VO 전달 
	@Override
	public void insertDept(DeptVO deptVO) {
		System.out.println("DeptDaoImpl insertDept Start...");
		session.selectOne("procDeptInsert", deptVO);
	}

// -------------------------------------------------------------------

	// PL/SQL(부서조회 Cursor)
	@Override
	public void selListDept(HashMap<String, Object> map) {
		System.out.println("DeptDaoImpl selListDept Start...");
		session.selectOne("procDeptList", map);	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
