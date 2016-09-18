package com.example.star.model.http;

import com.example.star.constant.UrlConstant;
import com.example.star.model.entity.HttpResult;
import com.example.star.model.entity.LoginResult;
import com.example.star.utils.json.GsonUtils;
import com.example.star.utils.log.LogUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by：Cral-Gates on 16/8/27 09:38
 * EMail：zhangxia2013105@gmail.com
 * Date: 16/8/27
 * Description:
 */
public class RestClient {

    private Retrofit retrofit;

    private ApiService apiService;

    private static RestClient ourInstance = new RestClient();

    public static RestClient getInstance() {
        return ourInstance;
    }

    private RestClient() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("X-LC-Id","XSR6DduuWLOhOGAxueY8ILUI-gzGzoHsz")
                                .addHeader("X-LC-Key","6Q3zKE0Lba4yM806ybQaWxNg")
                                .build();
                        return chain.proceed(request);
                    }
                });
        httpClientBuilder.connectTimeout(UrlConstant.DEFAULT_TIME, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .baseUrl(UrlConstant.URL)
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }
    /*
    * 登录的rxJava封装
    * */
//    public void login(String userName, String passWord, Subscriber<LoginResult> subscriber) {
//        apiService.login(userName, passWord)
//                .map(new MapFunc1<LoginResult>())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
    /*
    * 登录
    * */
    public void login (String username, String password, Subscriber<LoginResult> subscriber){
        apiService.login(username, password)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public class MapFunc1<T> implements Func1<HttpResult<T>, T>{
        @Override
        public T call(HttpResult<T> httpResult) {
            LogUtils.d(GsonUtils.toJson(httpResult));
            if (httpResult.getCode() != 0){
                throw new ApiException(httpResult.getDescription());
            }
            LogUtils.d("data" + httpResult.getBody());
            return httpResult.getBody();
        }
    }

    public abstract class FlatMapFunc1<T, R> implements Func1<HttpResult<T>, Observable<R>>{

        @Override
        public Observable<R> call(HttpResult<T> httpResult) {
            if (httpResult.getCode() != 0){
                throw new ApiException(httpResult.getDescription());
            }
            return flatMapCall(httpResult.getBody());
        }
        abstract Observable<R> flatMapCall(T t);
    }
}
