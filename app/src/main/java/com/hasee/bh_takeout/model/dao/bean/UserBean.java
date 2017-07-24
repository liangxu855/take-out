package com.hasee.bh_takeout.model.dao.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName = "t_user")
public class UserBean implements Serializable{
    @DatabaseField(id = true)
    public int _id;
//    @ForeignCollectionField(eager = true)
//    private ForeignCollection<AddressBean> addressList;
    @DatabaseField()
    public String name;
    @DatabaseField()
    public float balance;
    @DatabaseField()
    public int discount;
    @DatabaseField()
    public int integral;
    @DatabaseField()
    public String phone;
    @DatabaseField
    public boolean login;



    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public UserBean() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public UserBean(int _id, String name, float balance, int discount, int integral, String phone, boolean login) {
        this._id = _id;
        this.name = name;
        this.balance = balance;
        this.discount = discount;
        this.integral = integral;
        this.phone = phone;
        this.login = login;
    }
    //    public ForeignCollection<AddressBean> getAddressList() {
//        return addressList;
//    }
//
//    public void setAddressList(ForeignCollection<AddressBean> addressList) {
//        this.addressList = addressList;
//    }
}
