package com.example.smartbrta;

import com.firebase.ui.database.FirebaseRecyclerAdapter;

public class DataSetFire {


    String name;
    String reg;
    String gender;
    String amount;
    String status;
    String address;
    String vehicle;
    String mobile;
    String id;



    public DataSetFire() {
    }

    public DataSetFire(String name, String reg, String gender, String amount, String status, String address, String vehicle, String mobile, String id) {
        this.name = name;
        this.reg = reg;
        this.gender = gender;
        this.amount = amount;
        this.status = status;
        this.address = address;
        this.vehicle = vehicle;
        this.mobile = mobile;
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
