package com.metocs.common.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {

    private static final long serialVersionUID = 1656342010474487985L;

    public static final Integer SUCCESS = 200;

    public static final Integer ERROR  = 500;

    private Integer code;

    private String message;

    private Object data;


    public Result() {
    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
