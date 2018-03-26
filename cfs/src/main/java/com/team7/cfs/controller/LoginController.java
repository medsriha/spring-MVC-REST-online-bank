package com.team7.cfs.controller;

import com.team7.cfs.domain.User;
import com.team7.cfs.form.LoginForm;
import com.team7.cfs.response.MessageResponse;
import com.team7.cfs.service.AccountManageService;
import com.team7.cfs.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
public class LoginController {
    @Autowired
    private AccountManageService service;

    @PostMapping("/login")
    public ResponseEntity<MessageResponse> login(@RequestBody @Valid LoginForm form, HttpServletRequest request){

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        String message;
        System.out.println("-------------------------Login-------------------------");

        // already logged in
        if (user != null && user.getUsername().equals(form.getUsername())){
            message = Message.LOGIN_SUCCESS.getMessage()+user.getFirstName();
        } else {
            User newUser = service.login(form);
            if (newUser == null) {
                message = Message.LOGIN_FAILURE.getMessage();
            } else {
                message = Message.LOGIN_SUCCESS.getMessage()+ newUser.getFirstName();
                session.setAttribute("user", newUser);
                // set session time out in seconds
                session.setMaxInactiveInterval(15 * 60);
            }
        }
        MessageResponse messageResponse = new MessageResponse(message);
        System.out.println(messageResponse);
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);

    }

    @PostMapping("/logout")
    public ResponseEntity<MessageResponse> logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        System.out.println("----------------------------Logout----------------------------");
        String message;
        if (user != null) {
            session.setAttribute("user", null);
            message = Message.LOGOUT_SUCCESS.getMessage();
        } else{
            message = Message.LOGOUT_FAILURE.getMessage();
        }
        MessageResponse messageResponse = new MessageResponse(message);
        System.out.println(messageResponse);
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }
}
