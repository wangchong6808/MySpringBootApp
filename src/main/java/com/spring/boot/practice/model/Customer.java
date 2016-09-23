package com.spring.boot.practice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by wangchong on 9/18/16.
 * http://localhost:8080/customer/11?Address=Shanghai
 * http://localhost:8080/customer/11?address=Shanghai
 */
public class Customer extends Person {

    @JsonProperty("Address")
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
