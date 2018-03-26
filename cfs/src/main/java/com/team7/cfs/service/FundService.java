package com.team7.cfs.service;

import com.team7.cfs.domain.Fund;
import com.team7.cfs.domain.User;
import com.team7.cfs.form.*;
import com.team7.cfs.util.Message;

import java.util.List;

public interface FundService {

    Message buyFund(BuyFundForm form, User user);
    Message sellFund(SellFundForm form, User user);
    Message createFund(CreateFundForm form, User user);
    List<Fund> getAllFunds();

}
