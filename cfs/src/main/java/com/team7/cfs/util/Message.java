package com.team7.cfs.util;

public enum Message {
    LOGIN_SUCCESS("Welcome "),
    LOGIN_FAILURE("There seems to be an issue with the username/password combination that you entered"),
    LOGOUT_SUCCESS("You have been successfully logged out"),
    LOGOUT_FAILURE("You are not currently logged in"),
    NOT_LOGGED_IN("You are not currently logged in"),
    LOGGED_IN("You are logged in"),
    NUMBER_FORMAT_ERROR("Not a number"),
    BUY_FUND_SUCCESS("The fund has been successfully purchased"),
    NOT_ENOUGH_CASH("You didn’t provide enough cash to make this purchase"),
    NOT_ENOUGH_BALANCE("You don’t have enough cash in your account to make this purchase"),
    FUND_DOES_NOT_EXIST("The fund you provided does not exist"),
    NOT_CUSTOMER("You must be a customer to perform this action"),
    NOT_ADMIN("You must be an employee to perform this action"),
    DEPOSIT_CHECK_SUCCESS("The check was successfully deposited"),
    REQUEST_CHECK_SUCCESS("The check has been successfully requested"),
    REQUEST_CHECK_INSUFFICIENT_BAL("You don't have sufficient funds in your account to cover the requested check"),
    NOT_ENOUGH_SHARES("You don’t have that many shares in your portfolio"),
    SELL_FUND_SUCCESS("The shares have been successfully sold"),
    UNKNOWN_ERROR("Unknown error"),
    CREATE_CUSTOMER_SUCCESS("was registered successfully"),
    NOT_EMPLOYEE("You must be an employee to perform this action"),
    CREATE_CUSTOMER_INPUT_INVALID("The input you provided is not valid"),
    CREATE_FUND_ERROR("The fund with symbol exists"),
    CREATE_FUND_SUCCESS("The fund was successfully created"),
    TRANSITION_DAY_COMPLETED("The fund prices have been successfully recalculated"),
    VIEW_PORTFOLIO_SUCESS("The action was successful"),
    EMPTY_POTFOLIO("You don’t have any funds in your Portfolio");

    private String message;

    Message(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
