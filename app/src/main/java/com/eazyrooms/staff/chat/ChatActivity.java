package com.eazyrooms.staff.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.eazyrooms.staff.BaseActivity;
import com.eazyrooms.staff.R;
import com.eazyrooms.staff.chat.adapter.ChatAdapter;
import com.eazyrooms.staff.chat.model.ChatModel;
import com.eazyrooms.staff.databinding.ActivityChatBinding;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends BaseActivity {

    private ActivityChatBinding chatBinding;
    private List<ChatModel> chatModels;
    private ChatAdapter chatAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = findViewById(R.id.contentLayout);
        chatBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_chat, layout, true);
        topBarSetup();
        recyclerViewDataSetup();
    }

    private void recyclerViewDataSetup() {
        chatAdapter = new ChatAdapter();
        chatBinding.rvChartList.setAdapter(chatAdapter);
        chatAdapter.selectedPositionItem = new ChatAdapter.SelectedPositionItem() {
            @Override
            public void onClickPositionItem(ChatModel chatModel) {

                startActivity(new Intent(ChatActivity.this,ChatWindowActivity.class));
            }
        };
        initData();
        chatAdapter.updateData(chatModels);

    }

    private void topBarSetup() {
        showHideBottom(false);
        showHideBackIcon(true);
        centerTextTitle(getString(R.string.chat));
    }

    private List<ChatModel> initData() {
        chatModels = new ArrayList<>();

        chatModels.add(new ChatModel(R.drawable.chat_profile,
                "camrina",
                "Hey,Hi After long time",
                "A101","4:20 PM"));
        chatModels.add(new ChatModel(R.drawable.chat_profile,
                "Mathew Lewis",
                "Hey,Hi After long time",
                "A101","4:20 PM"));
        chatModels.add(new ChatModel(R.drawable.chat_profile,
                "Jennifer Fritz",
                "Hey,Hi After long time",
                "A101","4:20 PM"));
        chatModels.add(new ChatModel(R.drawable.chat_profile,
                "Laney rey",
                "Hey,Hi After long time",
                "A101","4:20 PM"));

        return chatModels;
    }
}