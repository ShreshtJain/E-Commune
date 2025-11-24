package com.ecommune.gn.api.ecommune.gn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		LOGGER.info("|-------------------------------------------|");
		LOGGER.info("|     E-Commune Server started     		 |");
		LOGGER.info("|        Bienvenue, welcome                 |");
		LOGGER.info("|-------------------------------------------|");
	}

}
