package br.com.lukkascost.attributes.module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("br.com.lukkascost.commons.module.models.entities")
@ComponentScan("br.com.lukkascost")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
