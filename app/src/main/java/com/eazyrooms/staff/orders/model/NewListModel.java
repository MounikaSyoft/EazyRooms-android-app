package com.eazyrooms.staff.orders.model;

public class NewListModel {

    public String OrderName;
    public String RoomNumber;
    public String DateTime;

    public NewListModel(String orderName, String roomNumber, String dateTime) {
        OrderName = orderName;
        RoomNumber = roomNumber;
        DateTime = dateTime;
    }

    public String getOrderName() {
        return OrderName;
    }

    public void setOrderName(String orderName) {
        OrderName = orderName;
    }

    public String getRoomNumber() {
        return RoomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        RoomNumber = roomNumber;
    }

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }

}
