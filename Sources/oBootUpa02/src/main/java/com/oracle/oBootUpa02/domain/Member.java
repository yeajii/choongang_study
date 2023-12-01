package com.oracle.oBootUpa02.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Data
@SequenceGenerator(name         = "member_seq_gen",        // 객체 seq명
				   sequenceName = "member_seq_generator",  // DB seq명
				   initialValue = 1,
				   allocationSize = 1
				   )
@Table(name = "member2")
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, 
					generator = "member_seq_gen"
					)
	@Column(name = "member_id") // 여기서 설정 안하면  max으로 세팅된다 
	private Long   id;
	
	@Column(name = "username", length = 100)  // number가 아닌 것은 size 먹는다
	private String name;
	
	private Long sal;
	
	// 관계 설정 = fk를 걸 곳에서 설정
	@ManyToOne  // 1:다 에서 다의 해당하는 곳에 설정(1=team : 다=member2)
	@JoinColumn(name = "team_id")  // team의 pk를 연관관계로 설정, DB member2에 team_id 컬럼이 없는 상태면 자동으로 만들어진다  
	private Team team;  		   // 테이블이 객체가 됨 
	
	
	// 테이블에 컬럼을 만들지 않고 참조용으로 사용 (참조)  
	@Transient
	private String teamname; 
	
	// 조회용(참조) 실제 컬럼 X --> Buffer 용도
	@Transient
	private Long teamid;
	
	
	
	
	
}
