package com.eazyrooms.staff.chat;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.eazyrooms.staff.BaseActivity;
import com.eazyrooms.staff.R;
import com.eazyrooms.staff.chat.adapter.ChatWindowAdapter;
import com.eazyrooms.staff.databinding.ActivityChatWindowBinding;

public class ChatWindowActivity extends BaseActivity {

    private ActivityChatWindowBinding chatWindowBinding;
    private ChatWindowAdapter chatWindowAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chatWindowBinding = DataBindingUtil.setContentView(this,R.layout.activity_chat_window);
        recyclerViewDataSetup();
        chatWindowBinding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
    }

    private void recyclerViewDataSetup(){

        chatWindowAdapter = new ChatWindowAdapter();
        chatWindowBinding.rvChatData.setAdapter(chatWindowAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}