package com.eazyrooms.staff.profile;

import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.eazyrooms.staff.BaseActivity;
import com.eazyrooms.staff.OTPVerificationActivity;
import com.eazyrooms.staff.R;
import com.eazyrooms.staff.constants.Globals;
import com.eazyrooms.staff.databinding.ActivityProfileBinding;
import com.eazyrooms.staff.login_model.OTPValidationResponse;
import com.eazyrooms.staff.network.ApiResponseInterface;
import com.eazyrooms.staff.network.Urls;
import com.eazyrooms.staff.orders.OrdersListActivity;
import com.eazyrooms.staff.preferences.AppSPManager;
import com.google.gson.JsonObject;

import retrofit2.Response;


public class ProfileActivity extends BaseActivity {

    public ActivityProfileBinding profileBinding;
    public OTPValidationResponse otpValidationResponse = null;
    private String staffId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = findViewById(R.id.contentLayout);
        profileBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_profile, layout, true);
        topBarSetup();


        profileBinding.etName.setEnabled(false);
        profileBinding.etNumber.setEnabled(false);
        profileBinding.etMail.setEnabled(false);
        profileBinding.etAddress.setEnabled(false);

        profileBinding.et1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileBinding.etName.setEnabled(true);
                profileBinding.etName.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
                imm.showSoftInput(profileBinding.etName, InputMethodManager.SHOW_IMPLICIT);
                profileBinding.etName.setSelection(profileBinding.etName.getText().length());

            }
        });
        profileBinding.et2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileBinding.etNumber.setEnabled(true);
                profileBinding.etNumber.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
                imm.showSoftInput(profileBinding.etNumber, InputMethodManager.SHOW_IMPLICIT);
                profileBinding.etNumber.setSelection(profileBinding.etNumber.getText().length());

            }
        });
        profileBinding.et3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileBinding.etMail.setEnabled(true);
                profileBinding.etMail.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
                imm.showSoftInput(profileBinding.etMail, InputMethodManager.SHOW_IMPLICIT);
                profileBinding.etMail.setSelection(profileBinding.etMail.getText().length());
            }
        });
        profileBinding.et4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileBinding.etAddress.setEnabled(true);
                profileBinding.etAddress.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
                imm.showSoftInput(profileBinding.etAddress, InputMethodManager.SHOW_IMPLICIT);
                profileBinding.etAddress.setSelection(profileBinding.etAddress.getText().length());
            }
        });

        profileBinding.saveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                profileBinding.etName.setEnabled(false);
                profileBinding.etNumber.setEnabled(false);
                profileBinding.etMail.setEnabled(false);
                profileBinding.etAddress.setEnabled(false);

                profileValidation();
            }
        });
        setData();
    }

    private void setData() {

        otpValidationResponse = AppSPManager.getOTPResponse(ProfileActivity.this);
        if (otpValidationResponse != null) {
            profileBinding.etName.setText(otpValidationResponse.getName());
            profileBinding.etNumber.setText(otpValidationResponse.getPhone());
            profileBinding.etMail.setText(otpValidationResponse.getEmail());
           // profileBinding.etAddress.setText(otpValidationResponse.getAddress());
            profileBinding.tvProfileName.setText(otpValidationResponse.getName());


        }
    }


    private void profileValidation() {
        if (TextUtils.isEmpty(profileBinding.etName.getText().toString())){
            showToast("Please enter name");
        } else if (TextUtils.isEmpty(profileBinding.etNumber.getText().toString())){
            showToast("please enter Phone Number");
        } else if (!Globals.emailValidate(profileBinding.etMail.getText().toString())){
            showToast("Please enter email");
        } /*else if (TextUtils.isEmpty(profileBinding.etAddress.getText().toString())){
            showToast("Please enter Address");
        }*/else{
            updateProfileAPI();
        }
    }

    private void updateProfileAPI() {
        JsonObject jsonObject = new JsonObject();
        try {
            jsonObject.addProperty("name", profileBinding.etName.getText().toString().trim());
            jsonObject.addProperty("phone", profileBinding.etNumber.getText().toString().trim());
            jsonObject.addProperty("email", profileBinding.etMail.getText().toString().trim());
            //jsonObject.addProperty("address", profileBinding.etAddress.getText().toString().trim());

        } catch (Exception e) {
            e.printStackTrace();
        }

        staffId = otpValidationResponse.getId();
        String url = String.format(Urls.editProfile, staffId);
        Log.d("url id value: ",url);

        apiManager.putMethod(url, jsonObject, new ApiResponseInterface() {
            @Override
            public void isError(String errorCode) {

                showToast("not updated");

            }

            @Override
            public void isSuccess(Response<JsonObject> response) {
                JsonObject dataObject = response.body();
                Log.d("Profile response: ", String.valueOf(response.code()));
                if (response.code() == 200 && dataObject!=null) {

                    Log.d("Profile: ", String.valueOf(response.code()));

                        otpValidationResponse.setName(Globals.parseStringValue(dataObject,"name"));
                        otpValidationResponse.setEmail(Globals.parseStringValue(dataObject,"email"));
                        otpValidationResponse.setPhone(Globals.parseStringValue(dataObject,"phone"));
                       // otpValidationResponse.setAddress(Globals.parseStringValue(dataObject,"address"));

                       // AppSPManager.saveStaffId(ProfileActivity.this,AppSPManager.staffId,staffId);
                        AppSPManager.saveOTPResponse(ProfileActivity.this,otpValidationResponse);
                        onBackPressed();
                    Toast.makeText(ProfileActivity.this, "updated", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    private void topBarSetup() {
        showHideBottom(false);
        showHideBackIcon(true);
        centerTextTitle(getString(R.string.profile_details));
    }

    public void hideKeyboard(View view){
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
}