package com.test.common.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
@EnableWebMvc
public class WebMvcConfig {
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("resx/**").addResourceLocations(
				"classpath:/resx/");
		log.debug("Added resource handlers");
	}

	public void configureMessageConverters(
			List<HttpMessageConverter<?>> converters) {
		converters.add(new MappingJackson2HttpMessageConverter());
	}

	public void configureContentNegotiation(
			ContentNegotiationConfigurer configurer) {
		configurer.mediaType("json", MediaType.APPLICATION_JSON)
				.mediaType("htm", MediaType.TEXT_HTML)
				.mediaType("html", MediaType.TEXT_HTML);
	}

	private final static Logger log = LoggerFactory
			.getLogger(WebMvcConfig.class);
}
