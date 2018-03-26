package com.team7.cfs.form;

import com.team7.cfs.util.NumberFormatCheck;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CreateFundForm {

    @NotNull
    private String symbol;

    @NotNull
    private String name;

    @NotNull
    @NumberFormatCheck
    @Min((long)0.01)
    private double initial_value;

    public double getInitial_value() {
        return initial_value;
    }

    public void setInitial_value(double initial_value) {
        this.initial_value = initial_value;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
