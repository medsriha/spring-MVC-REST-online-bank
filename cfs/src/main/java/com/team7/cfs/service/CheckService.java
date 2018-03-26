package com.team7.cfs.service;

import com.team7.cfs.domain.User;
import com.team7.cfs.form.*;
import com.team7.cfs.util.Message;

public interface CheckService {
    Message requestCheck(RequestCheckForm form, User user);
    Message depositCheck(DepositCheckForm form, User user);
}
