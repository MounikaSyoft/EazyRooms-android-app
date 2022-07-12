package com.eazyrooms.staff.constants;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.eazyrooms.staff.R;
import com.google.gson.JsonObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Globals {
    public static final DecimalFormat df = new DecimalFormat("0.00");

    public static void showToastTop(Context context, String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 0, 0);

        View toastView = toast.getView();
        if (toastView != null) {
            toastView.setBackgroundColor(ContextCompat.getColor(context, R.color.black));
            toastView.setPadding(25, 25, 25, 25);
            TextView text = toast.getView().findViewById(android.R.id.message);
            text.setTextColor(Color.WHITE);
            text.setTextSize(14);
        }
        toast.show();
    }
    public static boolean haveInternet(Context c) {
        NetworkInfo info = ((ConnectivityManager) c
                .getSystemService(Context.CONNECTIVITY_SERVICE))
                .getActiveNetworkInfo();
        if (info == null || !info.isConnected())
            return false;
        info.isRoaming();
        return true;
    }
//    public static boolean haveInternet(Context c) {
//
//        boolean isOnline = false;
//        try {
//            Runtime runtime = Runtime.getRuntime();
//            Process p = runtime.exec("ping -c 1 8.8.8.8");
//            int waitFor = p.waitFor();
//            isOnline = waitFor == 0;    // only when the waitFor value is zero, the network is online indeed
//
//            // BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
//            // String str;
//            // while ((str = br.readLine()) != null) {
//            //     System.out.println(str);     // you can get the ping detail info from Process.getInputStream()
//            // }
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        return isOnline;
//    }

    public static String dateWeek(String strDate){
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
            SimpleDateFormat outputFormat = new SimpleDateFormat("EEE", Locale.ENGLISH);
            Date date = inputFormat.parse(strDate);
            return outputFormat.format(date);
        } catch (Exception e) {
            return "";
        }
    }
    public static String dateDD(String strDate){
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd", Locale.ENGLISH);
            Date date = inputFormat.parse(strDate);
            return outputFormat.format(date);
        } catch (Exception e) {
            return "";
        }
    }
    public static String dateFormates(String informate, String outFormat,String strDate){
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat(informate, Locale.ENGLISH);
            SimpleDateFormat outputFormat = new SimpleDateFormat(outFormat, Locale.ENGLISH);
            Date date = inputFormat.parse(strDate);
            return outputFormat.format(date);
        } catch (Exception e) {
            return "";
        }
    }

    public static boolean emailValidate(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
    public static boolean isPhone(String text) {
        if(!TextUtils.isEmpty(text)){
            return TextUtils.isDigitsOnly(text);
        } else{
            return false;
        }
    }
    public static String parseStringValue(JsonObject object, String key) {
        String value = "";
        if (object.has(key) && !object.get(key).isJsonNull()) {
            value = object.get(key).getAsString();
        }
        return value;
    }

    public static int parseIntValue(JsonObject object, String key) {
        int value = 0;
        if (object.has(key) && !object.get(key).isJsonNull()) {
            value = object.get(key).getAsInt();
        }
        return value;
    }

    public static boolean parseBooleanValue(JsonObject object, String key) {
        boolean value = false;
        if (object.has(key) && !object.get(key).isJsonNull()) {
            value = object.get(key).getAsBoolean();
        }
        return value;
    }
    public static String everyWordFirstLetterUpper(String value) {
        String afterChange = value;
        // stores each characters to a char array
        char[] charArray = value.toLowerCase().toCharArray();
        boolean foundSpace = true;

        for (int i = 0; i < charArray.length; i++) {

            // if the array element is a letter
            if (Character.isLetter(charArray[i])) {

                // check space is present before the letter
                if (foundSpace) {

                    // change the letter into uppercase
                    charArray[i] = Character.toUpperCase(charArray[i]);
                    foundSpace = false;
                }
            } else {
                // if the new character is not character
                foundSpace = true;
            }
        }

        afterChange = String.valueOf(charArray);

        return afterChange;
    }

}
