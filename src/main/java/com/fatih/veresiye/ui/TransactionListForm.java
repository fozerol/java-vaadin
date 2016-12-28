/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fatih.veresiye.ui;

import com.fatih.veresiye.backend.TransactionService;
import com.fatih.veresiye.entity.Taction;
import com.fatih.veresiye.entity.Taction;
import static com.fatih.veresiye.ui.HomeScreen.sessionfactory;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.util.List;

/**
 *
 * @author fatih
 */
public class TransactionListForm extends Window {
    
    private VerticalLayout mainlayout = new VerticalLayout();
    private VerticalLayout vlayout = new VerticalLayout();
    private HorizontalLayout hlayout = new HorizontalLayout();
    private Grid grid = new Grid();
    private Button backbtn = new Button("Geri");
    private Button newbtn = new Button("Yeni");
    private Button editbtn = new Button("Düzenle");
    private int customerid;
    private List<Taction> transactions;
    private Taction transaction;
    private TransactionEditForm transactioneditform;
    private final TransactionService service = new TransactionService(sessionfactory.getHibernateSessionFactory());

    public TransactionListForm(int customerid,HomeScreen homescreen) {
        mainlayout.setSizeFull();
        mainlayout.setSpacing(true);
        mainlayout.setMargin(true);
        mainlayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        vlayout.setSpacing(true);
        vlayout.setMargin(true);
        vlayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        transaction = new Taction();
        transaction.setCustomerid(customerid);
        transactioneditform = new TransactionEditForm(transaction);
        this.customerid = customerid;
        hlayout.addComponents(backbtn, newbtn, editbtn);
        vlayout.addComponents(grid, hlayout);
        mainlayout.addComponent(vlayout);
        setModal(true);
        center();
        backbtn.addClickListener(e -> {
//                getUI().getWindows().iterator().next().close();
            getUI().removeWindow(this);
            homescreen.updategrid();
        });
        newbtn.addClickListener(e -> {
            transaction = new Taction();
            transaction.setCustomerid(customerid);
            transactioneditform.setTransaction(transaction);
            getUI().addWindow(transactioneditform);
        });
        transactioneditform.addCloseListener(e -> {
            updategrid();
        });
        /*grid.addItemClickListener(e -> {
                if (e.isDoubleClick()) {
                             transactioneditform.setTransaction((Transaction)grid.getSelectedRow());
                             getUI().addWindow(transactioneditform);
                            }
            });*/
 /*grid.addSelectionListener(e->{
                        if (grid.getSelectedRow() != null)
                            transactioneditform.setTransaction((Transaction)grid.getSelectedRows().iterator().next());
            });*/
        editbtn.addClickListener(e -> {
            if (grid.getSelectedRow() != null) {
                transactioneditform.setTransaction((Taction) grid.getSelectedRows().iterator().next());
                getUI().addWindow(transactioneditform);
            }
        });
        mainlayout.setMargin(true);
        mainlayout.setSpacing(true);
        this.setResizable(false);
        this.setWidth("600px");
        this.setHeight("600px");
        this.setClosable(true);
        setContent(mainlayout);
        updategrid();
        grid.getColumn("customerid").setHidden(true);
        grid.getColumn("id").setHidden(true);
        grid.getColumn("income").setHidden(true);
        
    }

    public void updategrid() {
        transactions = service.getAll(customerid);
        grid.setContainerDataSource(new BeanItemContainer<>(Taction.class, transactions));
    }
}
