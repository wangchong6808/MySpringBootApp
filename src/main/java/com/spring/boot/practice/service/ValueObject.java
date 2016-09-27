package com.spring.boot.practice.service;

/**
 * Created by wangchong on 9/26/16.
 */
public class ValueObject {

    private String id;
    private float value;

    public String getId() {
        return id;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ValueObject [id=" + id + ", value=" + value + "]";
    }
}
