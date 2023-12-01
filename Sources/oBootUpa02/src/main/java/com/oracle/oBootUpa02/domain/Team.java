package com.oracle.oBootUpa02.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Entity
@Data
@SequenceGenerator(
					name         = "team_seq_gen",  	// 객체 seq명             
					sequenceName = "team_seq_generator", // 매핑할 DB 시퀸스 이름
					initialValue = 1,
					allocationSize = 1
				   )
public class Team {  // @team이름 안 만들면 객체 이름으로 들어감 
	@Id
	@GeneratedValue(
					strategy = GenerationType.SEQUENCE,
					generator = "team_seq_gen"
				   )
	private Long team_id;
	
	@Column(name = "teamname")  
	private String name;
	
	
}
