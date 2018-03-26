package com.team7.cfs.domain;

public class CutomerDetails {


    private String balance;
    private String [] fundInformation;

    public CutomerDetails(String balance, String[] fundInformation) {

        this.balance = balance;
        this.fundInformation = fundInformation;
    }

    public String getBalance() {
        return balance;
    }

    public String []getFundInformation() {
        return fundInformation;
    }
}