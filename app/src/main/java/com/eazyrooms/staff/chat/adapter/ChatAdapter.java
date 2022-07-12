package com.eazyrooms.staff.chat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eazyrooms.staff.R;
import com.eazyrooms.staff.chat.model.ChatModel;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    List<ChatModel> chatModelList = new ArrayList<>();
    private Context context;

    public void updateData(List<ChatModel> chatList) {
        chatModelList.clear();
        chatModelList.addAll(chatList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chart_cell,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.Chat_Image.setImageResource(chatModelList.get(position).getChatImage());
        holder.Chat_Name.setText(chatModelList.get(position).getChatName());
        holder.Chat_Msg.setText(chatModelList.get(position).getChatMsg());
        holder.Chat_RoomNumber.setText(chatModelList.get(position).getChatRoomNumber());
        holder.Chat_Time.setText(chatModelList.get(position).getChatTime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedPositionItem.onClickPositionItem(chatModelList.get(holder.getAdapterPosition()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return chatModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView Chat_Image;
        TextView Chat_Name;
        TextView Chat_Msg;
        TextView Chat_RoomNumber;
        TextView Chat_Time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Chat_Image = itemView.findViewById(R.id.chat_img);
            Chat_Name = itemView.findViewById(R.id.chat_name);
            Chat_Msg = itemView.findViewById(R.id.chat_msg);
            Chat_RoomNumber = itemView.findViewById(R.id.chat_room_number);
            Chat_Time = itemView.findViewById(R.id.chat_time);

        }
    }

    public SelectedPositionItem selectedPositionItem;
    public interface SelectedPositionItem {
        void onClickPositionItem(ChatModel chatModel);
    }
}
