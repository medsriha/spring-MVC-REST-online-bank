package com.team7.cfs.service;

import com.team7.cfs.dao.UserDao;
import com.team7.cfs.domain.User;
import com.team7.cfs.form.DepositCheckForm;
import com.team7.cfs.form.RequestCheckForm;
import com.team7.cfs.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import static com.team7.cfs.util.Message.*;
import static com.team7.cfs.util.Role.CUSTOMER;
import static com.team7.cfs.util.Role.EMPLOYEE;

@Service(value = "CheckService")
public class CheckServiceImpl implements CheckService {
    @Autowired
    private UserDao userDao;

    @Override
    public synchronized Message requestCheck(RequestCheckForm form, User user) {
        if (user == null) {
            return NOT_LOGGED_IN;
        }
        if (user.getRole() != CUSTOMER) {
            return NOT_CUSTOMER;
        }
        try{
            double amount = form.getCashValue();
            double cur_bal = (user.getBalance() == null) ? 0 : Double.parseDouble(user.getBalance());
            if (cur_bal < amount) {
                return REQUEST_CHECK_INSUFFICIENT_BAL;
            }
            NumberFormat formatter = new DecimalFormat("#0.00");
            String new_bal = formatter.format(cur_bal - amount);
            user.setBalance(new_bal);
            userDao.save(user);
        } catch (NumberFormatException e) {
            return NUMBER_FORMAT_ERROR;
        }

        return REQUEST_CHECK_SUCCESS;
    }
    @Override
    public synchronized Message depositCheck(DepositCheckForm form, User user) {
        if (user == null) {
            return NOT_LOGGED_IN;
        }
        if (user.getRole() != EMPLOYEE) {
            return NOT_ADMIN;
        }

        try{
            User cus = userDao.getUserByUsername(form.getUsername());
            double amount = form.getCash();
            double cur_bal = (cus.getBalance() == null) ? 0 : Double.parseDouble(cus.getBalance());
            NumberFormat formatter = new DecimalFormat("#0.00");
            String new_bal = formatter.format(cur_bal + amount);
            cus.setBalance(new_bal);
            userDao.save(cus);
        } catch (NumberFormatException e) {
            return NUMBER_FORMAT_ERROR;
        }
        return DEPOSIT_CHECK_SUCCESS;
    }
}
