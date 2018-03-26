package com.team7.cfs.service;

import com.team7.cfs.domain.Fund;
import com.team7.cfs.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service(value = "TransitionDayService")
public class TransitionDayServiceImpl implements TransitionDayService  {

    @Autowired
    FundService fundService;

    @Override
    public synchronized Message performTransitionDay()   {

        List<Fund> funds = fundService.getAllFunds();
        Random rand = new Random();
        for(Fund fund : funds)  {
            String priceStr = fund.getValue();
            try {
                double newPrice = Double.parseDouble(priceStr);
                int min = -10;
                int max = 10;
                newPrice = newPrice + (rand.nextInt(20) + min)*newPrice/100;
                NumberFormat formatter = new DecimalFormat("#0.00");
                fund.setValue(formatter.format(newPrice));

            }   catch(NumberFormatException e)  {


            }
        }

        return Message.TRANSITION_DAY_COMPLETED;
    }
}
