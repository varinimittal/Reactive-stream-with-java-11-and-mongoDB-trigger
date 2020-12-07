package com.connect;




import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import com.mongodb.client.MongoClients;
import com.repository.StudentRepository;

@Configuration

@EnableReactiveMongoRepositories(basePackageClasses = StudentRepository.class)
@PropertySource("classpath:mongo.properties")
public abstract class MongoConfig extends AbstractReactiveMongoConfiguration {

  @Bean

  public com.mongodb.reactivestreams.client.MongoClient reactiveMongoClient() {
      return (com.mongodb.reactivestreams.client.MongoClient) MongoClients.create("mongodb://localhost");
  }
  @Override
  protected String getDatabaseName() {
    return "student";
  }


  @Bean

  public ReactiveMongoTemplate reactiveMongoTemplate() {

    return new ReactiveMongoTemplate((com.mongodb.reactivestreams.client.MongoClient) reactiveMongoClient(), getDatabaseName());

  }
  protected String getMappingBasePackage() {
	    return "StudentRepository.class";
	  }

}
