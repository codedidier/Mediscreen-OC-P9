package com.codedidier.msassess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@EnableFeignClients
@SpringBootApplication
public class MsassessApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsassessApplication.class, args);
	}

}
