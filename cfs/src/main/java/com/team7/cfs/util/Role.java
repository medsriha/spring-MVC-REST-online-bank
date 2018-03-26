package com.team7.cfs.util;

public enum Role {
    EMPLOYEE("Employee"),
    CUSTOMER("Customer");

    private String role;

    Role(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
