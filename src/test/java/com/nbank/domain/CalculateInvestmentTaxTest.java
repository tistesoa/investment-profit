package com.nbank.domain;

import com.nbank.domain.impl.CalculateInvestmentTaxImpl;
import com.nbank.domain.vo.Operation;
import com.nbank.domain.vo.Type;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class CalculateInvestmentTaxTest {

    CalculateTax calculateInvestmentTax;

    @Test
    public void case1() {
        calculateInvestmentTax = new CalculateInvestmentTaxImpl();
        List<BigDecimal> expected = new ArrayList<>();
        expected.add(BigDecimal.ZERO);
        expected.add(BigDecimal.ZERO);
        expected.add(BigDecimal.ZERO);

        List<Operation> input = new ArrayList<>();
        input.add(new Operation(Type.BUY,new BigDecimal("10.00"),100));
        input.add(new Operation(Type.SELL,new BigDecimal("15.00"),50));
        input.add(new Operation(Type.SELL,new BigDecimal("15.00"),50));
        var result = calculateInvestmentTax.calculate(input);

        assertIterableEquals(expected, result);
    }
    @Test
    public void case2() {
        calculateInvestmentTax = new CalculateInvestmentTaxImpl();
        List<BigDecimal> expected = new ArrayList<>();
        expected.add(BigDecimal.ZERO);
        expected.add(new BigDecimal("10000.00"));
        expected.add(BigDecimal.ZERO);

        List<Operation> input = new ArrayList<>();
        input.add(new Operation(Type.BUY,new BigDecimal("10.00"),10000));
        input.add(new Operation(Type.SELL,new BigDecimal("20.00"),5000));
        input.add(new Operation(Type.SELL,new BigDecimal("5.00"),5000));
        var result = calculateInvestmentTax.calculate(input);
        assertIterableEquals(expected, result);
    }

    @Test
    public void case3() {
        calculateInvestmentTax = new CalculateInvestmentTaxImpl();
        List<BigDecimal> expected = new ArrayList<>();
        expected.add(BigDecimal.ZERO);
        expected.add(BigDecimal.ZERO);
        expected.add(new BigDecimal("1000.00"));

        List<Operation> input = new ArrayList<>();
        input.add(new Operation(Type.BUY,new BigDecimal("10.00"),10000));
        input.add(new Operation(Type.SELL,new BigDecimal("5.00"),5000));
        input.add(new Operation(Type.SELL,new BigDecimal("20.00"),3000));
        var result = calculateInvestmentTax.calculate(input);
        assertIterableEquals(expected, result);
    }

    @Test
    public void case4() {
        calculateInvestmentTax = new CalculateInvestmentTaxImpl();
        List<BigDecimal> expected = new ArrayList<>();
        expected.add(BigDecimal.ZERO);
        expected.add(BigDecimal.ZERO);
        expected.add(BigDecimal.ZERO);

        List<Operation> input = new ArrayList<>();
        input.add(new Operation(Type.BUY,new BigDecimal("10.00"),10000));
        input.add(new Operation(Type.BUY,new BigDecimal("25.00"),5000));
        input.add(new Operation(Type.SELL,new BigDecimal("15.00"),10000));
        var result = calculateInvestmentTax.calculate(input);
        assertIterableEquals(expected, result);
    }

    @Test
    public void case5() {
        calculateInvestmentTax = new CalculateInvestmentTaxImpl();
        List<BigDecimal> expected = new ArrayList<>();
        expected.add(BigDecimal.ZERO);
        expected.add(BigDecimal.ZERO);
        expected.add(BigDecimal.ZERO);
        expected.add(new BigDecimal("10000.00"));
        List<Operation> input = new ArrayList<>();
        input.add(new Operation(Type.BUY,new BigDecimal("10.00"),10000));
        input.add(new Operation(Type.BUY,new BigDecimal("25.00"),5000));
        input.add(new Operation(Type.SELL,new BigDecimal("15.00"),10000));
        input.add(new Operation(Type.SELL,new BigDecimal("25.00"),5000));
        var result = calculateInvestmentTax.calculate(input);
        assertIterableEquals(expected, result);
    }

    @Test
    public void case6() {
        calculateInvestmentTax = new CalculateInvestmentTaxImpl();
        List<BigDecimal> expected = new ArrayList<>();
        expected.add(BigDecimal.ZERO);
        expected.add(BigDecimal.ZERO);
        expected.add(BigDecimal.ZERO);
        expected.add(BigDecimal.ZERO);
        expected.add(new BigDecimal("3000.00"));
        List<Operation> input = new ArrayList<>();
        input.add(new Operation(Type.BUY,new BigDecimal("10.00"),10000));
        input.add(new Operation(Type.SELL,new BigDecimal("2.00"),5000));
        input.add(new Operation(Type.SELL,new BigDecimal("20.00"),2000));
        input.add(new Operation(Type.SELL,new BigDecimal("20.00"),2000));
        input.add(new Operation(Type.SELL,new BigDecimal("25.00"),1000));
        var result = calculateInvestmentTax.calculate(input);
        assertIterableEquals(expected, result);
    }


    @Test
    public void case7() {
        calculateInvestmentTax = new CalculateInvestmentTaxImpl();
        List<BigDecimal> expected = new ArrayList<>();
        expected.add(BigDecimal.ZERO);
        expected.add(BigDecimal.ZERO);
        expected.add(BigDecimal.ZERO);
        expected.add(BigDecimal.ZERO);
        expected.add(new BigDecimal("3000.00"));
        expected.add(BigDecimal.ZERO);
        expected.add(BigDecimal.ZERO);
        expected.add(new BigDecimal("3700.00"));
        expected.add(BigDecimal.ZERO);

        List<Operation> input = new ArrayList<>();
        input.add(new Operation(Type.BUY,new BigDecimal("10.00"),10000));
        input.add(new Operation(Type.SELL,new BigDecimal("2.00"),5000));
        input.add(new Operation(Type.SELL,new BigDecimal("20.00"),2000));
        input.add(new Operation(Type.SELL,new BigDecimal("20.00"),2000));
        input.add(new Operation(Type.SELL,new BigDecimal("25.00"),1000));
        input.add(new Operation(Type.BUY,new BigDecimal("20.00"),10000));
        input.add(new Operation(Type.SELL,new BigDecimal("15.00"),5000));
        input.add(new Operation(Type.SELL,new BigDecimal("30.00"),4350));
        input.add(new Operation(Type.SELL,new BigDecimal("30.00"),650));
        var result = calculateInvestmentTax.calculate(input);
        assertIterableEquals(expected, result);
    }


    @Test
    public void case8() {
        calculateInvestmentTax = new CalculateInvestmentTaxImpl();
        List<BigDecimal> expected = new ArrayList<>();
        expected.add(BigDecimal.ZERO);
        expected.add(new BigDecimal("80000.00"));
        expected.add(BigDecimal.ZERO);
        expected.add(new BigDecimal("60000.00"));
        List<Operation> input = new ArrayList<>();
        input.add(new Operation(Type.BUY,new BigDecimal("10.00"),10000));
        input.add(new Operation(Type.SELL,new BigDecimal("50.00"),10000));
        input.add(new Operation(Type.BUY,new BigDecimal("20.00"),10000));
        input.add(new Operation(Type.SELL,new BigDecimal("50.00"),10000));
        var result = calculateInvestmentTax.calculate(input);
        assertIterableEquals(expected, result);
    }
}
