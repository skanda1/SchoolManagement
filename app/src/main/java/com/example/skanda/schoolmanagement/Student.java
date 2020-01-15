package com.example.skanda.schoolmanagement;

/**
 * Created by Suhas on 04-07-2018.
 */

public class Student {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
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

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getCall() {
        return call;
    }

    public void setCall(String call) {
        this.call = call;
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

    public Student(String name) {
        this.name = name;

    }

    public void setId(int id) {
        this.id = id;
    }

    int id;
    String name;
    String pass;

    public int getId() {
        return id;
    }




    String std;
    String sec;
    String fname;
    String mname;
    String call;
    String email;
    String address;

    public Student(String b,String s,String se)
    {
        std=s;
        sec=se;
    }
}
