package com.bananabot.bananabot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication
@PropertySource(value = "classpath:application.yml")
public class BananabotApplication {

    public static void main(String[] args) {
		SpringApplication.run(BananabotApplication.class, args);
	}

}
