package com.example.pageapp.models;

public class Student {
    private String email;
    private String phone;

    private String userName;

    public Student(){

    }
    public Student(String phone, String email,String userName) {
        this.phone = phone;
        this.email = email;
        this.userName=userName;
    }
    public String getuserName() {
        return userName;
    }

    public void setuserName(String userName) {
        this.userName = userName;
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
}
