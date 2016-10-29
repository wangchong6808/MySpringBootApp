package com.daimler.otr.service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wangchong on 10/10/16.
 */
public class LeadIDGenerator {

    public static String generate(){
        SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
        Date date = new Date();
        String randomPart = String.valueOf(date.getTime()).substring(0,6);
        String formatedDate = format.format(date);
        String leadId = "L"+formatedDate+randomPart;
        return leadId;
    }
}
