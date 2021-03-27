package br.com.lukkascost.dataset.module;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;
import java.util.ArrayList;


@SpringBootApplication
@EnableEurekaClient
@EntityScan("br.com.lukkascost.commons.module.models.entities")
@ComponentScan("br.com.lukkascost")
@EnableSwagger2
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public Docket api() throws IOException {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.lukkascost.dataset.module.controllers"))
				.paths(PathSelectors.any())
				.build().apiInfo(
						new ApiInfo("Automatic Api-gateway doc", "Documentation automatically generated", "v1", null, new Contact("Lucas Costa", "https://github.com/lukkascost", "lucas.costa@outlook.com.br"), null, null, new ArrayList())) ;

	}
}
