package com.eazyrooms.staff;

import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.eazyrooms.staff.databinding.ActivityOtpverificationBinding;
import com.eazyrooms.staff.login_model.OTPValidationResponse;
import com.eazyrooms.staff.network.ApiResponseInterface;
import com.eazyrooms.staff.network.Urls;
import com.eazyrooms.staff.orders.OrdersListActivity;
import com.eazyrooms.staff.preferences.AppSPManager;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import retrofit2.Response;

public class OTPVerificationActivity extends BaseActivity {

    public ActivityOtpverificationBinding otpVerificationBinding;
    String ph_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = findViewById(R.id.contentLayout);
        otpVerificationBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_otpverification, layout, true);
        topBarSetup();

        Intent intent1 = getIntent();
        ph_value = intent1.getStringExtra("number");

        otpVerificationBinding.tvVerify.setOnClickListener(view -> otpValidation());
    }

    private void otpValidation() {

        if (TextUtils.isEmpty(otpVerificationBinding.otpPinView.getText().toString())) {
            showToast("Please enter otp");
        } else if (otpVerificationBinding.otpPinView.getText().toString().length() < 6) {
            showToast("Please enter valid otp");
        }else {
            otpValidationAPI();
        }
    }

    private void otpValidationAPI() {

        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("phone", ph_value);
        jsonObject.addProperty("otp", otpVerificationBinding.otpPinView.getText().toString().trim());

        apiManager.postMethod(Urls.otpVerify, jsonObject, new ApiResponseInterface() {
            @Override
            public void isError(String errorCode) {
                showToast("Please enter valid otp");
            }

            @Override
            public void isSuccess(Response<JsonObject> response) {
                if (response.code() == 200) {
                    JsonObject jsonObject = response.body();
                    Type dataObject = new TypeToken<OTPValidationResponse>() {
                    }.getType();

                    AppSPManager.saveStringData(OTPVerificationActivity.this,AppSPManager.phone,ph_value);
                    OTPValidationResponse otpValidationResponse = new Gson().fromJson(jsonObject, dataObject);

                    if (otpValidationResponse != null) {
                        // creating a new variable for gson.
                        Gson gson = new Gson();
                        // getting data from gson and storing it in a string.
                        String otpRes = gson.toJson(otpValidationResponse);
                        Log.d("OTP verify", otpRes);

                        AppSPManager.saveStringData(OTPVerificationActivity.this, AppSPManager.otpResponse, otpRes);
                        AppSPManager.saveStringData(OTPVerificationActivity.this, AppSPManager.accessToken, "Bearer "+otpValidationResponse.getAccess());
                        AppSPManager.saveStaffId(OTPVerificationActivity.this, AppSPManager.staffId,otpValidationResponse.getId() );
                        Log.d("staff id: ", otpValidationResponse.getId());

                        startActivity(new Intent(OTPVerificationActivity.this, OrdersListActivity.class));
                        finishAffinity();
                    }
                }

            }
        });
    }

    private void topBarSetup() {
        showHideBottom(false);
        showHideBackIcon(true);
        centerTextTitle(getString(R.string.phone));
    }

    public void hideKeyboard(View view){
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
}