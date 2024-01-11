package br.com.accenture.apipessoa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ApipessoaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApipessoaApplication.class, args);
	}

}
