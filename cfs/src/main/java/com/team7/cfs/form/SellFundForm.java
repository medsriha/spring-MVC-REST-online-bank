package com.team7.cfs.form;

import javax.validation.constraints.NotNull;

public class SellFundForm {

    @NotNull
    private String symbol;

    @NotNull
    private String numShares;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getNumShares() {
        return numShares;
    }

    public void setNumShares(String numShares) {
        this.numShares = numShares;
    }
}
