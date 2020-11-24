package com.paulhoang.biglyproducer.config;

import com.paulhoang.biglyproducer.data.CompanyPrice;
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public class CompanyConfiguration {

  private List<CompanyPrice> companies;

  public List<CompanyPrice> getCompanies() {
    return companies;
  }

  public void setCompanies(List<CompanyPrice> companies) {
    this.companies = companies;
  }
}
