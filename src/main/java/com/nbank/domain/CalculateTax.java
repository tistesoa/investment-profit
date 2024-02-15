package com.nbank.domain;

import com.nbank.commons.vo.OperationVO;
import com.nbank.commons.vo.TaxVO;
import java.util.List;

public interface CalculateTax {

  List<TaxVO> calculate(List<OperationVO> operationVOList);
}
