package com.nbank.domain.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Operation {
    @JsonProperty("operation")
    private Type type;
    @JsonProperty("unit-cost")
    private BigDecimal unitCost;
    private int quantity;
}
