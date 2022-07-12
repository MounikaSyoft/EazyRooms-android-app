package com.eazyrooms.staff.orders;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.eazyrooms.staff.BaseActivity;
import com.eazyrooms.staff.R;
import com.eazyrooms.staff.databinding.ActivityOrdersListBinding;
import com.eazyrooms.staff.login_model.OTPValidationResponse;
import com.eazyrooms.staff.orders.adapter.NewListAdapter;
import com.eazyrooms.staff.orders.adapter.OrderTitleAdapter;
import com.eazyrooms.staff.preferences.AppSPManager;

import java.util.ArrayList;


public class OrdersListActivity extends BaseActivity {

    public ActivityOrdersListBinding ordersListBinding;
    private OrderTitleAdapter orderTitlesAdapter;
    private OTPValidationResponse otpValidationResponse = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = findViewById(R.id.contentLayout);
        ordersListBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_orders_list, layout, true);
        topBarSetup();
        setRecyclerViewData();
        setNameValue();

        String req = ordersListBinding.tvRequest.getText().toString();
        ordersListBinding.trRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = (new Intent(getApplicationContext(),OrderStatusActivity.class));
                i.putExtra("categories",req);
                startActivity(i);
            }
        });
        String iss = ordersListBinding.tvIssues.getText().toString();
        ordersListBinding.trIssues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = (new Intent(getApplicationContext(),OrderStatusActivity.class));
                i.putExtra("categories",iss);
                startActivity(i);
            }
        });
        String din = ordersListBinding.tvDining.getText().toString();
        ordersListBinding.trDining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = (new Intent(getApplicationContext(),OrderStatusActivity.class));
                i.putExtra("categories",din);
                startActivity(i);
            }
        });
        String wifi = ordersListBinding.tvWifi.getText().toString();
        ordersListBinding.trWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = (new Intent(getApplicationContext(),OrderStatusActivity.class));
                i.putExtra("categories",wifi);
                startActivity(i);
            }
        });

        String checkOut = ordersListBinding.tvCheckOut.getText().toString();
        ordersListBinding.trCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = (new Intent(getApplicationContext(),OrderStatusActivity.class));
                i.putExtra("categories",checkOut);
                startActivity(i);
            }
        });

        String checkIn = ordersListBinding.tvCheckIn.getText().toString();
        ordersListBinding.trCheckin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = (new Intent(getApplicationContext(),OrderStatusActivity.class));
                i.putExtra("categories",checkIn);
                startActivity(i);
            }
        });
        String spaB = ordersListBinding.tvSpa.getText().toString();
        ordersListBinding.trSpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = (new Intent(getApplicationContext(),OrderStatusActivity.class));
                i.putExtra("categories",spaB);
                startActivity(i);
            }
        });

        String lau = ordersListBinding.tvLaundry.getText().toString();
        ordersListBinding.trLaundry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = (new Intent(getApplicationContext(),OrderStatusActivity.class));
                i.putExtra("categories",lau);
                startActivity(i);
            }
        });
        String tra = ordersListBinding.tvTravel.getText().toString();
        ordersListBinding.trTravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = (new Intent(getApplicationContext(),OrderStatusActivity.class));
                i.putExtra("categories",tra);
                startActivity(i);
            }
        });
        String par = ordersListBinding.tvParking.getText().toString();
        ordersListBinding.trParking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = (new Intent(getApplicationContext(),OrderStatusActivity.class));
                i.putExtra("categories",par);
                startActivity(i);
            }
        });

        String tas = ordersListBinding.tvTask.getText().toString();
        ordersListBinding.trTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = (new Intent(getApplicationContext(),OrderStatusActivity.class));
                i.putExtra("categories",tas);
                startActivity(i);
            }
        });
    }

    private void setNameValue() {
        otpValidationResponse = AppSPManager.getOTPResponse(OrdersListActivity.this);
        if (otpValidationResponse != null){

            ordersListBinding.tvStaffName.setText(String.format("%s",otpValidationResponse.getName()));
        }
    }

    private void setRecyclerViewData() {
        ArrayList<String> data = new ArrayList<>();
        data.add("New");
        data.add("Processing");
        data.add("Completed");

        orderTitlesAdapter = new OrderTitleAdapter();
        ordersListBinding.rvOrderTitle.setAdapter(orderTitlesAdapter);
        orderTitlesAdapter.updateData(data);

        orderTitlesAdapter.selectedPositionTitle=new OrderTitleAdapter.SelectedPositionTitle() {
            @Override
            public void onClickTitle(int position) {
                //ordersListBinding.rvOrderData.setAdapter(null);
                /*if(data.get(position).equalsIgnoreCase("New")){
                    newListAdapter=new NewListAdapter();
                    orderStatusBinding.rvOrderData.setAdapter(newListAdapter);
                }else
                if(data.get(position).equalsIgnoreCase("Processing")){
                    newListAdapter=new NewListAdapter();
                    orderStatusBinding.rvOrderData.setAdapter(newListAdapter);
                }*/
            }
        };
       /* newListAdapter = new NewListAdapter();
        orderStatusBinding.rvOrderData.setAdapter(newListAdapter);

        newListAdapter.selectedOrderItem = new NewListAdapter.SelectedOrderItem(){
            @Override
            public void onClickOrderItem(int position) {
                // orderStatusBinding.rvOrderData.setAdapter(newListAdapter);
                startActivity(new Intent(getApplicationContext(),OrderDetailsActivity.class));
            }
        };*/
    }

    private void topBarSetup() {
        centerTextTitle(getString(R.string.orders));
    }
}