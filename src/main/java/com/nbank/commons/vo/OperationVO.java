package com.nbank.commons.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nbank.domain.OperationType;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationVO {

  @JsonProperty("operation")
  private OperationType operationType;
  @JsonProperty("unit-cost")
  private BigDecimal unitCost;
  private int quantity;
}
