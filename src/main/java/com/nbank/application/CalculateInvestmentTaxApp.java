package com.nbank.application;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nbank.domain.CalculateTax;
import com.nbank.domain.impl.CalculateInvestmentTaxImpl;
import com.nbank.domain.vo.Operation;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class CalculateInvestmentTaxApp {
    public static Logger logger = Logger.getLogger(CalculateInvestmentTaxApp.class.getName());

    public static void main(String[] args) throws JsonProcessingException {
        CalculateTax calculateTax = new CalculateInvestmentTaxImpl();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                break;
            }
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
            List<Operation> operations = objectMapper.readValue(line, new TypeReference<>() {
            });
            var results = calculateTax.calculate(operations);
            var restJson = objectMapper.writeValueAsString(results);
            //serializable to json
            System.out.println(restJson);

        }
        scanner.close();
        //System.out.println("Program terminated.");
    }
}