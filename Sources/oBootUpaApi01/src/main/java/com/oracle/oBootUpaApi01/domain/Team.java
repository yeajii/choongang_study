package com.oracle.oBootUpaApi01.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity  // @Entity = 논리모델링 , DB table = 물리모델링 
@Data
@Table(name = "team5")
@SequenceGenerator(
			name = "team_seq_gen5",
			sequenceName = "team_seq_generator5",  // DB
			initialValue = 1,
			allocationSize = 1
		)
public class Team {
	@Id
	@GeneratedValue(
				strategy = GenerationType.SEQUENCE,
				generator = "team_seq_gen5"
			)
	private Long teamId;  // DB table에는 team_id로 되어 있다 -> 카넬표기법은 밑줄로 표기 
	
	@Column(name = "teamname", length = 80)
	private String name;

}
