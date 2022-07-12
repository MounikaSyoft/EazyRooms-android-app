package com.eazyrooms.staff.network;

import com.google.gson.JsonArray;

import retrofit2.Response;

public interface ApiResponseInterfaceArray {
    void isError(String errorCode);
    void isSuccess(Response<JsonArray> response);
}
