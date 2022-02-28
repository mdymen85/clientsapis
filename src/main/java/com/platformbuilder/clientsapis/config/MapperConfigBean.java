package com.platformbuilder.clientsapis.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfigBean {

	@Bean
	public ModelMapper get() {
		return new ModelMapper();
	}
}
