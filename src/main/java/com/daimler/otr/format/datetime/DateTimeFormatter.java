package com.daimler.otr.format.datetime;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateTimeFormatter implements Formatter<Date> {


    @Override
    public Date parse(String text, Locale locale) throws ParseException {
        Calendar cal = Calendar.getInstance(locale);
        cal.setTimeInMillis(Long.valueOf(text));
        return cal.getTime();
    }

    @Override
    public String print(Date object, Locale locale) {
        return String.valueOf(object.getTime());
    }
}
