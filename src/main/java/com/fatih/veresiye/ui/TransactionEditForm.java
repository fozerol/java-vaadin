/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fatih.veresiye.ui;

import com.fatih.veresiye.backend.TransactionService;
import com.fatih.veresiye.entity.Taction;
import static com.fatih.veresiye.ui.HomeScreen.sessionfactory;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import org.vaadin.viritin.BeanBinder;

/**
 *
 * @author fatih
 */
public class TransactionEditForm extends Window {
    private FormLayout layout;
    private TextField amount;
    private TextField id;
    private TextField description;
    private ComboBox income;
    private Label transactiondate;
    private Button savebtn;
    private Button deletebtn;
    private Button cancelbtn;
    private Taction transaction;
    private TransactionService service;
    HorizontalLayout buttons;

    public TransactionEditForm(Taction transaction1) {
            this.transaction=transaction1;
            this.setCaption("transactioneditform");
            layout = new FormLayout();
            id = new TextField("ID");
            amount = new TextField("Tutar");
            description = new TextField("Açıklama");
            income = new ComboBox("Borç/Alacak");
            savebtn = new Button("Kaydet");
            deletebtn = new Button("Sil");
            cancelbtn = new Button("İptal");
            buttons  = new HorizontalLayout();
            buttons.addComponents(savebtn,cancelbtn,deletebtn);
            layout.setSpacing(true);
            layout.setMargin(true);
            layout.setWidth("500px");
            //this.setWidth("800px");
            transactiondate = new Label();
            service = new TransactionService(sessionfactory.getHibernateSessionFactory());
            setModal(true);
            center();
            initcombo();
            BeanFieldGroup.bindFieldsUnbuffered(transaction,this);
            layout.addComponents(id,amount,description,income,transactiondate,buttons);
            id.setVisible(false);
            description.setNullRepresentation("");
            savebtn.addClickListener(e->{
                if (service.Save(transaction))
                    getUI().removeWindow(this);
            });
            deletebtn.addClickListener(e -> {
                if (service.Delete(transaction))
                    getUI().removeWindow(this);
            });
            cancelbtn.addClickListener(e -> {
                getUI().removeWindow(this);
            });
            
//            addComponents(layout);
            amount.focus();
            setContent(layout);
            
    }

    private void initcombo() {
        income.addItem(1);
        income.setItemCaption(1, "Borç");
        income.addItem(-1);
        income.setItemCaption(-1, "Alacak");
        income.setValue(1);
        income.setNullSelectionAllowed(false);
    }
    public void setTransaction(Taction transaction1) {
        this.transaction = transaction1;
        BeanFieldGroup.bindFieldsUnbuffered(transaction,this);
        
    }
}
