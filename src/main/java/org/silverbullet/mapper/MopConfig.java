package org.silverbullet.mapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MopConfig {

	@Bean
	MopMapper mopMapper() {
		return new MopMapper();
	}
	
	@Bean
	NodeMapper nodeMapper() {
		return new NodeMapper();
	}
}
