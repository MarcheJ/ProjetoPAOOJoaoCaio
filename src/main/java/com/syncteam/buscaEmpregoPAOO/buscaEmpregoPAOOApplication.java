package com.syncteam.buscaEmpregoPAOO;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class buscaEmpregoPAOOApplication {

	public static void main(String[] args) {
		SpringApplication.run(buscaEmpregoPAOOApplication.class, args);
	}

}
