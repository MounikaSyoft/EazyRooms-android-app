package com.eazyrooms.staff.network;

import com.google.gson.JsonObject;

import retrofit2.Response;

public interface ApiResponseInterface {
    void isError(String errorCode);
    void isSuccess(Response<JsonObject> response);
}
