package com.springboot.inventoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.springboot.inventoryservice.repository")
@EntityScan("com.springboot.inventoryservice.model")
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

}
