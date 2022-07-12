package com.eazyrooms.staff.chat.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.eazyrooms.staff.R;
import com.eazyrooms.staff.databinding.ChatWindowCellBinding;

public class ChatWindowAdapter extends RecyclerView.Adapter<ChatWindowAdapter.ChatWindowHolder>{
    @NonNull
    @Override
    public ChatWindowHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new ChatWindowHolder(DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.chat_window_cell, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChatWindowHolder holder, int position) {

        holder.bindData(position);

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ChatWindowHolder extends RecyclerView.ViewHolder {

        ChatWindowCellBinding itemBinding;

        public ChatWindowHolder(@NonNull ChatWindowCellBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        public void bindData(int pos){

        }
    }
}
