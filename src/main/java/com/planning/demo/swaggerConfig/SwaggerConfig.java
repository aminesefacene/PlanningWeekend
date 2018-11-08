package com.planning.demo.swaggerConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import springfox.documentation.builders.PathSelectors;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	  
	@Bean
	public Docket productApi() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.planning.demo"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(metaInfo());
		
	}

	private ApiInfo metaInfo() {
		
		
		@SuppressWarnings("deprecation")
		ApiInfo apiInfo = new ApiInfo(
				"Weekend plannification API", 
				"An API which help you to plannify your weekend by offering sports activities", 
				"1.0", 
				"Terms of service",
				"SEFACENE-MIOLA, laminesefacene@gmail.com",
				"license", 
				"licenseUrl");
		return apiInfo;
	}
}
