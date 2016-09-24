package com.spring.boot.practice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by wangchong on 9/18/16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Person {

    @JsonProperty("JavaClassName")
    public String getClassName(){
        return this.getClass().getCanonicalName();
    }

    private String id;

    private String introduction;

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
