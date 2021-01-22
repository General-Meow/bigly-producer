package com.paulhoang.biglyproducer.service;

import com.paulhoang.biglyproducer.config.CompanyConfiguration;
import com.paulhoang.biglyproducer.data.CompanyPrice;
import com.paulhoang.biglyproducer.data.Operand;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ProducerService {

  private final GsonService gsonService;
  private final KafkaTemplate<String, String> kafkaTemplate;
  private CompanyConfiguration companyConfiguration;

  @Autowired
  public ProducerService(GsonService gsonService, KafkaTemplate<String, String> kafkaTemplate,
      CompanyConfiguration companyConfiguration) {
    this.gsonService = gsonService;
    this.kafkaTemplate = kafkaTemplate;
    this.companyConfiguration = companyConfiguration;
  }

  @Scheduled(initialDelay = 1000L, fixedRate = 1000L)
  public void triggerSchedule() {
    log.info("Sending data to Kafka!");

    updatePriceData(companyConfiguration.getCompanies());
    sendPriceDataToKafka(companyConfiguration.getCompanies());
  }

  private void updatePriceData(List<CompanyPrice> companies) {
    companies.forEach(c -> {
      c.setTime(Instant.now());
      BigDecimal amount = getUpdateAmount();
      Operand operand = chooseOperand();
      if (operand.equals(Operand.PLUS)) {
        c.setPrice(c.getPrice().add(amount).setScale(2, RoundingMode.HALF_UP));
      } else {
        c.setPrice(c.getPrice().subtract(amount).setScale(2, RoundingMode.HALF_UP));
      }
    });
  }

  protected Operand chooseOperand() {
    int randomNumber = getRandomNumberUpTo(1);
    return Operand.values()[randomNumber];
  }

  private BigDecimal getUpdateAmount() {
    return BigDecimal.valueOf(getRandomBigDecimalUpTo(0.20d));
  }

  protected int getRandomNumberUpTo(int max) {
    SecureRandom random = new SecureRandom();
    return random.nextInt(max + 1);
  }

  protected double getRandomBigDecimalUpTo(double max) {
    SecureRandom random = new SecureRandom();
    double randomDoubleUnder1 = random.nextDouble();
    double randomBigDecimalUpToMax = getRandomNumberUpTo(Double.valueOf(max).intValue());
    return randomBigDecimalUpToMax + randomDoubleUnder1;
  }

  private void sendPriceDataToKafka(List<CompanyPrice> companies) {
    companies.stream().forEach(c -> {
      String json = gsonService.toJson(c);
      kafkaTemplate.send("company_price", c.getCode(), json);
    });
  }
}
