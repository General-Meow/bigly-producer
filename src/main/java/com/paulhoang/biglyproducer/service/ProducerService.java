package com.paulhoang.biglyproducer.service;

import com.google.gson.Gson;
import com.paulhoang.biglyproducer.config.CompanyConfiguration;
import com.paulhoang.biglyproducer.data.CompanyPrice;
import com.paulhoang.biglyproducer.data.Operand;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

  private final GsonService gsonService;
  private final KafkaTemplate<String, String> kafkaTemplate;
  private final List<CompanyPrice> companies;

  @Autowired
  public ProducerService(GsonService gsonService, KafkaTemplate<String, String> kafkaTemplate,
      CompanyConfiguration companyConfiguration) {
    this.gsonService = gsonService;
    this.kafkaTemplate = kafkaTemplate;
    this.companies = companyConfiguration.getCompanies()  ;
  }

  @Scheduled(initialDelay = 1000L, fixedRate = 1000L)
  public void triggerSchedule() {
    System.out.println("hello!");

    fluxPriceData(companies);
    sendPriceDataToKafka(companies);
  }

  private void fluxPriceData(List<CompanyPrice> companies) {
    companies.forEach(c -> {
      BigDecimal amount = getFluxAmount();
      Operand operand = chooseOperand();
      switch (operand){
        case PLUS:
          c.setPrice(c.getPrice().add(amount));
        case MINUS:
          c.setPrice(c.getPrice().subtract(amount));
      }
      //round
      c.setPrice(c.getPrice().setScale(2, RoundingMode.HALF_UP));
    });
  }

  protected Operand chooseOperand() {
    int randomNumber = getRandomNumberUpTo(1);
    return Operand.values()[randomNumber];
  }

  private BigDecimal getFluxAmount() {
    return new BigDecimal(getRandomBigDecimalUpTo(0.20d));
  }

  protected int getRandomNumberUpTo(int max){
    return ThreadLocalRandom.current().nextInt(0, max + 1);
  }

  protected double getRandomBigDecimalUpTo(double max){
    return ThreadLocalRandom.current().nextDouble(0.0d, max);
  }

  private void sendPriceDataToKafka(List<CompanyPrice> companies) {
    companies.stream().forEach(c -> {
      String json = gsonService.toJson(c);
      kafkaTemplate.send("company_price", c.getCode(), json);
    });
  }
}
