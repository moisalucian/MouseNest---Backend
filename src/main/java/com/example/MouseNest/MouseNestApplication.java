package com.example.MouseNest;

import com.example.MouseNest.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MouseNestApplication implements CommandLineRunner {

	@Autowired
	private MailService mailService;

	public static void main(String[] args) {
		SpringApplication.run(MouseNestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		mailService.sendEmail("moisamihailucian@gmail.com", "moisamihailucian@gmail.com", "Test12345", "Salut, Lucian!");
	}



}
