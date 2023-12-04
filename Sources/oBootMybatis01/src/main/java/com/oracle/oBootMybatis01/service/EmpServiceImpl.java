package com.oracle.oBootMybatis01.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.oracle.oBootMybatis01.dao.DeptDao;
import com.oracle.oBootMybatis01.dao.EmpDao;
import com.oracle.oBootMybatis01.dao.Member1Dao;
import com.oracle.oBootMybatis01.model.Dept;
import com.oracle.oBootMybatis01.model.DeptVO;
import com.oracle.oBootMybatis01.model.Emp;
import com.oracle.oBootMybatis01.model.EmpDept;
import com.oracle.oBootMybatis01.model.Member1;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor	
public class EmpServiceImpl implements EmpService {
	
	// 하나의 서비스에서 여러 개의 다오 연결 가능  
	private final EmpDao     ed;  
	private final DeptDao    dd;    // @RequiredArgsConstructor 로 인해 동시에 생성자 만듦
	private final Member1Dao md; 

	// 총 갯수
	@Override
	public int totalEmp() {
		System.out.println("EmpServiceImpl totalEmp Start...");
		int totEmpCnt = ed.totalEmp();
		System.out.println("EmpServiceImpl totalEmp totEmpCnt-> " + totEmpCnt);
		return totEmpCnt;
	}

	// 전체 리스트 갖고 오기
	@Override
	public List<Emp> listEmp(Emp emp) {
		System.out.println("EmpServiceImpl listEmp Start...");
		List<Emp> listEmp = ed.listEmp(emp);    // Dao
		System.out.println("EmpServiceImpl listEmp listEmp-> " + listEmp);
		
		return listEmp;
	}

// ----------------------------------------------------------------------	
	
	// 상세정보 
	@Override
	public Emp detailEmp(int empno) {
		System.out.println("EmpServiceImpl detailEmp Start...");
		Emp detailEmp = ed.detailEmp(empno);
		System.out.println("EmpServiceImpl detailEmp detailEmp-> " + detailEmp);  // null이 나오면 없는 사번이다 
		
		return detailEmp;
	}

// ----------------------------------------------------------------------	
	
	// 수정
	@Override
	public int updateEmp(Emp emp) {    // update는 결과가 int형이므로 int로 맞춰준다 
		System.out.println("EmpServiceImpl updateEmp Start...");
		int updateEmp = ed.updateEmp(emp);
		System.out.println("EmpServiceImpl updateEmp-> " + updateEmp);
		
		return updateEmp;
	}
	
// ----------------------------------------------------------------------	
	
	// 새 글 작성 -> 관리자 사번만 get
	@Override
	public List<Emp> listManager() {
		List<Emp> empList = null;
		System.out.println("EmpServiceImpl empList Start...");
		empList = ed.listManager();
		System.out.println("EmpServiceImpl empList empList.size()-> " + empList.size());
		
		return empList;
	}

	// 새 글 작성 -> Dept정보 : 부서(코드, 부서명)
	// Dept 정보라서 dept dao에 만들지, emp dao에 만들지 선택해야 한다  -> 테이블 조인의 경우 데이터량이 많은 곳에 dao 만듦
	@Override
	public List<Dept> deptSelect() {  
		List<Dept> deptList = null;
		System.out.println("EmpServiceImpl deptSelect Start...");
		deptList = dd.deptSelect();
		System.out.println("EmpServiceImpl deptSelect deptList.size()-> " + deptList.size());
		
		return deptList;
	}

	// 새 글 작성 -> insert
	@Override
	public int insertEmp(Emp emp) {
		System.out.println("EmpServiceImpl insertEmp Start...");
		int insertEmp = ed.insertEmp(emp);
		System.out.println("EmpServiceImpl insertEmp-> " + insertEmp);
		
		return insertEmp;
	}
	
// ----------------------------------------------------------------------	

	// 삭제
	@Override
	public int deleteEmp(int empno) {
		System.out.println("EmpServiceImpl deleteEmp Start...");
		int result = ed.deleteEmp(empno);
		System.out.println("EmpServiceImpl deleteEmp result-> " + result);
		
		return result;
	}

// ----------------------------------------------------------------------	

	// 검색 기능 
	@Override
	public List<Emp> listSearchEmp(Emp emp) {
		List<Emp> empSearchList = null;
		System.out.println("EmpServiceImpl empSearchList Start...");
		empSearchList = ed.empSearchList3(emp);
		System.out.println("EmpServiceImpl empSearchList-> " + empSearchList);
		
		return empSearchList;
	}

	@Override
	public int condTotalEmp(Emp emp) {
		System.out.println("EmpServiceImpl condTotalEmp Start...");
		int totEmpCnt = ed.condTotalEmp(emp);
		System.out.println("EmpServiceImpl totalEmp totEmpCnt-> " + totEmpCnt);
		
		return totEmpCnt;
	}

// ----------------------------------------------------------------------	

	// join해서 직원부서조회 
	@Override
	public List<EmpDept> listEmpDept() {
		List<EmpDept> listEmpDept = null;
		System.out.println("EmpServiceImpl listEmpDept Start...");
		listEmpDept = ed.listEmpDept();
		System.out.println("EmpServiceImpl listEmpDept-> " + listEmpDept);
		
		return listEmpDept;
	}

// ----------------------------------------------------------------------	

	// Procedure 통한 Dept 입력 후 VO 전달 
	@Override
	public void insertDept(DeptVO deptVO) {
		System.out.println("EmpServiceImpl insertDept Start...");
		dd.insertDept(deptVO);
	}

// ----------------------------------------------------------------------	

	// PL/SQL(부서조회 Cursor)
	@Override
	public void selListDept(HashMap<String, Object> map) {
		System.out.println("EmpServiceImpl selListDept Start...");
		dd.selListDept(map);
	}

// ----------------------------------------------------------------------	

	// interCetpor 
	@Override
	public int memCount(String id) {
		System.out.println("EmpServiceImpl memCount id-> " + id);
		return md.memCount(id);
	}

	// ID가 존재 
	@Override
	public List<Member1> listMem(Member1 member1) {
		System.out.println("EmpServiceImpl listMem Start");
		return md.listMem(member1);
	}

// ----------------------------------------------------------------------	

	// 부서 코드를 주면 부서명을 갖고 옴 
	@Override
	public String deptName(int deptno) {
		System.out.println("EmpServiceImpl deptName Start");
		return ed.deptName(deptno);
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
