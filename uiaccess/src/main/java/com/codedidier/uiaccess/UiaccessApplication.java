package com.codedidier.uiaccess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class UiaccessApplication {

	public static void main(String[] args) {
		SpringApplication.run(UiaccessApplication.class, args);
	}

}
