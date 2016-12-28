/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fatih.veresiye.ui;

import com.fatih.veresiye.backend.CustomerService;
import com.fatih.veresiye.backend.HibernateSessionFactory;
import com.fatih.veresiye.entity.Customer;
import com.fatih.veresiye.entity.CustomerGrid;
import com.fatih.veresiye.entity.SearchCrits;
import com.fatih.veresiye.ui.component.Filter;
import com.fatih.veresiye.ui.component.SearchBy;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fatih
 */
public class HomeScreen extends VerticalLayout {

    private VerticalLayout mainlayout = new VerticalLayout();
    private SearchBy searchby = new SearchBy();
    private Filter filter = new Filter();
    private Grid customergrid = new Grid();
    private Grid transactionlist = new Grid();
    private Button newcustomerbtn = new Button("Yeni Cari");
    private Button editcustomerbtn = new Button("Değiştir");
    public static HibernateSessionFactory sessionfactory = new HibernateSessionFactory();
    private CustomerService service = new CustomerService(sessionfactory.getHibernateSessionFactory());
    private HorizontalLayout upperbarlayout = new HorizontalLayout();
    private ModalCustomer modalcustomer;
    private SearchCrits searchcrits;
    private TransactionListForm transactionlistform;
    FindComponent fc = new FindComponent();

    public HomeScreen() {
        customergrid.setWidth("900px");
        customergrid.setHeight("500px");
        upperbarlayout.addComponents(filter, searchby);
        upperbarlayout.setWidth("900px");
        upperbarlayout.setSpacing(true);
        upperbarlayout.setComponentAlignment(searchby,Alignment.TOP_RIGHT);
        mainlayout.setWidth("900px");
        //mainlayout.setSizeFull();
        mainlayout.setSpacing(true);
        HorizontalLayout bottombar = new HorizontalLayout();
        bottombar.setWidth("900px");
        bottombar.setSpacing(true);
        bottombar.addComponents(newcustomerbtn, editcustomerbtn);
        bottombar.setComponentAlignment(editcustomerbtn, Alignment.MIDDLE_RIGHT);
        mainlayout.addComponents(upperbarlayout, customergrid, bottombar);
        setSizeFull();
        ((TextField) fc.findById(upperbarlayout, "5")).addTextChangeListener(e -> {
            ((TextField) fc.findById(upperbarlayout, "5")).setValue(e.getText());
            populatevalues();
            Notification.show(searchcrits.getSearchSql());
            updategrid();
        });
        ((ComboBox) fc.findById(upperbarlayout, "2")).addValueChangeListener(e-> {
            populatevalues();
            updategrid();
        });
        ((ComboBox) fc.findById(upperbarlayout, "3")).addValueChangeListener(e-> {
            populatevalues();
            updategrid();
        });

        setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        addComponent(mainlayout);
        setComponentAlignment(mainlayout, Alignment.MIDDLE_CENTER);
        modalcustomer = new ModalCustomer();
        newcustomerbtn.addClickListener(e -> {
            try {
                modalcustomer.setCustomerContent(new Customer());
            } catch (Exception ex) {
                Logger.getLogger(HomeScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
            getUI().addWindow(modalcustomer);
        });
        editcustomerbtn.addClickListener(e -> {
            if (customergrid.getSelectedRow() == null) {
                Notification.show("Herhangi Bir Seçim Yapılmadı");
            } else {
                    modalcustomer.setCustomerContent(((CustomerGrid)customergrid.getSelectedRows().iterator().next()).getCustomer());
                }
                getUI().addWindow(modalcustomer);
        });
        updategrid();
        initcustomergrid();
        modalcustomer.addCloseListener(new CloseListener() {
            public void windowClose(CloseEvent e) {
                Notification.show("Close listener");
                updategrid();
            }
        });
        customergrid.addItemClickListener(e -> {
            if (e.isDoubleClick()) {
                /*Transaction transaction = new Transaction();
                transaction.setAmount(500);
                transaction.setDescription("deneme");*/
                transactionlistform = new TransactionListForm((int)e.getItem().getItemProperty("id").getValue(),this);
                getUI().addWindow(transactionlistform);
            }

        });

    }
    public void initcustomergrid()
    {
        customergrid.getColumn("address").setHidden(true);
        customergrid.getColumn("id").setHidden(true);
        customergrid.getColumn("taxnumber").setHidden(true);
        customergrid.getColumn("taxadmin").setHidden(true);
        customergrid.getColumn("phonenumber").setHidden(true);
        customergrid.getColumn("status").setHidden(true);
        customergrid.getColumn("createdate").setHidden(true);
        customergrid.getColumn("customer").setHidden(true);
        customergrid.getColumn("barrowlimit").setHidden(true);
        
        customergrid.setColumnOrder("name","ownername","city","district","phonenumber","total","status_text");
    }
    public void updategrid() {
        populatevalues();
        List<CustomerGrid> customers = service.getAll(searchcrits.getSearchSql());
        customergrid.setContainerDataSource(new BeanItemContainer<>(CustomerGrid.class, customers));
    }
    /*public void updatetransactiongrid() {
        //populatevalues();
        //String s = ((ComboBox)(upperbarlayout.getComponent(1)).getValue();
        List<Transaction> transactions = transactionservice.getAll(searchcrits.getSearchSql());
        customergrid.setContainerDataSource(new BeanItemContainer<>(Customer.class, customers));
    }*/

    private void populatevalues() {
        boolean perfectsearch;
        int status;
        int filterby;
        int searchbytext;
        String searchtext = new String();

        searchbytext = (int) ((ComboBox) fc.findById(upperbarlayout, "1")).getValue();
        perfectsearch = (boolean) ((CheckBox) fc.findById(upperbarlayout, "4")).getValue();
        status = (int) ((ComboBox) fc.findById(upperbarlayout, "2")).getValue();
        filterby = (int) ((ComboBox) fc.findById(upperbarlayout, "3")).getValue();
        searchtext = (String) ((TextField) fc.findById(upperbarlayout, "5")).getValue();
        searchcrits = new SearchCrits(perfectsearch, status, filterby, searchbytext, searchtext);
    }
}
