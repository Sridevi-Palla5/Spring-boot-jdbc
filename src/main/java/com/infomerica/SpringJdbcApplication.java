package com.infomerica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class SpringJdbcApplication {

	public static void main(String[] args) {
		log.info("SpringJdbcApplication Started ................................");
		SpringApplication.run(SpringJdbcApplication.class, args);
	}

}
