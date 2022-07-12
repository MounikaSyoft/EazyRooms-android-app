package com.eazyrooms.staff.orders.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.eazyrooms.staff.R;
import com.eazyrooms.staff.databinding.StatusTitleCellBinding;


import java.util.ArrayList;

public class OrderTitleAdapter extends RecyclerView.Adapter<OrderTitleAdapter.OrdersHolder> {

    int selectedPos= 0;
    ArrayList<String> titleData=new ArrayList<>();

    public void updateData(ArrayList<String>data){
        titleData.clear();
        titleData.addAll(data);
    }

    @NonNull
    @Override
    public OrderTitleAdapter.OrdersHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new OrdersHolder(DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.status_title_cell, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderTitleAdapter.OrdersHolder holder, int position) {
        holder.onBindData();
    }

    @Override
    public int getItemCount() {
        return titleData.size();
    }

    public class OrdersHolder extends RecyclerView.ViewHolder {
        StatusTitleCellBinding itemBinding;
        public OrdersHolder(@NonNull StatusTitleCellBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        public void onBindData() {
            if(selectedPos==getAdapterPosition()){
                this.itemBinding.tvStatusTitle.setTextColor(ContextCompat.getColor(itemBinding.tvStatusTitle.getContext(),R.color._002AB3));
                this.itemBinding.tvCount.setTextColor(ContextCompat.getColor(itemBinding.tvCount.getContext(),R.color._002AB3));
                this.itemBinding.viewSep.setVisibility(View.VISIBLE);
            }else{
                this.itemBinding.tvStatusTitle.setTextColor(ContextCompat.getColor(itemBinding.tvStatusTitle.getContext(),R.color._989898));
                this.itemBinding.tvCount.setTextColor(ContextCompat.getColor(itemBinding.tvCount.getContext(),R.color._989898));
                this.itemBinding.viewSep.setVisibility(View.GONE);
            }
            this.itemBinding.tvStatusTitle.setText(titleData.get(getAdapterPosition()));
            this.itemBinding.layoutStatusTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedPos=getAdapterPosition();
                    selectedPositionTitle.onClickTitle(getAdapterPosition());

                    notifyDataSetChanged();
                }
            });
        }

    }

    public SelectedPositionTitle selectedPositionTitle;
    public interface SelectedPositionTitle {
        void onClickTitle(int position);
    }
}
