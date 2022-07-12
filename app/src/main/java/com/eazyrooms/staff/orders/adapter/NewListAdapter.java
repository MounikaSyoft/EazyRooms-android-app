package com.eazyrooms.staff.orders.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.eazyrooms.staff.R;
import com.eazyrooms.staff.chat.adapter.ChatAdapter;
import com.eazyrooms.staff.chat.model.ChatModel;
import com.eazyrooms.staff.databinding.StatusListCellBinding;
import com.eazyrooms.staff.orders.model.NewListModel;


public class NewListAdapter extends RecyclerView.Adapter<NewListAdapter.NewListHolder> {

    int selectedPos= 0;

    @NonNull
    @Override
    public NewListAdapter.NewListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new NewListHolder(DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),R.layout.status_list_cell,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewListAdapter.NewListHolder holder, int position) {

        holder.onBindData();
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class NewListHolder extends RecyclerView.ViewHolder {

     StatusListCellBinding itemBinding;

        public NewListHolder(@NonNull StatusListCellBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }
        public void onBindData() {

            this.itemBinding.statusLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (selectedPos == getAdapterPosition()){

                        selectedOrderItem.onClickOrderItem(getAdapterPosition());
                    }

                }
            });

        }
    }

    public SelectedOrderItem selectedOrderItem;
    public interface SelectedOrderItem {
        void onClickOrderItem(int position);
    }
}
