package com.mylearning;

import com.mylearning.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootManyToOneApplication implements CommandLineRunner {

	@Autowired
	private OrderService orderService;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootManyToOneApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		orderService.createOrder();

	}
}
