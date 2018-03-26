package com.team7.cfs.service;

import java.util.ArrayList;
import java.util.List;
import com.team7.cfs.dao.UserDao;
import com.team7.cfs.dao.PositionDao;
import com.team7.cfs.domain.Position;
import com.team7.cfs.domain.PositionCompPK;
import com.team7.cfs.domain.User;
import com.team7.cfs.form.CustomerForm;
import com.team7.cfs.form.LoginForm;
import com.team7.cfs.response.Portfolio;
import com.team7.cfs.response.ViewPortfolioResponse;
import com.team7.cfs.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.team7.cfs.util.Message.*;
import static com.team7.cfs.util.Message.CREATE_CUSTOMER_SUCCESS;
import static com.team7.cfs.util.Message.UNKNOWN_ERROR;
import static com.team7.cfs.util.Role.CUSTOMER;
import static com.team7.cfs.util.Role.EMPLOYEE;

@Service(value = "AccountManageService")
public class AccountManageServiceImpl implements AccountManageService {

    @Autowired
    private UserDao userdao;
    @Autowired
    private PositionDao positionDao;

    @Override
    public User login(LoginForm form) {
        return userdao.getUserByUsernameAndPassword(form.getUsername(), form.getPassword());
    }

    @Override
    public Message init() {
        User user = new User();
        user.setFirstName("Jane");
        user.setLastName("Admin");
        user.setUsername("jadmin");
        user.setRole(EMPLOYEE);
        user.setPassword("admin");
        user.setAddress("123 Main street");
        user.setCity("Pittsburgh");
        user.setState("Pa");
        user.setZip("15143");
        userdao.save(user);
        return LOGIN_SUCCESS;
    }

    @Override
    public synchronized Object createCustomerAccount(CustomerForm form, User user) {
        User customer = new User();

        if (user == null) {
            return NOT_LOGGED_IN;
        }
        if (user.getRole() != EMPLOYEE) {
            return NOT_EMPLOYEE;
        }
        try {

            if (userdao.getUserByUsername(form.getUsername()) != null) {
                return CREATE_CUSTOMER_INPUT_INVALID;
            } else {
                customer.setUsername(form.getUsername());
                customer.setPassword(form.getPassword());
                customer.setFirstName(form.getFname());
                customer.setLastName(form.getLname());
                customer.setAddress(form.getAddress());
                customer.setCity(form.getCity());
                customer.setState(form.getState());
                customer.setZip(form.getZip());
                customer.setEmail(form.getEmail());
                //To be checked
                customer.setBalance(form.getCash());
                customer.setRole(CUSTOMER);
                userdao.save(customer);
            }

        }catch (NumberFormatException e) {
            return NUMBER_FORMAT_ERROR;
        }
        catch (Exception e) {
            return UNKNOWN_ERROR;
        }
        return customer.getFirstName() + " was registered successfully";
    }

    @Override
    public Message userLoginStatus(User user)   {

        if(user == null)    {
            return NOT_LOGGED_IN;
        }
        if(user.getRole() != EMPLOYEE)  {
            return NOT_EMPLOYEE;
        }

        return LOGGED_IN;
    }

    @Override
    public Object customerPortfolio(User user) {
        if (user == null){
            return NOT_LOGGED_IN;
        }
        if (user.getRole() != CUSTOMER){
            return NOT_CUSTOMER;
        }

        List<Position> positionList = positionDao.findPositionsById_User(user);
        if (positionList.size() == 0) {
            return EMPTY_POTFOLIO;
        }
        List<Portfolio> portfolioList = new ArrayList<>();
        for(Position position: positionList){
            String name = position.getId().getFund().getName();
            String shares = position.getShares();
            String price = position.getId().getFund().getValue();
            portfolioList.add(new Portfolio(name, shares, price));
        }
        String cash = user.getBalance();
        return new ViewPortfolioResponse(VIEW_PORTFOLIO_SUCESS.getMessage(), cash, portfolioList);
    }
}

