package com.service.serverPool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class ServerPoolApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(ServerPoolApplication.class, args) ;
	}

}
