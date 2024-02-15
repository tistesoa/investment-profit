package com.nbank.domain.impl;

import com.nbank.commons.vo.OperationVO;
import com.nbank.commons.vo.TaxVO;
import com.nbank.domain.CalculateTax;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;


public class CalculateInvestmentTaxImpl implements CalculateTax {

  private BigDecimal weightedAveragePrice;
  private int qtStocks;
  private BigDecimal loss;
  public static final TaxVO NO_TAX_VALUE = new TaxVO(
      BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP));

  private final BigDecimal minProfit;
  private final BigDecimal taxRate;


  public CalculateInvestmentTaxImpl(BigDecimal taxRate, BigDecimal minProfit){
    if(taxRate.compareTo(BigDecimal.ZERO) <= 0){
      throw new RuntimeException("Invalid taxRate "+taxRate+" It must be greater than zero");
    }
    if(minProfit.compareTo(BigDecimal.ZERO) <= 0){
      throw new RuntimeException("Invalid minProfit "+minProfit+" It must be greater than zero");
    }
    this.minProfit = minProfit;
    this.taxRate = taxRate;
  }
  public List<TaxVO> calculate(List<OperationVO> operationVOList) {
    reset();
    List<TaxVO> results = new ArrayList<>();
    for (OperationVO operationVO : operationVOList) {
      switch (operationVO.getOperationType()) {
        case BUY -> results.add(this.executeBuyOperation(operationVO));
        case SELL -> results.add(this.executeSellOperation(operationVO));
      }
    }
    return results;
  }

  private void reset() {
    this.loss = BigDecimal.ZERO;
    this.qtStocks = 0;
    this.weightedAveragePrice = BigDecimal.ZERO;
  }

  private void addStocks(int quantity) {
    qtStocks += quantity;
  }
  private void removeStocks(int quantity) {
    qtStocks -= quantity;
  }

  private void setLoss(BigDecimal newLoss){
    this.loss = newLoss;
  }

  /**
   * Method responsible to execute buy operation, calculating the weighted average price,
   * add stocks quantities and return no tax value.
   * @param operationVO
   * @return TaxVO
   */
  protected TaxVO executeBuyOperation(OperationVO operationVO) {
    this.setWeightedAveragePrice(this.calculateWeightedAveragePrice(operationVO));
    this.addStocks(operationVO.getQuantity());
    return NO_TAX_VALUE;
  }

  private void setWeightedAveragePrice(BigDecimal newWeightedAveragePrice) {
    this.weightedAveragePrice = newWeightedAveragePrice;
  }

  protected BigDecimal calculateWeightedAveragePrice(OperationVO operationVO) {
    return ((weightedAveragePrice.multiply(BigDecimal.valueOf(qtStocks))).add(
        (operationVO.getUnitCost()
            .multiply(BigDecimal.valueOf(operationVO.getQuantity())))))
        .divide(BigDecimal.valueOf(qtStocks + operationVO.getQuantity()));
  }

  /**
   * Method responsible to execute sell operation, calculating the profits of the sell,
   * verifying if it is less than min_profit settled, or if the profits is less than bought average price.
   * In case of loss, it's registered and will be used for next profits calculation.
   * In case of profits, it's applied tax rate to calculate the tax value for the operation.
   * @param operationVO
   * @return TaxVO
   */
  protected TaxVO executeSellOperation(OperationVO operationVO) {
    var tax = NO_TAX_VALUE;
    var totalSell = operationVO.getUnitCost().multiply(BigDecimal.valueOf(operationVO.getQuantity()));
    if (totalSell.compareTo(this.minProfit) <= 0
        || operationVO.getUnitCost().compareTo(this.weightedAveragePrice) <= 0) {
      this.setLoss(calculateLosses(operationVO));
    } else {
      var currentProfit = calculateProfitsWithLosses(operationVO);
      if (currentProfit.compareTo(BigDecimal.ZERO) <= 0) {
        this.setLoss(currentProfit);
      } else {
        tax = calculateProfitTaxes(currentProfit);
      }
    }
    this.removeStocks(operationVO.getQuantity());
    return tax;
  }

  protected TaxVO calculateProfitTaxes(BigDecimal profit) {
    return new TaxVO(profit.multiply(this.taxRate).setScale(2, RoundingMode.HALF_UP));
  }

  protected BigDecimal calculateLosses(OperationVO operationVO) {
    return this.loss.add(operationVO.getUnitCost().subtract(this.weightedAveragePrice)
        .multiply(BigDecimal.valueOf(operationVO.getQuantity())));
  }

  protected BigDecimal calculateProfitsWithLosses(OperationVO operationVO) {
    return (operationVO.getUnitCost().subtract(this.weightedAveragePrice)).abs()
        .multiply(BigDecimal.valueOf(operationVO.getQuantity())).add(this.loss);
  }
}
