package com.internship.application.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.internship.application.model.Domain;
import com.internship.application.storage.CsvToMongoJob;
import com.internship.application.storage.StorageService;

import com.internship.application.storage.*;



@Controller
public class UploadController {
 
  @Autowired
  StorageService storageService;

  @Autowired
  private JobBuilderFactory jobBuilderFactory;
  @Autowired
  private StepBuilderFactory stepBuilderFactory;
  @Autowired
  private ApplicationContext context;
  @Autowired
  CsvToMongoJob csvToMongoJob;
  
  public static String fileName;
  List<String> files = new ArrayList<String>();
  //public static String fileName;
 
  @PostMapping("/post")
  public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
    String message = "";
   fileName=file.getOriginalFilename();
    try {
    	storageService.store(file);
	    files.add(file.getOriginalFilename());
    	System.out.println("333333333333333333333333333333333333333333333333"+fileName);
    	CsvToMongoJob.file=fileName;
    	context.getBean(CsvToMongoJob.class);    	
    	System.out.println("333333333333333333333333333333333333333333333333"+fileName);
	    
	    
 
      message = "You successfully uploaded " + file.getOriginalFilename() + "!";
      return ResponseEntity.status(HttpStatus.OK).body(message);
    } catch (Exception e) {
      message = "FAIL to upload " + file.getOriginalFilename() + "!";
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
    }
  }
 
  @GetMapping("/getallfiles")
  public ResponseEntity<List<String>> getListFiles(Model model) {
    List<String> fileNames = files
        .stream().map(fileName -> MvcUriComponentsBuilder
            .fromMethodName(UploadController.class, "getFile", fileName).build().toString())
        .collect(Collectors.toList());
 
    return ResponseEntity.ok().body(fileNames);
  }
 
  @GetMapping("/files/{filename:.+}")
  @ResponseBody
  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
    Resource file = storageService.loadFile(filename);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
        .body(file);
  }
}
