package com.oracle.oBootDBConnect;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.oracle.oBootDBConnect.repository.JdbcMemberRepository;
import com.oracle.oBootDBConnect.repository.MemberRepository;
import com.oracle.oBootDBConnect.repository.MemoryMemberRepository;

// 환경 설정
@Configuration
public class SpringConfig {
	private final DataSource dataSource;
	
	@Autowired 
	public SpringConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Bean
	public MemberRepository memberRepository() {
//		return new JdbcMemberRepository(dataSource);
		return new MemoryMemberRepository(); 
	}
	

}
