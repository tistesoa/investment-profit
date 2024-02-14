package com.nbank.domain;

import com.nbank.domain.vo.Operation;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

public interface CalculateTax {

    List<BigDecimal> calculate(List<Operation> operationList);
}
