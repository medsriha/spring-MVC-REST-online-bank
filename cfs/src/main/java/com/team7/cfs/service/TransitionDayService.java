package com.team7.cfs.service;

import com.team7.cfs.domain.Fund;
import com.team7.cfs.util.Message;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

public interface TransitionDayService {

    Message performTransitionDay();
}
