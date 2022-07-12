package com.eazyrooms.staff.orders;

import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.eazyrooms.staff.BaseActivity;
import com.eazyrooms.staff.R;
import com.eazyrooms.staff.databinding.ActivityOrderStatusBinding;
import com.eazyrooms.staff.login_model.OTPValidationResponse;
import com.eazyrooms.staff.orders.adapter.NewListAdapter;
import com.eazyrooms.staff.orders.adapter.OrderTitleAdapter;
import com.eazyrooms.staff.preferences.AppSPManager;

import java.util.ArrayList;

public class OrderStatusActivity extends BaseActivity {

    public OTPValidationResponse otpValidationResponse = null;
    public ActivityOrderStatusBinding orderStatusBinding;
    private OrderTitleAdapter orderTitlesAdapter;
    private NewListAdapter newListAdapter;
    String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = findViewById(R.id.contentLayout);
        orderStatusBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_order_status, layout, true);
        setRecyclerViewData();
        topBarSetup();
        dataSetUp();
    }

    private void dataSetUp() {
        otpValidationResponse = AppSPManager.getOTPResponse(OrderStatusActivity.this);
    }

    private void topBarSetup() {
        showHideBottom(false);
        showHideBackIcon(true);
        //centerTextTitle(getString(R.string.orders));
        Intent intent1 = getIntent();
        value = intent1.getStringExtra("categories");
        centerTextTitle(value);
    }

    private void setRecyclerViewData() {
        ArrayList<String> data = new ArrayList<>();
        data.add("New");
        data.add("Processing");
        data.add("Completed");

        orderTitlesAdapter = new OrderTitleAdapter();
        //orderStatusBinding.rvOrderTitle.setAdapter(orderTitlesAdapter);
        orderTitlesAdapter.updateData(data);

        orderTitlesAdapter.selectedPositionTitle=new OrderTitleAdapter.SelectedPositionTitle() {
            @Override
            public void onClickTitle(int position) {
                orderStatusBinding.rvOrderData.setAdapter(null);
                if(data.get(position).equalsIgnoreCase("New")){
                    newListAdapter=new NewListAdapter();
                    orderStatusBinding.rvOrderData.setAdapter(newListAdapter);
                }else
                if(data.get(position).equalsIgnoreCase("Processing")){
                    newListAdapter=new NewListAdapter();
                    orderStatusBinding.rvOrderData.setAdapter(newListAdapter);
                }
            }
        };
        newListAdapter = new NewListAdapter();
        orderStatusBinding.rvOrderData.setAdapter(newListAdapter);

        newListAdapter.selectedOrderItem = new NewListAdapter.SelectedOrderItem(){
            @Override
            public void onClickOrderItem(int position) {
                orderStatusBinding.rvOrderData.setAdapter(newListAdapter);
                startActivity(new Intent(getApplicationContext(),OrderDetailsActivity.class));
            }
        };
    }
}