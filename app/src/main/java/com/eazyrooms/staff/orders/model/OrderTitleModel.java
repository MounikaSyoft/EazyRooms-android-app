package com.eazyrooms.staff.orders.model;

import android.widget.TextView;

public class OrderTitleModel {

    public TextView OrderTitle;

    public OrderTitleModel(TextView orderTitle) {
        OrderTitle = orderTitle;
    }

    public TextView getOrderTitle() {
        return OrderTitle;
    }

    public void setOrderTitle(TextView orderTitle) {
        OrderTitle = orderTitle;
    }


}
