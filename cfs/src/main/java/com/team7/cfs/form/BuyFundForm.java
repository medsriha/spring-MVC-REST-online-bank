package com.team7.cfs.form;

import com.team7.cfs.util.NumberFormatCheck;

import javax.validation.constraints.NotNull;

public class BuyFundForm {

    @NotNull
    private String symbol;

    @NotNull
    @NumberFormatCheck
    private double cashValue;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getCashValue() {
        return cashValue;
    }

    public void setCashValue(double cashValue) {
        this.cashValue = cashValue;
    }
}
