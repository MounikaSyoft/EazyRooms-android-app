package com.eazyrooms.staff;

import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.eazyrooms.staff.databinding.ActivityLoginScreenBinding;
import com.eazyrooms.staff.network.ApiResponseInterface;
import com.eazyrooms.staff.network.Urls;
import com.eazyrooms.staff.orders.OrderStatusActivity;
import com.google.gson.JsonObject;


import retrofit2.Response;

public class LoginScreen extends BaseActivity {

    public ActivityLoginScreenBinding loginScreenBinding;
    boolean phoneValidation = false;
   // String type = "phone";
    String countryCode = "";
    String ph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginScreenBinding = DataBindingUtil.setContentView(this,R.layout.activity_login_screen);

        loginScreenBinding.signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countryCode=loginScreenBinding.countryCode.getSelectedCountryCodeWithPlus();

                if (TextUtils.isEmpty(loginScreenBinding.etLoginNumber.getText().toString()) /*&& loginScreenBinding.etLoginNumber.getText().toString().length() == 10*/){
                    showToast("Please enter valid phone number");
                }else if (!phoneValidation){
                    login();
                }
            }
        });
    }

    private void login() {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("phone", countryCode+loginScreenBinding.etLoginNumber.getText().toString().trim());

        ph = countryCode+loginScreenBinding.etLoginNumber.getText().toString().trim();

        Log.d("LoginScreen", jsonObject.toString());

        apiManager.postMethod(Urls.login, jsonObject, new ApiResponseInterface() {
            @Override
            public void isError(String errorCode) {
                showToast("Please enter valid phone number");
               // Log.d("error",errorCode);
            }

            @Override
            public void isSuccess(Response<JsonObject> response) {
                Log.d("msg",String.valueOf(response));
                if (response.code() == 200) {
                    Intent i = (new Intent(getApplicationContext(), OTPVerificationActivity.class));
                    i.putExtra("number",ph);
                    startActivity(i);
                    Toast.makeText(LoginScreen.this,"Otp sent successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void hideKeyboard(View view){
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
}