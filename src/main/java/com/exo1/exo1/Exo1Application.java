package com.exo1.exo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

//Exerice 2 -> cache
@EnableCaching
@SpringBootApplication
public class Exo1Application {

	public static void main(String[] args) {
		SpringApplication.run(Exo1Application.class, args);
	}

}
