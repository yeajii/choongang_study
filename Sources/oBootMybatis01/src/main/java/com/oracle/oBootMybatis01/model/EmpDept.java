package com.oracle.oBootMybatis01.model;

import lombok.Data;

// Emp와 Dept의 컬럼이 10개씩(많다)이라는 대등한 조건이라고 가정하면 따로 dto 만들면 된다 
// 따로 안 만들면 테이블이 많아지므로 9:38 

// join 목적
@Data
public class EmpDept {
	// Emp용
	private int empno;         private String ename;
	private String job;        private int mgr;
	private String hiredate;   private int sal;
	private int comm;          private int deptno;
	
	// Dept용 (Dept가 많다는 가정)
	private String dname;
	private String loc;

}
