package com.daimler.otr.format.datetime;

import org.springframework.format.FormatterRegistrar;
import org.springframework.format.FormatterRegistry;

import java.util.Date;

public class DateTimeFormatterRegistrar implements FormatterRegistrar {

    private DateTimeFormatter formatter;

    @Override
    public void registerFormatters(FormatterRegistry registry) {
        registry.addFormatterForFieldAnnotation(new DateTimeFormatAnnotationFormatterFactory());
        if (formatter!=null){
            registry.addFormatter(this.formatter);
            registry.addFormatterForFieldType(Date.class, this.formatter);
        }
    }
    public void setFormatter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }
}
