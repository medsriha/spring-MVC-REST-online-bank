package com.team7.cfs.controller;


import com.team7.cfs.domain.User;
import com.team7.cfs.form.*;
import com.team7.cfs.response.MessageResponse;
import com.team7.cfs.response.ViewPortfolioResponse;
import com.team7.cfs.service.AccountManageService;
import com.team7.cfs.service.FundService;
import com.team7.cfs.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.text.View;
import javax.validation.Valid;
import static com.team7.cfs.util.Message.*;
@RestController
public class FundController {

    @Autowired
    private FundService fundService;

    @Autowired
    private AccountManageService accountManageService;

    @PostMapping("/buyFund")
    @Transactional
    public ResponseEntity<MessageResponse> buyFund(@RequestBody @Valid BuyFundForm form, HttpServletRequest request) {
        System.out.println("-------------------------Buy Fund-------------------------");

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        Message message = fundService.buyFund(form, user);
        MessageResponse messageResponse = new MessageResponse(message.getMessage());
        System.out.println(message.getMessage());
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);

    }
    @PostMapping("/sellFund")
    @Transactional
    public ResponseEntity<MessageResponse> sellFund(@RequestBody @Valid SellFundForm form, HttpServletRequest request) {
        System.out.println("-------------------------Sell Fund-------------------------");
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        Message message = fundService.sellFund(form, user);
        MessageResponse messageResponse = new MessageResponse(message.getMessage());
        System.out.println(message.getMessage());
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);

    }

    @PostMapping("/createFund")
    @Transactional
    public ResponseEntity<MessageResponse> createFund(@RequestBody @Valid CreateFundForm form, HttpServletRequest request)  {
        System.out.println("-------------------------Create Fund-------------------------");
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        Message message = fundService.createFund(form, user);
        MessageResponse messageResponse = new MessageResponse(message.getMessage());
        System.out.println(message.getMessage());
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @GetMapping(path="/viewPortfolio")
    public ResponseEntity<Object> createCustomer(HttpServletRequest request) {

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        System.out.println("-------------------------Create Customer-------------------------");

        Object response = accountManageService.customerPortfolio(user);
        if (response instanceof Message) {
            MessageResponse messageResponse = new MessageResponse(((Message)response).getMessage());
            System.out.println(messageResponse);
            return new ResponseEntity<>(messageResponse, HttpStatus.OK);
        } else if (response instanceof ViewPortfolioResponse){
            ViewPortfolioResponse portfolioResponse = (ViewPortfolioResponse)response;
            System.out.println(portfolioResponse);
            return new ResponseEntity<>(portfolioResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(UNKNOWN_ERROR.getMessage(), HttpStatus.OK);


    }
}
