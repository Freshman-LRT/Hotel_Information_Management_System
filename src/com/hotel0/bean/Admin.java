package com.hotel0.bean;

public class Admin {
    private String user_id;
    private String user_name;
    private String user_password;
    private String user_tel;


    @Override
    public String toString() {
        return "Admin{" +
                "user_id='" + user_id + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_password='" + user_password + '\'' +
                ", user_tel='" + user_tel + '\'' +
                '}';
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_tel() {
        return user_tel;
    }

    public void setUser_tel(String user_tel) {
        this.user_tel = user_tel;
    }

    public Admin(String user_id, String user_name, String user_password, String user_tel) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_password = user_password;
        this.user_tel = user_tel;
    }


}
