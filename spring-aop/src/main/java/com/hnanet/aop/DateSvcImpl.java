package com.hnanet.aop;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service("dateSvc")
public class DateSvcImpl implements DateSvc {

    @Override
    public void printDate(Date date) {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
    }
}
