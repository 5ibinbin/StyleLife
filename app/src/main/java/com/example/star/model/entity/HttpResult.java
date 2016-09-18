package com.example.star.model.entity;

/**
 * Created by：Cral-Gates on 16/8/27 09:34
 * EMail：zhangxia2013105@gmail.com
 * Date: 16/8/27
 * Description:
 */
public class HttpResult<T> {

    private int Code;
    private String Message;
    private T Body;

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public T getBody() {
        return Body;
    }

    public void setBody(T body) {
        Body = body;
    }

    public String getDescription(){
        return "Code"+getCode()+"Message"+getMessage();
    }
}
