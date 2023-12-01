package com.oracle.oBootUpaApi01.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Data
@SequenceGenerator(
			name = "member_seq_gen5",
			sequenceName = "member_seq_generator5", // DB
			initialValue = 1,
			allocationSize = 1
		)
@Table(name = "member5")
public class Member {
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "member_seq_gen5"
			)
	@Column(name = "member_id")
	private Long id;
	
	@NotEmpty  // not null, 논리적 O, 물리적 X
	@Column(name = "userName")
	private String name;
	
	private Long sal;
	
	// 관계 매핑 : fk거는 테이블에 한다 
//	@ManyToOne
	@ManyToOne(fetch = FetchType.LAZY)  // 디폴트는 EAGER / FetchType.LAZY: 전문가 권장 
	@JoinColumn(name = "team_id")
	private Team team;
	

}
