package com.team7.cfs.controller;

import com.team7.cfs.domain.User;
import com.team7.cfs.form.CustomerForm;
import com.team7.cfs.response.MessageResponse;
import com.team7.cfs.service.AccountManageService;
import com.team7.cfs.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.team7.cfs.util.Message.CREATE_CUSTOMER_SUCCESS;
import static com.team7.cfs.util.Message.LOGIN_SUCCESS;

@RestController
public class CreateCustomerController {

    @Autowired
    private AccountManageService customerService;

    @GetMapping(path="/init")
    public ResponseEntity<MessageResponse> createTestAccount() {
        Message message = customerService.init();
        MessageResponse messageResponse = new MessageResponse(message.getMessage());
        System.out.println(messageResponse);
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @PostMapping(path="/createCustomerAccount")
    @Transactional
    public ResponseEntity<MessageResponse> createCustomer(@RequestBody @Valid CustomerForm form, HttpServletRequest request) {
        System.out.println("-------------------------Create Customer-------------------------");

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        Object response = customerService.createCustomerAccount(form, user);
        MessageResponse messageResponse;
        if (response instanceof String) {
            messageResponse = new MessageResponse((String)response);
        } else {
            messageResponse = new MessageResponse(((Message)response).getMessage());
        }
        System.out.println(messageResponse);
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

}
