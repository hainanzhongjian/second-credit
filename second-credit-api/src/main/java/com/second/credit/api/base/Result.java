package com.second.credit.api.base;

import java.io.Serializable;

/**
 * 操作结果集
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1l;

    private Object code = -1;

    private String message = "";

    private T bean;

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBean() {
        return bean;
    }

    public void setBean(T bean) {
        this.bean = bean;
    }

    public void setCodeMessage(Object code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Result [code=" + code + ", message=" + message + "]";
    }

}
