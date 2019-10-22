package com.internship.application.storage;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.internship.application.model.Domain;


@Configuration
public class CsvToMongoJob {
  public static String file="test.csv";
  @Autowired
  private JobBuilderFactory jobBuilderFactory;
  @Autowired
  private StepBuilderFactory stepBuilderFactory;
 
  @Autowired
  private MongoTemplate mongoTemplate;
 
  @Bean
  public Job readCSVFile() {
    return jobBuilderFactory.get("readCSVFile").incrementer(new RunIdIncrementer()).start(step1())
        .build();
  }
 
  @Bean
  public Step step1() {
    return stepBuilderFactory.get("step1").<Domain, Domain>chunk(10).reader(reader())
        .writer(writer()).build();
  }
 
  @Bean
  public FlatFileItemReader<Domain> reader() {
	  
	System.out.println("444444444444444"+file);
    FlatFileItemReader<Domain> reader = new FlatFileItemReader<>();
    reader.setResource(new ClassPathResource(file));
    reader.setLineMapper(new DefaultLineMapper<Domain>() {{
      setLineTokenizer(new DelimitedLineTokenizer() {{
        setNames(new String[]{"id","name"});
 
      }});
      setFieldSetMapper(new BeanWrapperFieldSetMapper<Domain>() {{
        setTargetType(Domain.class);
      }});
    }});
    return reader;
  }
 
  @Bean
  public MongoItemWriter<Domain> writer() {
    MongoItemWriter<Domain> writer = new MongoItemWriter<Domain>();
    writer.setTemplate(mongoTemplate);
    writer.setCollection("domain");
    return writer;
  }
}

  