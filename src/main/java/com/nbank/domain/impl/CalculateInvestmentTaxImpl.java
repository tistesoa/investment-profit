package com.nbank.domain.impl;

import com.nbank.domain.CalculateTax;
import com.nbank.domain.vo.Operation;
import com.nbank.domain.vo.Result;
import com.nbank.domain.vo.Type;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;


public class CalculateInvestmentTaxImpl implements CalculateTax {
    private static final BigDecimal MIN_PROFIT = new BigDecimal("20000");
    private static final BigDecimal TAX_RATE = BigDecimal.valueOf(0.20);

    public static final Result ZERO_VALUE = new Result(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP));

    @Override
    public List<Result> calculate(List<Operation> operationList) {
        List<Result> result = new ArrayList<>();
        var weightedAveragePrice = BigDecimal.ZERO;
        int qtStocks = 0;
        var loss = BigDecimal.ZERO;
        for (Operation op : operationList) {
            if (op.getType() == Type.BUY) {
                weightedAveragePrice = ((weightedAveragePrice.multiply(BigDecimal.valueOf(qtStocks))).add((op.getUnitCost().multiply(BigDecimal.valueOf(op.getQuantity()))))).divide(BigDecimal.valueOf(qtStocks + op.getQuantity()));
                result.add(ZERO_VALUE);
                qtStocks += op.getQuantity();
            } else {
                var total = op.getUnitCost().multiply(BigDecimal.valueOf(op.getQuantity()));
                if (total.compareTo(MIN_PROFIT) <= 0 || op.getUnitCost().compareTo(weightedAveragePrice) <= 0) {
                    result.add(ZERO_VALUE);
                    loss = loss.add(op.getUnitCost().subtract(weightedAveragePrice).multiply(BigDecimal.valueOf(op.getQuantity())));
                } else {
                    var profit = (op.getUnitCost().subtract(weightedAveragePrice)).abs().multiply(BigDecimal.valueOf(op.getQuantity())).add(loss);
                    if (profit.compareTo(BigDecimal.ZERO) <= 0) {
                        result.add(ZERO_VALUE);
                        loss = profit;
                    } else {
                        var tax = (profit).multiply(TAX_RATE).setScale(2, RoundingMode.HALF_UP);
                        result.add(new Result(tax));
                    }
                }
                qtStocks -= op.getQuantity();
            }
        }

        return result;
    }
}
