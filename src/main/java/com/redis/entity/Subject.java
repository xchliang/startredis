package com.redis.entity;

import java.io.Serializable;

public class Subject implements Serializable {

    private static final long serialVersionUID = 5678567515743579811L;
    public String name;
    public String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
