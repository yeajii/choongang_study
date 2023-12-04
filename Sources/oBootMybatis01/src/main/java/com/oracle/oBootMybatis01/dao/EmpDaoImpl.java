package com.oracle.oBootMybatis01.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.oracle.oBootMybatis01.model.Emp;
import com.oracle.oBootMybatis01.model.EmpDept;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor   // final이라 생성자 만들어야 해서 선언함 
public class EmpDaoImpl implements EmpDao {

	// Mybatis DB 연동 = SqlSession, sql은 dao와 연결되니까  @Repository에서 해준다
	// CRUD를 session 에서 처리
	private final SqlSession session;
	
	// 총 갯수
	@Override
	public int totalEmp() {
		System.out.println("EmpDaoImpl totalEmp Start..");
		
		int totEmpCount = 0;
		// session이 안 잡힐 수 있으므로 try문 걸기 
		try {
			totEmpCount = session.selectOne("empTotal");  //  Emp.xml의  mapper id와 연결
			System.out.println("EmpDaoImpl totalEmp totEmpCount-> " + totEmpCount);
			
		} catch (Exception e) {
			System.out.println("EmpDaoImpl totalEmp Exception-> " +  e.getMessage());
		}
		return totEmpCount;
	}

	// 전체 리스트 갖고 오기   
	@Override
	public List<Emp> listEmp(Emp emp) {
		System.out.println("EmpDaoImpl listEmp Start..");
		
		List<Emp> listEmp = new ArrayList<Emp>();  
		try {
			//                                Map ID     parameter 
			listEmp = session.selectList("tkEmpListAll", emp);
			System.out.println("EmpDaoImpl List<Emp> empList.size()-> " +  listEmp.size());
			
		}catch (Exception e) {
			System.out.println("EmpDaoImpl List<Emp> Exception-> " +  e.getMessage());
		}
		return listEmp;
	}
	
// ----------------------------------------------------------------------	

	// 상세정보
	@Override
	public Emp detailEmp(int empno) {
		System.out.println("EmpDaoImpl detailEmp Start..");
		
		Emp detailEmp = null;
		try {
			detailEmp = session.selectOne("tkEmpSelOne", empno);
			System.out.println("EmpDaoImpl detailEmp detailEmp-> " +  detailEmp);  // null이 나오면 없는 사번이다 
			
		}catch (Exception e) {
			System.out.println("EmpDaoImpl detailEmp Exception-> " +  e.getMessage());
		}
		return detailEmp;
	}
	
// ----------------------------------------------------------------------
	
	// 수정 
	@Override
	public int updateEmp(Emp emp) {
		System.out.println("EmpDaoImpl updateEmp Start..");
		
		int updateEmp = 0;
		
		try {
			updateEmp = session.update("tkEmpUpdate", emp);
			System.out.println("EmpDaoImpl detailEmp updateEmp-> " +  updateEmp);
			
		} catch (Exception e) {
			System.out.println("EmpDaoImpl updateEmp Exception-> " +  e.getMessage());
		}
		return updateEmp;
	}

// ----------------------------------------------------------------------	
	
	// 새 글 작성 -> emp 관리자만 select    
	@Override
	public List<Emp> listManager() {
		System.out.println("EmpDaoImpl listManager Start..");
		
		List<Emp> empList = null;
		try {
			// emp 관리자만 select             Naming Nule
			empList = session.selectList("tkSelectManager");
		}catch (Exception e) {
			System.out.println("EmpDaoImpl listManager Exception-> " +  e.getMessage());
		}
		return empList;
	}

	// 새 글 작성 -> insert
	@Override
	public int insertEmp(Emp emp) {
		System.out.println("EmpDaoImpl insertEmp Start..");
		
		int insertEmp = 0;
		try {
			insertEmp = session.insert("tkEmpInsert", emp);
			System.out.println("EmpDaoImpl insertEmp-> " +  insertEmp);
			
		} catch (Exception e) {
			System.out.println("EmpDaoImpl insertEmp Exception-> " +  e.getMessage());
		}
		return insertEmp;
	}

// ----------------------------------------------------------------------		

	// 삭제
	@Override
	public int deleteEmp(int empno) {
		System.out.println("EmpDaoImpl deleteEmp Start..");
		int deleteEmp = 0;
		
		try {
			deleteEmp = session.delete("tkEmpDelete", empno);
			System.out.println("EmpDaoImpl deleteEmp-> " +  deleteEmp);
			
		} catch (Exception e) {
			System.out.println("EmpDaoImpl deleteEmp Exception-> " +  e.getMessage());
		}
		return deleteEmp;
	}
	
// ----------------------------------------------------------------------		
	
	// 검색 기능
	@Override                       //   search, keyword 담겨 있음
	public List<Emp> empSearchList3(Emp emp) {
		List<Emp> empSearchList3 = null;
		System.out.println("EmpDaoImpl empSearchList3 Start..");
		
		try {
			// keyword 검색
			// Naming Rule                            Map Id        parameter 
			empSearchList3 = session.selectList("tkEmpSearchList3", emp);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl empSearchList3 Exception-> " +  e.getMessage());
		}
		return empSearchList3;
	}

	
	@Override
	public int condTotalEmp(Emp emp) {
		System.out.println("EmpDaoImpl condTotalEmp Start..");
		int totEmpCount = 0;
		
		try {
			totEmpCount = session.selectOne("condEmpTotal", emp);  //  Emp.xml의  mapper id와 연결
			System.out.println("EmpDaoImpl totalEmp totEmpCount-> " + totEmpCount);
			
		} catch (Exception e) {
			System.out.println("EmpDaoImpl totalEmp Exception-> " +  e.getMessage());
		}
		return totEmpCount;
	}

	
// ----------------------------------------------------------------------	

	// join해서 직원부서조회 
	@Override
	public List<EmpDept> listEmpDept() {
		List<EmpDept> listEmpDept = new ArrayList<EmpDept>(); 
		System.out.println("EmpDaoImpl listEmpDept Start..");
		
		try {
			listEmpDept = session.selectList("tkListEmpDept");
			System.out.println("EmpDaoImpl totalEmp listEmpDept.size()-> " + listEmpDept.size());
			
		} catch (Exception e) {
			System.out.println("EmpDaoImpl listEmpDept Exception-> " +  e.getMessage());
		}
		return listEmpDept;
	}

// ----------------------------------------------------------------------	

	// 부서 코드(pk)를 주면 부서명을 갖고 옴 
	@Override
	public String deptName(int deptno) {
		System.out.println("EmpDaoImpl deptName Start..");
		
		String resultStr = "";
		try {
			System.out.println("EmpDaoImpl deptName deptno-> " + deptno);
			resultStr = session.selectOne("tkDeptName", deptno);
			System.out.println("EmpDaoImpl deptName resultStr-> " + resultStr);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl deptName Exception-> " +  e.getMessage());
		}
		return resultStr;
	}
	
	
	
	
	
	
	
	
	

	
	
	

}
