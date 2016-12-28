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
import javax.persistence.Table;

/**
 *
 * @author fatih
 */
@Entity  
@Table(name= "transactions")  
public class Taction {
@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private int customerid;
    private Date transactiondate;
    private String description;
    private int income = -1;
    private double amount;
    @Column(insertable = false, updatable = false)
    private String income_text;

    /*public Taction() {
        
    }*/
    /*public Taction(int customerid) {
        this.customerid = customerid;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public Date getTransactiondate() {
        return transactiondate;
    }

    public void setTransactiondate(Date transactiondate) {
        this.transactiondate = transactiondate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }
    public void setAmount(double amount) {
        this.amount=amount;
    }
    public double getAmount() {
        return amount;
    }
    public String getIncome_text() {
        return income_text;
    }

    public void setIncome_text(String income_text) {
        this.income_text = income_text;
    }
}
