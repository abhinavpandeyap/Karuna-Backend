package com.app.karuna;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KarunaApplication {

	public static void main(String[] args) {
		SpringApplication.run(KarunaApplication.class, args);
	}
	
	// configure model mapper bean , with STRICT mapping instructions
		@Bean
		public ModelMapper mapper() {
			ModelMapper modelMapper = new ModelMapper();
			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			return modelMapper;
		}

}
