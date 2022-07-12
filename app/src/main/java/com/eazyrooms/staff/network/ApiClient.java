package com.eazyrooms.staff.network;

import com.eazyrooms.staff.BuildConfig;
import com.google.gson.GsonBuilder;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String BASE_URL = Urls.baseURL;
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        httpBuilder.connectTimeout(10, TimeUnit.MINUTES)
                .writeTimeout(10, TimeUnit.MINUTES)
                .readTimeout(10, TimeUnit.MINUTES);
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpBuilder.addInterceptor(loggingInterceptor);
        }
        httpBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .addHeader("Content-Type","application/json;charset=UTF-8")
                        .addHeader("Accept","application/json, text/plain, */*")
                        .build();
                return chain.proceed(request);
            }
        });


        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .client(httpBuilder.build())
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                    .build();
        }
        return retrofit;
    }
}
