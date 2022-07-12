package com.eazyrooms.staff.network;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.eazyrooms.staff.R;
import com.eazyrooms.staff.constants.Globals;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiManager {
    private Context context;
    private String baseUrl;
    private String accessToken = "";
    Dialog dialog = null;//loading dialog
    int progressCount = 0;

    public ApiManager(Context context, String baseUrl, String accessToken) {
        this.context = context;
        this.baseUrl = baseUrl;
        this.accessToken = accessToken;
        dialog = ProgressDialogUtil.setProgressDialog(context, "");
    }
    private void dismissDialog() {
        try {
            progressCount = progressCount - 1;
            if (progressCount == 0) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showDialog() {
        if (!dialog.isShowing()) {
            dialog.show();
        }
        progressCount = progressCount + 1;
    }

    public void postMethod(String url, JsonObject object, ApiResponseInterface apiResponseInterface) {

        if (!Globals.haveInternet(context)) {
            Globals.showToastTop(context, context.getResources().getString(R.string.CHECK_INTERNET));
            return;
        }
        HashMap<String, String> headers = new HashMap<>();
        if (!TextUtils.isEmpty(accessToken)) {
            headers.put(NetWorkConstants.authorization, accessToken);
        }
        String finalUrl = baseUrl + url;
        Log.d("ApiManager", finalUrl);
        showDialog();
        FormRestService apiService =
                ApiClient.getClient().create(FormRestService.class);
        Log.d("VRV", "parms!!!!" + object.toString());
        Call<JsonObject> call = apiService.doPostWithHeaders(finalUrl, headers,object);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(@NotNull Call<JsonObject> call, @NotNull Response<JsonObject> response) {
                dismissDialog();
                if (response.code() == 400) {
                    Log.d("resp code", String.valueOf(response.code()));
                    apiResponseInterface.isError(response.message());
                } else {
                    Log.d("resp code", String.valueOf(response.code()));
                    apiResponseInterface.isSuccess(response);
                }
            }

            @Override
            public void onFailure(@NotNull Call<JsonObject> call, @NotNull Throwable t) {
                dismissDialog();
                Log.d("resp code", String.valueOf(t.getMessage()));
                apiResponseInterface.isError(t.getMessage());
            }
        });
    }

    public void postMethodArray(String url, JsonObject object, ApiResponseInterfaceArray apiResponseInterface) {

        if (!Globals.haveInternet(context)) {
            Globals.showToastTop(context, context.getResources().getString(R.string.CHECK_INTERNET));
            return;
        }
        HashMap<String, String> headers = new HashMap<>();
        if (!TextUtils.isEmpty(accessToken)) {
            headers.put(NetWorkConstants.authorization, accessToken);
        }
        String finalUrl = baseUrl + url;
        showDialog();
        FormRestService apiService =
                ApiClient.getClient().create(FormRestService.class);
        Log.d("VRV", "parms!!!!" + object.toString());
        Call<JsonArray> call = apiService.doPostWithHeadersArray(finalUrl, headers,object);
        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(@NotNull Call<JsonArray> call, @NotNull Response<JsonArray> response) {
                dismissDialog();
                if (response.code() == 401) {
                } else {
                    apiResponseInterface.isSuccess(response);
                }
            }

            @Override
            public void onFailure(@NotNull Call<JsonArray> call, @NotNull Throwable t) {
                dismissDialog();
                apiResponseInterface.isError(t.getMessage());
            }
        });
    }

    public void getMethod(String url, ApiResponseInterface apiResponseInterface) {

        if (!Globals.haveInternet(context)) {
            Globals.showToastTop(context, context.getResources().getString(R.string.CHECK_INTERNET));
            return;
        }
        HashMap<String, String> headers = new HashMap<>();
        if (!TextUtils.isEmpty(accessToken)) {
            headers.put(NetWorkConstants.authorization, accessToken);
        }
        String finalUrl = baseUrl + url;
        showDialog();
        FormRestService apiService =
                ApiClient.getClient().create(FormRestService.class);
        Call<JsonObject> call = apiService.doGetWithHeaders(finalUrl, headers);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(@NotNull Call<JsonObject> call, @NotNull Response<JsonObject> response) {
                dismissDialog();
                if (response.code() == 401) {
                } else {
                    apiResponseInterface.isSuccess(response);
                }
            }

            @Override
            public void onFailure(@NotNull Call<JsonObject> call, @NotNull Throwable t) {
                dismissDialog();
                apiResponseInterface.isError(t.getMessage());
            }
        });

    }
    public void getMethodArray(String url, ApiResponseInterfaceArray apiResponseInterface) {

        if (!Globals.haveInternet(context)) {
            Globals.showToastTop(context, context.getResources().getString(R.string.CHECK_INTERNET));
            return;
        }
        HashMap<String, String> headers = new HashMap<>();
        if (!TextUtils.isEmpty(accessToken)) {
            headers.put(NetWorkConstants.authorization, accessToken);
        }
        String finalUrl = baseUrl + url;
        showDialog();
        FormRestService apiService =
                ApiClient.getClient().create(FormRestService.class);
        Call<JsonArray> call = apiService.doGetWithHeadersArray(finalUrl, headers);
        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(@NotNull Call<JsonArray> call, @NotNull Response<JsonArray> response) {
                dismissDialog();
                if (response.code() == 401) {
                } else {
                    apiResponseInterface.isSuccess(response);
                }
            }

            @Override
            public void onFailure(@NotNull Call<JsonArray> call, @NotNull Throwable t) {
                dismissDialog();
                apiResponseInterface.isError(t.getMessage());
            }
        });

    }


    public void loginAPI(String url, JsonObject object, ApiResponseInterface apiResponseInterface) {

        if (!Globals.haveInternet(context)) {
            Globals.showToastTop(context, context.getResources().getString(R.string.CHECK_INTERNET));
            return;
        }
        String finalUrl = baseUrl + url;
        showDialog();
        FormRestService apiService =
                ApiClient.getClient().create(FormRestService.class);
        Log.d("VRV", "parms!!!!" + object.toString());
        Call<JsonObject> call = apiService.doPostWithParams(finalUrl, object);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(@NotNull Call<JsonObject> call, @NotNull Response<JsonObject> response) {
                dismissDialog();
                if (response.code() == 401) {
                } else {
                    apiResponseInterface.isSuccess(response);
                }
            }

            @Override
            public void onFailure(@NotNull Call<JsonObject> call, @NotNull Throwable t) {
                dismissDialog();
                apiResponseInterface.isError(t.getMessage());
            }
        });
    }




    public void postImageMethod(String url, MultipartBody.Part file, ApiResponseInterface apiResponseInterface) {

        if (!Globals.haveInternet(context)) {
            Globals.showToastTop(context, context.getResources().getString(R.string.CHECK_INTERNET));
            return;
        }

    }
    public void postImageMethodParams(String url, List<MultipartBody.Part> images, Map<String, RequestBody> partMap, ApiResponseInterface apiResponseInterface) {

        if (!Globals.haveInternet(context)) {
            Globals.showToastTop(context, context.getResources().getString(R.string.CHECK_INTERNET));
            return;
        }

    }

    public void putMethod(String url, JsonObject object, ApiResponseInterface apiResponseInterface) {

        if (!Globals.haveInternet(context)) {
            Globals.showToastTop(context, context.getResources().getString(R.string.CHECK_INTERNET));
            return;
        }
        HashMap<String, String> headers = new HashMap<>();
        if (!TextUtils.isEmpty(accessToken)) {
            headers.put(NetWorkConstants.authorization, accessToken);
        }
        String finalUrl = baseUrl + url;
        Log.d("ApiManager", finalUrl);
        showDialog();
        FormRestService apiService =
                ApiClient.getClient().create(FormRestService.class);
        Log.d("VRV", "parms!!!!" + object.toString());
        Call<JsonObject> call = apiService.doPutWithHeaders(finalUrl, headers,object);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(@NotNull Call<JsonObject> call, @NotNull Response<JsonObject> response) {
                dismissDialog();
                if (response.code() == 400) {
                    Log.d("resp code", String.valueOf(response.code()));
                    apiResponseInterface.isError(response.message());
                } else {
                    Log.d("resp code", String.valueOf(response.code()));
                    apiResponseInterface.isSuccess(response);
                }
            }

            @Override
            public void onFailure(@NotNull Call<JsonObject> call, @NotNull Throwable t) {
                dismissDialog();
                Log.d("resp code", String.valueOf(t.getMessage()));
                apiResponseInterface.isError(t.getMessage());
            }
        });
    }
}
