package br.com.alurafood.orders.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {
	
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

}
