/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fatih.veresiye.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 *
 * @author fatih
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@Table(name= "customers")  
public class Customer {
@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    //private int code;
    private Date createdate;
    private String name;
    private String ownername;
    private String phonenumber;
    private String address;
    private String city;
    private String district;
    private String taxadmin;
    private int    taxnumber;
    private double barrowlimit;
    private boolean status;
 /*   @Column(insertable = false, updatable = false)
    private double total;*/
    
    //private double initialbarrow;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
            
    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getTaxadmin() {
        return taxadmin;
    }

    public void setTaxadmin(String taxadmin) {
        this.taxadmin = taxadmin;
    }

    public int getTaxnumber() {
        return taxnumber;
    }

    public void setTaxnumber(int taxnumber) {
        this.taxnumber = taxnumber;
    }

    public double getBarrowlimit() {
        return barrowlimit;
    }

    public void setBarrowlimit(double barrowlimit) {
        this.barrowlimit = barrowlimit;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    /*public void setTotal(double total) {
        this.total = total;
    }
    
    public double  getTotal() {
        return total;
    }*/

    /*public void setInitialbarrow (double initialbarrow) {
        this.initialbarrow=initialbarrow;
    }
    public double getInitialbarrow(){
        return initialbarrow;
    }*/
}
