package com.example.star.model.http;

import com.example.star.model.entity.LoginResult;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by：Cral-Gates on 16/8/20 21:23
 * EMail：zhangxia2013105@gmail.com
 * Date: 16/8/20
 * Description:
 */
public interface ApiService {
    //----------------------登录---------------------//
    @GET("login")
    Observable<LoginResult> login(
            @Query("username") String username,
            @Query("password") String password);
}
