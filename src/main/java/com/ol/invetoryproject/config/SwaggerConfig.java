package com.ol.invetoryproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig 
{
	@Bean
	public Docket InvetoryApi() 
	{	
		return new Docket(DocumentationType.SWAGGER_2).select()
	            .apis(RequestHandlerSelectors
	                .basePackage("com.ol.invetoryproject.controllers"))
	            .paths(PathSelectors.regex("/api.*"))
	            .build().apiInfo(metaInfo()); 
	}

	private ApiInfo metaInfo() {

		return new ApiInfoBuilder().title("Invetory REST API")
	            .description("Invetory Management REST API using Spring Boot")
	            .contact(new Contact("Avi Yeshayahu", "https://github.com/Avi-Yes", "yeshayahu.avi@gmail.com"))
	            .license("Apache 2.0")
	            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
	            .version("1.0.0")
	            .build();
    }

}
