package com.paulhoang.biglyproducer.config;

import java.util.Map;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaConfiguration {

  @Bean
  public KafkaTemplate<String, String> kafkaTemplate(KafkaProperties kafkaProperties) {
    Map<String, Object> producerProperties = kafkaProperties.buildProducerProperties();
    ProducerFactory<String, String> producerFactory = new DefaultKafkaProducerFactory<>(
        producerProperties);

    return new KafkaTemplate<>(producerFactory);
  }


}
