package com.eazyrooms.staff.network;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AlertDialog;

import com.eazyrooms.staff.R;

public class ProgressDialogUtil {
    //    Splash screen as loading dialog
    public static AlertDialog setProgressDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.DialogTheme);
        builder.setCancelable(false);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View dialogueView = layoutInflater.inflate(R.layout.activity_splash_screen, null);
        builder.setView(dialogueView);

        AlertDialog dialog = builder.create();
        return dialog;
    }

    //    Please wait message as loading dialog
    public static Dialog setProgressDialog(Context context, String message) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.custom_progress_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT
        );

        return dialog;

    }
}
