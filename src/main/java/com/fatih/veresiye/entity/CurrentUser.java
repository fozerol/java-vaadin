/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fatih.veresiye.entity;

/**
 *
 * @author fatih
 */
public class CurrentUser {
    private String user;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    public static boolean isLoggedIn(){
        return false;
    }
}
