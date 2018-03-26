package com.team7.cfs.form;

import com.team7.cfs.util.NumberFormatCheck;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;

public class DepositCheckForm {
    @NotNull
    private String username;

    @NotNull
    @Min(0)
    @NumberFormatCheck
    private double cash;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }
}
