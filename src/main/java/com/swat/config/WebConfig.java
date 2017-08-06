package com.swat.config;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swat.util.Constants;

import antlr.collections.List;


@Configuration
@EnableWebMvc
@ComponentScan("com.swat")
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void configureMessageConverters(java.util.List<HttpMessageConverter<?>> converters) {
		converters.add(converter());
		super.configureMessageConverters(converters);
	}

	@Bean
	MappingJackson2HttpMessageConverter converter() {

		ObjectMapper objectMapper = new ObjectMapper();
		DateFormat dateFormat = new SimpleDateFormat(Constants.Date.DATETIME_FORMAT_STRING);
		dateFormat.setTimeZone(TimeZone.getTimeZone(Constants.Date.DATE_TIMEZONE));
		objectMapper.setDateFormat(dateFormat);

		return new MappingJackson2HttpMessageConverter(objectMapper);
	}

}
