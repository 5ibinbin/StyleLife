package com.example.star.model.http;

/**
 * Created by：Cral-Gates on 16/8/20 21:39
 * EMail：zhangxia2013105@gmail.com
 * Date: 16/8/20
 * Description:
 */
public class ApiException extends RuntimeException {

    public ApiException() {
    }
    public ApiException(String detailMessage) {
        super(detailMessage);
    }

    public ApiException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public ApiException(Throwable throwable) {
        super(throwable);
    }
}
