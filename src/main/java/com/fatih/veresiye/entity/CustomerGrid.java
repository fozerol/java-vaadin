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
public class CustomerGrid extends Customer {
    private double total;
    private String status_text;
    private Customer customer;
    public Customer getCustomer () {
        customer = new Customer();
        customer.setId(this.getId());
        customer.setAddress(this.getAddress());
        customer.setBarrowlimit(this.getBarrowlimit());
        customer.setCity(this.getCity());
        customer.setDistrict(this.getDistrict());
        customer.setName(this.getName());
        customer.setOwnername(this.getOwnername());
        customer.setPhonenumber(this.getPhonenumber());
        customer.setStatus(this.isStatus());
        customer.setTaxadmin(this.getTaxadmin());
        customer.setTaxnumber(this.getTaxnumber());
        return customer;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getStatus_text() {
        return status_text;
    }

    public void setStatus_text(String status_text) {
        this.status_text = status_text;
    }
}
