package com.infnet.companyApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.infnet.companyApi.domain")
@EnableJpaRepositories("com.infnet.companyApi.repository")
public class CompanyApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(CompanyApiApplication.class, args);
		System.out.println("Api is on");

	}

}
