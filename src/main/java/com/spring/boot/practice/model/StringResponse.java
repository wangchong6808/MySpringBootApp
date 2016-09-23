package com.spring.boot.practice.model;

/**
 * Created by wangchong on 9/23/16.
 */
public class StringResponse {
    private String response;
    public StringResponse(String s) {
        this.response = s;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
