/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fatih.veresiye.ui;

import com.fatih.veresiye.backend.CustomerService;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import java.util.Date;
import com.fatih.veresiye.entity.Customer;
import static com.fatih.veresiye.ui.HomeScreen.sessionfactory;
import com.vaadin.ui.Button;
import com.vaadin.ui.Window;
import com.fatih.veresiye.ui.ModalCustomer;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
/**
 *
 * @author fatih
 */
public class CustomerForm extends FormLayout {
    
    private TextField id;
    private Label createdate;
    private TextField name;
    private TextField ownername;
    private TextField phonenumber;
    private TextField address;
    private TextField city;
    private TextField district;
    private TextField taxadmin;
    private TextField taxnumber;
    private TextField barrowlimit;
    private CheckBox status;
    private HorizontalLayout hlayout1;
    private Button savebtn;
    private Button deletebtn;
    Customer customer;
    CustomerService service;
    Window window;
    
    public CustomerForm(Customer customer,Window window) {
     this.customer=customer;
     this.window = window;
     //this.id.setNullRepresentation("");
     id = new TextField("Cari Kodu");
     createdate = new Label("Tarih");
     name = new TextField("Ünvan");
     name.setNullRepresentation("");
     ownername = new TextField("Yetkili");
     phonenumber = new TextField("Tel");
     address = new TextField("Adres");
     city = new TextField("İl");
     district = new TextField("İlçe");
     taxadmin = new TextField("Vergi Dairesi");
     taxnumber = new TextField("Vergi No:");
     barrowlimit = new TextField("Borç Limiti");
     status = new CheckBox("Aktif/Pasif");
     hlayout1 = new HorizontalLayout();
     savebtn = new Button("Kaydet");
     deletebtn = new Button("Sil");
     service = new CustomerService(sessionfactory.getHibernateSessionFactory());
     hlayout1.addComponents(savebtn,deletebtn);
     BeanFieldGroup.bindFieldsUnbuffered(customer,this);
     savebtn.addClickListener(e -> {
     if (service.Save(customer))
     {
//getUI().close();
         getUI().getWindows().iterator().next().close();
     }
     });
    deletebtn.addClickListener(e -> {
         if  (service.Delete(customer))
         {
           getUI().getWindows().iterator().next().close();
         }
     });

     addComponents(id,createdate,name,ownername,phonenumber,address,city,district,taxadmin,taxnumber,barrowlimit,status,hlayout1);
     for (Component child : this) {
     if (child instanceof TextField )   
         ((TextField) child).setNullRepresentation("");
     }
     this.setWidth("400px");
     this.setSpacing(true);
     this.setMargin(true);
    }
    public void setCustomer (Customer customer) {
        this.customer=customer;
        BeanFieldGroup.bindFieldsUnbuffered(customer, this);
    }
    
}
