package com.spring.boot.practice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by wangchong on 9/18/16.
 * http://localhost:8080/app/customer/11?Address=Shanghai
 * http://localhost:8080/app/customer/11?address=Shanghai
 */
public class Customer extends Person {

    @JsonProperty("Address")
    private String address;

    private List<Contact> contacts;

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
