package com.nbank.domain;

import com.nbank.domain.vo.Operation;
import com.nbank.domain.vo.Result;

import java.util.List;

public interface CalculateTax {

    List<Result> calculate(List<Operation> operationList);
}
