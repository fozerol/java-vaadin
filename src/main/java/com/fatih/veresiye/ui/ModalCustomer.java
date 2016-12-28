/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fatih.veresiye.ui;
import com.fatih.veresiye.entity.Customer;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Window;
import org.apache.commons.beanutils.BeanUtils;
import static org.apache.commons.beanutils.BeanUtils.copyProperties;
import org.apache.commons.beanutils.BeanUtilsBean;


/**
 *
 * @author fatih
 */

public class ModalCustomer extends Window {
    private Customer customer;
    public ModalCustomer() {
        setModal(true);
        center();
        
        //setContent(new CustomerForm(new Customer(),this));
        }
/*        public ModalCustomer(Customer customer) {
        this.customer=customer;
        setModal(true);
        center();
        setContent(new CustomerForm(customer,this));
        }*/
        public void setCustomerContent(Customer customer) {

        this.customer = customer;
        setContent(new CustomerForm(this.customer,this));
        }
    }
