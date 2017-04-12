package com.springframework.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggerConfig {

	private String className = "";

	public LoggerConfig(String className) {
		super();
		this.className = className;
	}

	@Bean("logger")
	public Logger getLogger() {
		Logger logger = LogManager.getLogger(this.className);

		return logger;
	}
	
	
	
}
