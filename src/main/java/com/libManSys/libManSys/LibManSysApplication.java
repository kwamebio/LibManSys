package com.libManSys.libManSys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibManSysApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibManSysApplication.class, args);
	}

	public static void printMe(){
		System.out.println("No I won't");
	}

}
