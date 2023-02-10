package com.tdd.backend.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.tdd.backend.auth.AuthResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	// @Override
	// public void addCorsMappings(CorsRegistry registry) {
	// 	registry.addMapping("/**")
	// 		.allowedOriginPatterns("http://letsTdd.site")
	// 		.allowedMethods("*")
	// 		.allowCredentials(true)
	// 		.maxAge(3000);
	// }

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new AuthResolver());
	}

}
