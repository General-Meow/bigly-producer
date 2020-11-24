package com.paulhoang.biglyproducer.data;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyPrice {

  private String code;
  private String name;
  private BigDecimal price;
}
