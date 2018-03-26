package com.team7.cfs.form;

import com.team7.cfs.util.NumberFormatCheck;

import javax.validation.constraints.NotNull;

public class RequestCheckForm {
    @NotNull
    @NumberFormatCheck
    private double cashValue;

    public double getCashValue() {
        return cashValue;
    }

    public void setCashValue(double cashValue) {
        this.cashValue = cashValue;
    }
}
