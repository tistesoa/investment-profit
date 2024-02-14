package com.nbank.domain.vo;

public enum Type {
    BUY("buy"),
    SELL("sell");

    private final String value;
    Type(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
