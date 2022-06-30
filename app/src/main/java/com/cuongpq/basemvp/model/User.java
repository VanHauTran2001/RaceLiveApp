package com.cuongpq.basemvp.model;
public class User {
    private String email;
    private String passWord;

    public User(String email, String passWord) {
        this.email = email;
        this.passWord = passWord;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

}
