package com.eazyrooms.staff.login_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OTPValidationResponse implements Serializable {

    @SerializedName("msg")
    @Expose
    String msg = "";
    @SerializedName("id")
    @Expose
    String id="";
    @SerializedName("name")
    @Expose
    String name="";
    @SerializedName("email")
    @Expose
    String email="";
    @SerializedName("phone")
    @Expose
    String phone="";
    @SerializedName("gender")
    @Expose
    String gender="";
    @SerializedName("address")
    @Expose
    String address="";
    @SerializedName("zip_code")
    @Expose
    String zip_code="";
    @SerializedName("location")
    @Expose
    String location="";
    @SerializedName("refresh")
    @Expose
    String refresh="";
    @SerializedName("access")
    @Expose
    String access="";

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

   /* public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }*/

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRefresh() {
        return refresh;
    }

    public void setRefresh(String refresh) {
        this.refresh = refresh;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }
}
