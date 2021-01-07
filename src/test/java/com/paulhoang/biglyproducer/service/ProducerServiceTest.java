package com.paulhoang.biglyproducer.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.paulhoang.biglyproducer.config.CompanyConfiguration;
import com.paulhoang.biglyproducer.data.CompanyPrice;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
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
  private KafkaTemplate<String, String> kafkaTemplateMock;
  @Mock
  private CompanyConfiguration companyConfigurationMock;
  @Mock
  private CompanyPrice firstCompanyMock, secondCompanyMock;
  @InjectMocks
  private ProducerService testObj;

  @BeforeEach
  void setup() {
  }

  @Test
  void triggerScheduleShouldUpdatePriceDataAndSendPricesToKafka() {
    when(firstCompanyMock.getCode()).thenReturn("first");
    when(firstCompanyMock.getPrice()).thenReturn(BigDecimal.ZERO);
    when(secondCompanyMock.getCode()).thenReturn("second");
    when(secondCompanyMock.getPrice()).thenReturn(new BigDecimal("1.00"));

    List<CompanyPrice> companies = List.of(firstCompanyMock, secondCompanyMock);
    when(companyConfigurationMock.getCompanies()).thenReturn(companies);

    when(gsonServiceMock.toJson(firstCompanyMock)).thenReturn("first json");
    when(gsonServiceMock.toJson(secondCompanyMock)).thenReturn("second json");

    testObj.triggerSchedule();

    verify(firstCompanyMock).setPrice(any(BigDecimal.class));
    verify(secondCompanyMock).setPrice(any(BigDecimal.class));
    verify(kafkaTemplateMock).send("company_price", "first", "first json");
    verify(kafkaTemplateMock).send("company_price", "second", "second json");
  }
}