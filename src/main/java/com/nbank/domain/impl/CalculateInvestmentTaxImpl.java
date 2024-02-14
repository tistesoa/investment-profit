package com.nbank.domain.impl;

import com.nbank.application.CalculateInvestmentTaxApp;
import com.nbank.domain.CalculateTax;
import com.nbank.domain.vo.Operation;
import com.nbank.domain.vo.Type;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CalculateInvestmentTaxImpl implements CalculateTax {
    public static Logger logger = Logger.getLogger(CalculateInvestmentTaxApp.class.getName());

    @Override
    public List<BigDecimal> calculate(List<Operation> operationList) {
        List<BigDecimal> result = new ArrayList<>();
        var weightedAveragePrice = BigDecimal.ZERO;
        int qtStocks = 0;
        var loss = BigDecimal.ZERO;
        for (Operation op : operationList) {
            if (op.getType() == Type.BUY) {
                weightedAveragePrice = ((weightedAveragePrice.multiply(BigDecimal.valueOf(qtStocks))).add((op.getUnitCost().multiply(BigDecimal.valueOf(op.getQuantity()))))).divide(BigDecimal.valueOf(qtStocks + op.getQuantity()));
                //logger.log(Level.INFO, "The weighted average price is R${0}", weightedAveragePrice);
                result.add(BigDecimal.ZERO);
                qtStocks += op.getQuantity();
            } else {
                var total = op.getUnitCost().multiply(BigDecimal.valueOf(op.getQuantity()));
                if (total.compareTo(new BigDecimal("20000")) <= 0 || op.getUnitCost().compareTo(weightedAveragePrice) <= 0) {
                    result.add(BigDecimal.ZERO);
                    loss = loss.add(op.getUnitCost().subtract(weightedAveragePrice).multiply(BigDecimal.valueOf(op.getQuantity())));
                } else {
                    var profit = (op.getUnitCost().subtract(weightedAveragePrice)).abs().multiply(BigDecimal.valueOf(op.getQuantity())).add(loss);
                    //logger.log(Level.INFO, "The profit value is R${0}", profit);
                    if (profit.compareTo(BigDecimal.ZERO) <= 0) {
                        result.add(BigDecimal.ZERO);
                        loss = profit;
                    } else {
                        var tax = (profit).multiply(BigDecimal.valueOf(0.20));
                        result.add(tax.setScale(2, RoundingMode.HALF_UP));
                    }

                    //logger.log(Level.INFO, "The tax value is R${0}", tax);

                }
                qtStocks -= op.getQuantity();

            }
        }
        //logger.log(Level.INFO, "Total stocks remains is {0}", qtStocks);

        return result;
    }
}
