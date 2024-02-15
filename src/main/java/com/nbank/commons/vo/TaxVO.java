package com.nbank.commons.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaxVO {
  @JsonProperty("tax")
  private BigDecimal value;
}
