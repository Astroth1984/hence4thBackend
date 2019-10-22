package com.internship.application;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import com.internship.application.storage.CsvToMongoJob;
import com.internship.application.storage.StorageService;
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class Stage2a1Application implements CommandLineRunner {

	@Resource
	StorageService storageService;
	@Autowired
	CsvToMongoJob csvToMongoJob;
	
	
	public static void main(String[] args) {
		SpringApplication.run(Stage2a1Application.class, args);
	}
	
	@Override
	  public void run(String... arg) throws Exception {
	    storageService.deleteAll();
	    storageService.init();
	    csvToMongoJob.readCSVFile();
	  }

}
