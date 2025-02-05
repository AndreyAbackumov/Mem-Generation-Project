package com.example.pet_project_mem_generation;

import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
}
