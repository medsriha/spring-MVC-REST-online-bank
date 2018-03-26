package com.team7.cfs.response;

public class Portfolio {
    private String name;
    private String shares;
    private String price;

    public Portfolio(String name, String shares, String price) {
        this.name = name;
        this.shares = shares;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShares() {
        return shares;
    }

    public void setShares(String shares) {
        this.shares = shares;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Portfolio{" +
                "name='" + name + '\'' +
                ", shares='" + shares + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
