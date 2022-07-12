package com.eazyrooms.staff;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.eazyrooms.staff.chat.ChatActivity;
import com.eazyrooms.staff.databinding.ActivityBaseBinding;
import com.eazyrooms.staff.network.ApiManager;
import com.eazyrooms.staff.network.Urls;
import com.eazyrooms.staff.preferences.AppSPManager;
import com.eazyrooms.staff.profile.ProfileActivity;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class BaseActivity extends AppCompatActivity {

    public ActivityBaseBinding activityBaseBinding;
    public ApiManager apiManager;
    public SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
    public SimpleDateFormat dateFormatYMD = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    public SimpleDateFormat dateFormatMMDDYY = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
    public String regex = "[0-9]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBaseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base);
        apiManager = new ApiManager(this, Urls.baseURL, AppSPManager.getStringData(this, AppSPManager.accessToken));


        activityBaseBinding.topBarLyt.imgBackClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        activityBaseBinding.bottomLayout.imgMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(BaseActivity.this, ChatActivity.class));
            }
        });

        activityBaseBinding.bottomLayout.imgAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(BaseActivity.this, ProfileActivity.class));
            }
        });
    }

    public void showHideBottom(boolean flag){
        if(flag){
            activityBaseBinding.bottomLayout.bottomLayoutMain.setVisibility(View.VISIBLE);
        }else{
            activityBaseBinding.bottomLayout.bottomLayoutMain.setVisibility(View.GONE);
        }

    }

    public void  topBarShowHide(boolean flag){
        if(flag){
            activityBaseBinding.topBarLyt.topBarLytMain.setVisibility(View.VISIBLE);
        }else{
            activityBaseBinding.topBarLyt.topBarLytMain.setVisibility(View.GONE);
        }
    }

    public void  centerTextTitle(String title){
        activityBaseBinding.topBarLyt.tvMiddleName.setText(title);
    }
    public void  showHideCenterTextTitle(boolean flag){
        if(flag){
            activityBaseBinding.topBarLyt.tvMiddleName.setVisibility(View.VISIBLE);
        }else{
            activityBaseBinding.topBarLyt.tvMiddleName.setVisibility(View.GONE);
        }
    }

    public void  showHideBackIcon(boolean flag){
        if(flag){
            activityBaseBinding.topBarLyt.imgBack.setVisibility(View.VISIBLE);
        }else{
            activityBaseBinding.topBarLyt.imgBack.setVisibility(View.GONE);
        }
    }

    public void showToast(String msg) {

        //Creating the LayoutInflater instance
        LayoutInflater li = getLayoutInflater();
        //Getting the View object as defined in the customtoast.xml file
        View layout = li.inflate(R.layout.custom_toast_layout,(ViewGroup) findViewById(R.id.customToastLayout));

        TextView textView = layout.findViewById(R.id.tvShowToast);
        textView.setText(msg);
        //Creating the Toast object
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
//        toast.setGravity(Gravity.BOTTOM, 0, -30);
        toast.setView(layout);//setting the view of custom toast layout
        toast.show();
    }
}