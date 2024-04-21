package com.harjoitustyo.movieapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
	"com.harjoitustyo.movieapp", 
	"com.harjoitustyo.movieapp.config", 
	"com.harjoitustyo.movieapp.controller", 
	"com.harjoitustyo.movieapp.domain", 
	"com.harjoitustyo.movieapp.repository", 
	"com.harjoitustyo.movieapp.service"
  })
public class MovieappApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieappApplication.class, args);
	}

}
