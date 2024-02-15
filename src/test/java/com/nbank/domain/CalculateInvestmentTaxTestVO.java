package com.nbank.domain;

import static com.nbank.application.CalculateInvestmentTaxApp.MIN_PROFIT;
import static com.nbank.application.CalculateInvestmentTaxApp.TAX_RATE;
import static com.nbank.domain.impl.CalculateInvestmentTaxImpl.NO_TAX_VALUE;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import com.nbank.domain.impl.CalculateInvestmentTaxImpl;
import com.nbank.commons.vo.OperationVO;
import com.nbank.commons.vo.TaxVO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class CalculateInvestmentTaxTestVO {

  CalculateTax calculateInvestmentTax;

  @Test
  public void shouldRunCase1Successfully() {
    calculateInvestmentTax = new CalculateInvestmentTaxImpl(TAX_RATE, MIN_PROFIT);
    List<TaxVO> expected = new ArrayList<>();
    expected.add(NO_TAX_VALUE);
    expected.add(NO_TAX_VALUE);
    expected.add(NO_TAX_VALUE);

    List<OperationVO> input = new ArrayList<>();
    input.add(new OperationVO(OperationType.BUY, new BigDecimal("10.00"), 100));
    input.add(new OperationVO(OperationType.SELL, new BigDecimal("15.00"), 50));
    input.add(new OperationVO(OperationType.SELL, new BigDecimal("15.00"), 50));
    var result = calculateInvestmentTax.calculate(input);

    assertIterableEquals(expected, result);
  }

  @Test
  public void shouldRunCase2Successfully() {
    calculateInvestmentTax = new CalculateInvestmentTaxImpl(TAX_RATE, MIN_PROFIT);
    List<TaxVO> expected = new ArrayList<>();
    expected.add(NO_TAX_VALUE);
    expected.add(new TaxVO(new BigDecimal("10000.00")));
    expected.add(NO_TAX_VALUE);

    List<OperationVO> input = new ArrayList<>();
    input.add(new OperationVO(OperationType.BUY, new BigDecimal("10.00"), 10000));
    input.add(new OperationVO(OperationType.SELL, new BigDecimal("20.00"), 5000));
    input.add(new OperationVO(OperationType.SELL, new BigDecimal("5.00"), 5000));
    var result = calculateInvestmentTax.calculate(input);
    assertIterableEquals(expected, result);
  }

  @Test
  public void shouldRunCase3Successfully() {
    calculateInvestmentTax = new CalculateInvestmentTaxImpl(TAX_RATE, MIN_PROFIT);
    List<TaxVO> expected = new ArrayList<>();
    expected.add(NO_TAX_VALUE);
    expected.add(NO_TAX_VALUE);
    expected.add(new TaxVO(new BigDecimal("1000.00")));

    List<OperationVO> input = new ArrayList<>();
    input.add(new OperationVO(OperationType.BUY, new BigDecimal("10.00"), 10000));
    input.add(new OperationVO(OperationType.SELL, new BigDecimal("5.00"), 5000));
    input.add(new OperationVO(OperationType.SELL, new BigDecimal("20.00"), 3000));
    var result = calculateInvestmentTax.calculate(input);
    assertIterableEquals(expected, result);
  }

  @Test
  public void shouldRunCase4Successfully() {
    calculateInvestmentTax = new CalculateInvestmentTaxImpl(TAX_RATE, MIN_PROFIT);
    List<TaxVO> expected = new ArrayList<>();
    expected.add(NO_TAX_VALUE);
    expected.add(NO_TAX_VALUE);
    expected.add(NO_TAX_VALUE);

    List<OperationVO> input = new ArrayList<>();
    input.add(new OperationVO(OperationType.BUY, new BigDecimal("10.00"), 10000));
    input.add(new OperationVO(OperationType.BUY, new BigDecimal("25.00"), 5000));
    input.add(new OperationVO(OperationType.SELL, new BigDecimal("15.00"), 10000));
    var result = calculateInvestmentTax.calculate(input);
    assertIterableEquals(expected, result);
  }

  @Test
  public void shouldRunCase5Successfully() {
    calculateInvestmentTax = new CalculateInvestmentTaxImpl(TAX_RATE, MIN_PROFIT);
    List<TaxVO> expected = new ArrayList<>();
    expected.add(NO_TAX_VALUE);
    expected.add(NO_TAX_VALUE);
    expected.add(NO_TAX_VALUE);
    expected.add(new TaxVO(new BigDecimal("10000.00")));
    List<OperationVO> input = new ArrayList<>();
    input.add(new OperationVO(OperationType.BUY, new BigDecimal("10.00"), 10000));
    input.add(new OperationVO(OperationType.BUY, new BigDecimal("25.00"), 5000));
    input.add(new OperationVO(OperationType.SELL, new BigDecimal("15.00"), 10000));
    input.add(new OperationVO(OperationType.SELL, new BigDecimal("25.00"), 5000));
    var result = calculateInvestmentTax.calculate(input);
    assertIterableEquals(expected, result);
  }

  @Test
  public void shouldRunCase6Successfully() {
    calculateInvestmentTax = new CalculateInvestmentTaxImpl(TAX_RATE, MIN_PROFIT);
    List<TaxVO> expected = new ArrayList<>();
    expected.add(NO_TAX_VALUE);
    expected.add(NO_TAX_VALUE);
    expected.add(NO_TAX_VALUE);
    expected.add(NO_TAX_VALUE);
    expected.add(new TaxVO(new BigDecimal("3000.00")));
    List<OperationVO> input = new ArrayList<>();
    input.add(new OperationVO(OperationType.BUY, new BigDecimal("10.00"), 10000));
    input.add(new OperationVO(OperationType.SELL, new BigDecimal("2.00"), 5000));
    input.add(new OperationVO(OperationType.SELL, new BigDecimal("20.00"), 2000));
    input.add(new OperationVO(OperationType.SELL, new BigDecimal("20.00"), 2000));
    input.add(new OperationVO(OperationType.SELL, new BigDecimal("25.00"), 1000));
    var result = calculateInvestmentTax.calculate(input);
    assertIterableEquals(expected, result);
  }


  @Test
  public void shouldRunCase7Successfully() {
    calculateInvestmentTax = new CalculateInvestmentTaxImpl(TAX_RATE, MIN_PROFIT);
    List<TaxVO> expected = new ArrayList<>();
    expected.add(NO_TAX_VALUE);
    expected.add(NO_TAX_VALUE);
    expected.add(NO_TAX_VALUE);
    expected.add(NO_TAX_VALUE);
    expected.add(new TaxVO(new BigDecimal("3000.00")));
    expected.add(NO_TAX_VALUE);
    expected.add(NO_TAX_VALUE);
    expected.add(new TaxVO(new BigDecimal("3700.00")));
    expected.add(NO_TAX_VALUE);

    List<OperationVO> input = new ArrayList<>();
    input.add(new OperationVO(OperationType.BUY, new BigDecimal("10.00"), 10000));
    input.add(new OperationVO(OperationType.SELL, new BigDecimal("2.00"), 5000));
    input.add(new OperationVO(OperationType.SELL, new BigDecimal("20.00"), 2000));
    input.add(new OperationVO(OperationType.SELL, new BigDecimal("20.00"), 2000));
    input.add(new OperationVO(OperationType.SELL, new BigDecimal("25.00"), 1000));
    input.add(new OperationVO(OperationType.BUY, new BigDecimal("20.00"), 10000));
    input.add(new OperationVO(OperationType.SELL, new BigDecimal("15.00"), 5000));
    input.add(new OperationVO(OperationType.SELL, new BigDecimal("30.00"), 4350));
    input.add(new OperationVO(OperationType.SELL, new BigDecimal("30.00"), 650));
    var result = calculateInvestmentTax.calculate(input);
    assertIterableEquals(expected, result);
  }


  @Test
  public void shouldRunCase8Successfully() {
    calculateInvestmentTax = new CalculateInvestmentTaxImpl(TAX_RATE, MIN_PROFIT);
    List<TaxVO> expected = new ArrayList<>();
    expected.add(NO_TAX_VALUE);
    expected.add(new TaxVO(new BigDecimal("80000.00")));
    expected.add(NO_TAX_VALUE);
    expected.add(new TaxVO(new BigDecimal("60000.00")));
    List<OperationVO> input = new ArrayList<>();
    input.add(new OperationVO(OperationType.BUY, new BigDecimal("10.00"), 10000));
    input.add(new OperationVO(OperationType.SELL, new BigDecimal("50.00"), 10000));
    input.add(new OperationVO(OperationType.BUY, new BigDecimal("20.00"), 10000));
    input.add(new OperationVO(OperationType.SELL, new BigDecimal("50.00"), 10000));
    var result = calculateInvestmentTax.calculate(input);
    assertIterableEquals(expected, result);
  }
}
