package com.eazyrooms.staff.orders;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eazyrooms.staff.BaseActivity;
import com.eazyrooms.staff.R;
import com.eazyrooms.staff.chat.ChatActivity;
import com.eazyrooms.staff.databinding.ActivityOrderDetailsBinding;
import com.eazyrooms.staff.profile.ProfileActivity;

public class OrderDetailsActivity extends BaseActivity {

    private ActivityOrderDetailsBinding orderDetailsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = findViewById(R.id.contentLayout);
        orderDetailsBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_order_details, layout, true);
        topBarSetup();
        initTopInfo();

        orderDetailsBinding.tvAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
            }
        });
    }

    private void topBarSetup() {
        showHideBottom(false);
        showHideBackIcon(true);
        centerTextTitle(getString(R.string.order_details));
    }

    private void initTopInfo(){
        Spannable word1 = new SpannableString(getResources().getString(R.string.details_sugg));
        Spannable word2 = new SpannableString(getResources().getString(R.string.see));

        word2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color._1CAE81)), 0, word2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        word2.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View v) {
                Log.d("main", "word2 clicked");

            }
            public void updateDrawState(TextPaint ds) {
                ds.setUnderlineText(false);
            }
        }, 0, word2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        orderDetailsBinding.textView1.setText(word1);
        orderDetailsBinding.textView1.append(word2);
    }
}