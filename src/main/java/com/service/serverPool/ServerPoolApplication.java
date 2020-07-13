package com.service.serverPool;

import com.service.serverPool.service.ServiceProvider;
import com.service.serverPool.util.config.PoolExecuter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class ServerPoolApplication {

	public static void main(String[] args) {
		 int MAX_T = 10 ;
		 ExecutorService pool = Executors.newFixedThreadPool(MAX_T);
		SpringApplication.run(ServerPoolApplication.class, args);
		pool.execute(new ServiceProvider(30));
		pool.execute(new ServiceProvider(50));
		pool.execute(new ServiceProvider(30));
		//pool.execute(new ServiceProvider(40));


	}

}
