package com.paulhoang.biglyproducer.service;

import static org.junit.jupiter.api.Assertions.*;

import com.google.gson.Gson;
import com.paulhoang.biglyproducer.config.CompanyConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

@ExtendWith(MockitoExtension.class)
class ProducerServiceTest {

  @Mock
  private GsonService gsonServiceMock;
  @Mock
  private KafkaTemplate kafkaTemplateMock;
  @Mock
  private CompanyConfiguration companyConfigurationMock;

  @InjectMocks
  private ProducerService testObj;

  @Test
  void blah() {
    for (int i = 0; i < 10; i++) {
      System.out.println(testObj.getRandomBigDecimalUpTo(1.0d));
    }
  }

  @Test
  void chooseOperand() {
    for (int i = 0; i < 10; i++) {
      System.out.println(testObj.getRandomNumberUpTo(1));
    }
  }
}