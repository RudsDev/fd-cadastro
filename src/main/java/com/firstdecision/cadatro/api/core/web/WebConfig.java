package com.firstdecision.cadatro.api.core.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
            String[] metodos = {"HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"};
            registry.addMapping("/**").allowedHeaders("*");
            registry.addMapping("/**").allowedMethods(metodos);
	}

}
