package com.team7.cfs.response;

import com.team7.cfs.domain.Fund;

import java.util.List;

public class ViewPortfolioResponse {
    private String message;
    private String cash;
    private List<Portfolio> funds;

    public ViewPortfolioResponse(String message, String cash, List<Portfolio> funds) {
        this.message = message;
        this.cash = cash;
        this.funds = funds;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }

    public List<Portfolio> getFunds() {
        return funds;
    }

    public void setFunds(List<Portfolio> funds) {
        this.funds = funds;
    }

    @Override
    public String toString() {
        return "ViewPortfolioResponse{" +
                "message='" + message + '\'' +
                ", cash='" + cash + '\'' +
                ", funds=" + funds +
                '}';
    }
}
