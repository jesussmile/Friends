package com.example.pannam.friends;

/**
 * Created by pannam on 2/16/2016.
 */
public class Friend {
    private int _id;
    private String name;
    private String phone;
    private String email;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Friend(int _id, String email, String name, String phone) {
        this._id = _id;
        this.email = email;

        this.name = name;
        this.phone = phone;
    }
}
