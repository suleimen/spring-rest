package com.swat.config;

import javax.servlet.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.swat.filters.CORSFilter;

@Configuration
public class FilterConfig {

	@Bean
	public Filter CORSFilter() {
		return new CORSFilter();
	}
}