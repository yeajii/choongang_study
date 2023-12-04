package com.oracle.oBootMybatis01.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.oracle.oBootMybatis01.service.SampleInterceptor;

@Configuration   // 환경 설정이라서 걸어주고, bean 안 걸어줘도 자동으로 됨 
public class WebMvcConfiguration implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 누군가 url /interCeptor를 친다면 --> SampleInterceptor() 처리 해줌 
		registry.addInterceptor(new SampleInterceptor()).addPathPatterns("/interCeptor")
		;
	}

}
