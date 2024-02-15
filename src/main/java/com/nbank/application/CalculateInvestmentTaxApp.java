package com.nbank.application;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nbank.domain.CalculateTax;
import com.nbank.domain.impl.CalculateInvestmentTaxImpl;
import com.nbank.commons.vo.OperationVO;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class CalculateInvestmentTaxApp {

  public static final BigDecimal TAX_RATE = BigDecimal.valueOf(0.20);
  public static final BigDecimal MIN_PROFIT = new BigDecimal("20000");

  public static void main(String[] args) throws JsonProcessingException {
    CalculateTax calculateTax = new CalculateInvestmentTaxImpl(TAX_RATE, MIN_PROFIT);
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      if (line.isEmpty()) {
        break;
      }
      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
      List<OperationVO> operationVOS = objectMapper.readValue(line, new TypeReference<>() {
      });
      var results = calculateTax.calculate(operationVOS);
      var restJson = objectMapper.writeValueAsString(results);
      System.out.println(restJson);

    }
    scanner.close();
  }
}