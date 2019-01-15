package com.chatapp;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@SuppressWarnings("deprecation")
@EnableSwagger2
@SpringBootApplication

public class ChatappApplication  extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(ChatappApplication.class, args);
		
	}
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new com.chatapp.util.LoggingInterceptor()).addPathPatterns("/**");
	}

	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage(getClass().getPackage().getName())).paths(PathSelectors.any())
				.build().apiInfo(generateApiInfo());
	}
	
	
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	private ApiInfo generateApiInfo() {
		return new ApiInfo("Live Mall",
				"* PLease provide lang parameter with each request in the url for internationalization e.g. ?lang=en, ?lang=fr",
				"Version 1.0", "urn:tos", "live mall", "Apache 2.0",
				"http://www.apache.org/licenses/LICENSE-2.0");
	}
}
