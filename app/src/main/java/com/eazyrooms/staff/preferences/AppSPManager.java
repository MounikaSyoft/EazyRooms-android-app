package com.eazyrooms.staff.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.eazyrooms.staff.login_model.OTPValidationResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class AppSPManager {

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private static final String epgm_sh_pref = "sh_pref_eazyrooms";



    public static final String phone = "phone";
    public static final String staffId = "id";
    public static final String accessToken = "accessToken";
    public static final String otpResponse = "otpResponse";

    public static void saveStringData(Context context, String key, String value) {
        sharedPreferences = context.getSharedPreferences(epgm_sh_pref, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void saveBooleanValue(Context context, String key, boolean isValue) {
        sharedPreferences = context.getSharedPreferences(epgm_sh_pref, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putBoolean(key, isValue);
        editor.apply();
    }

    public static boolean getBooleanData(Context context, String key) {
        sharedPreferences = context.getSharedPreferences(epgm_sh_pref, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }

    public static String getStringData(Context context, String key) {
        sharedPreferences = context.getSharedPreferences(epgm_sh_pref, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, "");
    }

    public static void saveStaffId(Context context, String key, String isValue) {
        sharedPreferences = context.getSharedPreferences(epgm_sh_pref, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString(key, isValue);
        editor.apply();
    }

    public static  String getStaffId(Context context, String key) {
        sharedPreferences = context.getSharedPreferences(epgm_sh_pref, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, "");
    }

    public static void saveIntValue(Context context, String key, int isValue) {
        sharedPreferences = context.getSharedPreferences(epgm_sh_pref, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putInt(key, isValue);
        editor.apply();
    }

    public static int getIntData(Context context, String key) {
        sharedPreferences = context.getSharedPreferences(epgm_sh_pref, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, 0);
    }

    /*public static void saveBookFlow(Context context,BookSlotsFlowModel bookSlotsFlowModel){
        Gson gson = new Gson();
        // getting data from gson and storing it in a string.
        String flow = gson.toJson(bookSlotsFlowModel);
        saveStringData(context, AppSPManager.bookingFLow, flow);

    }*/
    /*public static BookSlotsFlowModel getBookFlow(Context context){
        BookSlotsFlowModel bookSlotsFlowModel = null;
        Type dataObject = new TypeToken<BookSlotsFlowModel>() {
        }.getType();
        bookSlotsFlowModel = new Gson().fromJson(getStringData(context, AppSPManager.bookingFLow),
                dataObject);

        return bookSlotsFlowModel;

    }*/
    public static OTPValidationResponse getOTPResponse(Context context){
        OTPValidationResponse otpValidationResponse = null;
        Type dataObject = new TypeToken<OTPValidationResponse>() {
        }.getType();
        otpValidationResponse = new Gson().fromJson(getStringData(context, AppSPManager.otpResponse),
                dataObject);

        return otpValidationResponse;

    }
    public static void saveOTPResponse(Context context,OTPValidationResponse otpValidationResponse){
        // creating a new variable for gson.
        Gson gson = new Gson();
        // getting data from gson and storing it in a string.
        String otpRes = gson.toJson(otpValidationResponse);
       saveStringData(context, AppSPManager.otpResponse, otpRes);

    }

    public static void removeFromSharedPreferences(Context mContext, String key) {
        if (mContext != null) {
            SharedPreferences mSharedPreferences = mContext.getSharedPreferences(epgm_sh_pref, Context.MODE_PRIVATE);
            if (mSharedPreferences != null)
                mSharedPreferences.edit().remove(key).apply();
        }
    }

    public static void clearAll(Context context) {
        sharedPreferences = context.getSharedPreferences(epgm_sh_pref, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}
