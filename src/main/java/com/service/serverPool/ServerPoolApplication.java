package com.service.serverPool;

import com.service.serverPool.service.ServerService;
import com.service.serverPool.service.ServerServiceImpl;
import com.service.serverPool.shared.dto.ServerDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class ServerPoolApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(ServerPoolApplication.class, args) ;
		ServerService lServerService =new ServerServiceImpl();
		ServerDto lServerDto=((ServerServiceImpl) lServerService).getAvailableNonLockedServer(10);
		System.out.println(lServerDto);
	}

}
