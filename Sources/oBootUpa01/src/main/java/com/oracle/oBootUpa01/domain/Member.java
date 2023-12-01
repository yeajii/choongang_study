package com.oracle.oBootUpa01.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// jpa   5:13
@Entity
// @Getter
// @Setter
// @ToString
@Data
@Table(name = "member1")
public class Member {
	
	@Id  // pk의미
	private Long   id;
	private String name;

//	@Override
//	public String toString() {
//		String returnStr = "";
//		returnStr = "[id:" + this.id + ", name:" + this.name + "]";  // ex) [id:1001, name:연개소문] toString()메소드 사용해서 
//		return returnStr;
//	}
	
	
	
	
}
