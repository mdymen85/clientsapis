package com.platformbuilder.clientsapis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ClientsapisApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientsapisApplication.class, args);
	}

}
