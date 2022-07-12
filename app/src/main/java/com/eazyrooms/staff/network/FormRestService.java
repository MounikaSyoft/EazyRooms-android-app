package com.eazyrooms.staff.network;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Url;

public interface FormRestService {

    @POST
    Call<JsonObject> doPostWithParams(
            @Url  String url,
            @Body JsonObject jsonObject
    );
    @POST
    Call<JsonObject> doPostWithHeaders(
            @Url  String url,
            @HeaderMap HashMap<String,String> hashMap,
            @Body JsonObject jsonObject
    );
    @POST
    Call<JsonArray> doPostWithHeadersArray(
            @Url  String url,
            @HeaderMap HashMap<String,String> hashMap,
            @Body JsonObject jsonObject
    );

    @GET
    Call<JsonObject> doGetWithHeaders(
            @Url  String url,
            @HeaderMap HashMap<String,String> hashMap
    );
    @GET
    Call<JsonArray> doGetWithHeadersArray(
            @Url  String url,
            @HeaderMap HashMap<String,String> hashMap
    );
    @PUT
    Call<JsonObject> doPutWithHeaders(
            @Url  String url,
            @HeaderMap HashMap<String,String> hashMap,
            @Body JsonObject jsonObject
    );
}
