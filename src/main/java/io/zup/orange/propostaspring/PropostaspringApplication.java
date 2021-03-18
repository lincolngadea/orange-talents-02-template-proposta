package io.zup.orange.propostaspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@EnableFeignClients
public class PropostaspringApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropostaspringApplication.class, args);
	}

}
