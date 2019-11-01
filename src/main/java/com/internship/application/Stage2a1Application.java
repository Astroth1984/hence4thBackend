package com.internship.application;




import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import property.FileStorageProperties;



@SpringBootApplication
@EnableConfigurationProperties({
	FileStorageProperties.class
})
public class Stage2a1Application {

	
	
	
	public static void main(String[] args) {
		SpringApplication.run(Stage2a1Application.class, args);
	}
	
	
}
