package br.com.accenture.apipessoa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableCaching
public class ApipessoaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApipessoaApplication.class, args);
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

}
