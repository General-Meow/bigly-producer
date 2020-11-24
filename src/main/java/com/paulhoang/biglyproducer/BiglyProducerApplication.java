package com.paulhoang.biglyproducer;

import com.paulhoang.biglyproducer.config.CompanyConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableKafka
@EnableConfigurationProperties(value = CompanyConfiguration.class)
public class BiglyProducerApplication {

  public static void main(String[] args) {
    SpringApplication.run(BiglyProducerApplication.class, args);
  }

}
