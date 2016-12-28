/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fatih.veresiye.entity;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;

/**
 *
 * @author fatih
 */
public class LoginService {
    private String username;
    private String password;
    private final IniSecurityManagerFactory factory;
    private final org.apache.shiro.mgt.SecurityManager securitymanager;
    private Subject subject;
    private UsernamePasswordToken token;
    public LoginService(){
         factory = new IniSecurityManagerFactory("classpath:/shiro.ini");
         securitymanager = factory.getInstance();
         org.apache.shiro.SecurityUtils.setSecurityManager(securitymanager);
         subject = SecurityUtils.getSubject();
         
         
         
        
    }
    public boolean login(){
        try 
        { 
            subject.login(token);
            //if (subject.isAuthenticated())
              //  Notification.show("Login Success");
              return true;
        } catch (AuthenticationException e) {
            //Notification.show("Login failed.", "Hint: use any non-empty strings", Notification.Type.WARNING_MESSAGE);
            //username.focus();
            return false;
        }    
    }
    public UsernamePasswordToken getToken() {
        return token;
    }

    public void setToken(String username,String password) {
        this.token = new UsernamePasswordToken(username,password);
    }
    
}
