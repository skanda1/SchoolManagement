package com.example.skanda.schoolmanagement;

/**
 * Created by Suhas on 30-06-2018.
 */

public class Teacher {

    String name,password,std,sec,email,address,phone;
  String id;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStd() {
        return std;
    }

    public void setStd(String std) {
        this.std = std;
    }

    public String getSec() {
        return sec;
    }

    public void setSec(String sec) {
        this.sec = sec;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Teacher(String id,String name, String password, String std, String sec, String email, String address, String phone) {
        this.name = name;
        this.password = password;
        this.std = std;
        this.sec = sec;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    public Teacher(String name,String std,String sec)
    {
        this.name=name;
        this.std=std;
        this.sec=sec;
    }
    Teacher()
    {}



}
