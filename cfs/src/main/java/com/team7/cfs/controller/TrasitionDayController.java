package com.team7.cfs.controller;


import com.team7.cfs.domain.User;
import com.team7.cfs.form.CreateFundForm;
import com.team7.cfs.response.MessageResponse;
import com.team7.cfs.service.AccountManageService;
import com.team7.cfs.service.TransitionDayService;
import com.team7.cfs.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.team7.cfs.util.Message.NOT_EMPLOYEE;
import static com.team7.cfs.util.Message.NOT_LOGGED_IN;

@RestController
public class TrasitionDayController {

    @Autowired
    AccountManageService accountManageService;

    @Autowired
    TransitionDayService transitionDayService;

    @PostMapping("/transitionDay")
    @Transactional
    public ResponseEntity<MessageResponse> transitionDayProcess(HttpServletRequest request)    {
        System.out.println("-------------------------Transition Day-------------------------");

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        Message message = accountManageService.userLoginStatus(user);
        if(message.equals(NOT_EMPLOYEE) || message.equals(NOT_LOGGED_IN))    {
            MessageResponse messageResponse = new MessageResponse(message.getMessage());
            return new ResponseEntity<>(messageResponse, HttpStatus.OK);
        }

        message = transitionDayService.performTransitionDay();
        MessageResponse messageResponse = new MessageResponse(message.getMessage());
        System.out.println(messageResponse);
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

}
