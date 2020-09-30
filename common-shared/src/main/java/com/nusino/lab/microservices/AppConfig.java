package com.nusino.lab.microservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.nusino.lab.microservices.config.common.AuditInterceptor;

@Configuration
public class AppConfig implements WebMvcConfigurer {
	@Autowired
	private AuditInterceptor auditInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		WebMvcConfigurer.super.addInterceptors(registry);
		registry.addInterceptor(auditInterceptor);
	}

}
