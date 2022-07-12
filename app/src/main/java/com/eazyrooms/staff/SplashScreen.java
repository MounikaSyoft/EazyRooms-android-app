package com.eazyrooms.staff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;

import com.eazyrooms.staff.orders.OrdersListActivity;
import com.eazyrooms.staff.preferences.AppSPManager;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(() ->{
            Intent intent;
            if (!TextUtils.isEmpty(AppSPManager.getStringData(SplashScreen.this,AppSPManager.accessToken))){
                intent = new Intent(SplashScreen.this, OrdersListActivity.class);
            }else {
                intent = new Intent(SplashScreen.this, LoginScreen.class);
            }
            startActivity(intent);
            finishAffinity();
        },500);
    }
}