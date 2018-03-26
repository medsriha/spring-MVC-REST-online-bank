package com.team7.cfs.service;


import com.team7.cfs.domain.User;
import com.team7.cfs.form.CustomerForm;
import com.team7.cfs.form.LoginForm;
import com.team7.cfs.response.ViewPortfolioResponse;
import com.team7.cfs.util.Message;

public interface AccountManageService {
    Message init();
    User login(LoginForm form);
    Object createCustomerAccount(CustomerForm form, User user);
    Message userLoginStatus(User user);
    Object customerPortfolio(User user);
}
