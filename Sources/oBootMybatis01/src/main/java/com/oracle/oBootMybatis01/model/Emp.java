package com.oracle.oBootMybatis01.model;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class Emp {
	private int empno;
	@NotEmpty(message = "이름은 필수입니다")  // @NotEmpty = Validation에서 나온 것
	private String ename;
	private String job;
	private int mgr;
	private String hiredate;
	private int sal;
	private int comm;
	private int deptno;
	
	// 조회용 (테이블에 없다)
	private String search;   private String keyword;
	private String pageNum;  
	private int start;		 private int end;       // start와 end는 Paging 작업을 위함 
	
	

}
