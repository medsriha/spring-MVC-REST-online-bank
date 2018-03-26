package com.team7.cfs.controller;

import com.team7.cfs.domain.User;
import com.team7.cfs.form.BuyFundForm;
import com.team7.cfs.form.*;
import com.team7.cfs.response.MessageResponse;
import com.team7.cfs.service.CheckService;
import com.team7.cfs.service.FundService;
import com.team7.cfs.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.team7.cfs.util.Message.*;

@RestController
public class CheckController {

    @Autowired
    private CheckService checkService;

    @PostMapping("/requestCheck")
    public ResponseEntity<MessageResponse> requestCheck(@RequestBody @Valid RequestCheckForm form, HttpServletRequest request) {
        System.out.println("-------------------------Request-------------------------");

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        Message message = checkService.requestCheck(form, user);
        MessageResponse messageResponse = new MessageResponse(message.getMessage());
        System.out.println(messageResponse);
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);

    }
    @PostMapping("/depositCheck")
    public ResponseEntity<MessageResponse> depositCheck(@RequestBody @Valid DepositCheckForm form, HttpServletRequest request) {
        System.out.println("-------------------------Deposit-------------------------");
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        Message message = checkService.depositCheck(form, user);
        MessageResponse messageResponse = new MessageResponse(message.getMessage());
        System.out.println(messageResponse);
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);

    }
}
