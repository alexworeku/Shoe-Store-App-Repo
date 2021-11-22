package com.example.shoestoreapp.Models;

public class Admin {
    private int id;
    private String username;
    private String password;
    private boolean loginStatus;


    public Admin( String username, String password,boolean status) {
        this.username = username;
        this.password = password;
        loginStatus=status;

    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isLoggedIn() {
        return loginStatus;
    }
}
