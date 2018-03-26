package com.team7.cfs.service;

import com.team7.cfs.dao.FundDao;
import com.team7.cfs.dao.PositionDao;
import com.team7.cfs.dao.UserDao;
import com.team7.cfs.domain.Fund;
import com.team7.cfs.domain.Position;
import com.team7.cfs.domain.PositionCompPK;
import com.team7.cfs.domain.User;
import com.team7.cfs.form.*;
import com.team7.cfs.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import static com.team7.cfs.util.Message.*;
import static com.team7.cfs.util.Role.CUSTOMER;

@Service(value = "FundService")
public class FundServiceImpl implements FundService {

    @Autowired
    private FundDao fundDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PositionDao positionDao;

    @Autowired
    private AccountManageService accountManageService;

    @Override
    public synchronized Message buyFund(BuyFundForm form, User user) {

        if (user == null) {
            return NOT_LOGGED_IN;
        }
        if (user.getRole() != CUSTOMER) {
            return NOT_CUSTOMER;
        }
        String symbol = form.getSymbol();
        Fund fund = fundDao.findFundBySymbol(symbol);
        if (fund == null) {
            return FUND_DOES_NOT_EXIST;
        }
        try{
            double price = Double.parseDouble(fund.getValue());
            double amount = form.getCashValue();
            double cur_bal = (user.getBalance() == null) ? 0 : Double.parseDouble(user.getBalance());
            if (amount < price) {
                return NOT_ENOUGH_CASH;
            }
            if (cur_bal < amount) {
                return NOT_ENOUGH_BALANCE;
            }
            int shares = (int)(amount/price - amount/price % 1);
            double deduct = shares * price;

            NumberFormat formatter = new DecimalFormat("#0.00");
            String new_bal = formatter.format(cur_bal - deduct);

            user.setBalance(new_bal);
            userDao.save(user);
            Position cur_pos = positionDao.findPositionById(new PositionCompPK(fund, user));
            if (cur_pos == null) {
                Position position = new Position();
                position.setId(new PositionCompPK(fund, user));
                position.setShares(String.valueOf(shares));
                positionDao.save(position);
            } else {
                String new_shares = String.valueOf(cur_pos.getShares() + shares);
                cur_pos.setShares(new_shares);
                positionDao.save(cur_pos);
            }
        } catch (NumberFormatException e) {
            return NUMBER_FORMAT_ERROR;
        }

        return BUY_FUND_SUCCESS;
    }

    @Override
    @Transactional
    public synchronized Message sellFund(SellFundForm form, User user) {
        if (user == null) {
            return NOT_LOGGED_IN;
        }
        if (user.getRole() != CUSTOMER) {
            return NOT_CUSTOMER;
        }
        String symbol = form.getSymbol();
        Fund fund = fundDao.findFundBySymbol(symbol);
        if (fund == null) {
            return FUND_DOES_NOT_EXIST;
        }
        try{
            double price = Double.parseDouble(fund.getValue());
            int num_shares = Integer.parseInt(form.getNumShares());
            double cur_bal = (user.getBalance() == null) ? 0 : Double.parseDouble(user.getBalance());
            Position cur_pos = positionDao.findPositionById(new PositionCompPK(fund, user));
            int cur_num_shares = Integer.parseInt(cur_pos.getShares());
            if (num_shares <= cur_num_shares) {
                NumberFormat formatter = new DecimalFormat("#0.00");
                String new_bal = formatter.format(cur_bal + num_shares * price);
                user.setBalance(new_bal);
                userDao.save(user);
                cur_pos.setShares(Integer.toString(cur_num_shares - num_shares));
                positionDao.save(cur_pos);
            } else {
                return NOT_ENOUGH_SHARES;
            }
        } catch (NumberFormatException e) {
            return NUMBER_FORMAT_ERROR;
        }
        return SELL_FUND_SUCCESS;
    }

    @Override
    public synchronized Message createFund(CreateFundForm form, User user) {

        Message loginStatus = accountManageService.userLoginStatus(user);

        if(loginStatus != LOGGED_IN)    {
            return loginStatus;
        }

        String fundSymbol = form.getSymbol();
        String fundName = form.getName();

        if(fundDao.findFundBySymbol(fundSymbol) != null || fundDao.findFundBySymbol(fundName) != null)  {
            return CREATE_FUND_SUCCESS;
        }

        Fund newFund = new Fund();
        newFund.setName(fundName);
        newFund.setSymbol(fundSymbol);
        NumberFormat formatter = new DecimalFormat("#0.00");
        newFund.setValue(formatter.format(form.getInitial_value()));
        fundDao.save(newFund);
        return CREATE_FUND_SUCCESS;
    }

    @Override
    public synchronized List<Fund> getAllFunds() {
        List<Fund> fundList = fundDao.findAll();
        return fundList;
    }
}
