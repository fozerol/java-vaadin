/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fatih.veresiye.ui.component;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author fatih
 */
public class Filter extends VerticalLayout {
    private HorizontalLayout hlayout;
    private ComboBox filterstatus;
    private ComboBox filterby;
    private TextField searchtext;

    public Filter() {
        hlayout = new HorizontalLayout();
        filterstatus = new ComboBox("Durumu");
        filterby = new ComboBox("Borç/Alacak");
        searchtext = new TextField();
        searchtext.setId("5");
        initfilterstatus();
        initfilterby();
        hlayout.addComponents(filterstatus,filterby);
        searchtext.setWidth("100%");
        setWidth(String.valueOf(hlayout.getWidth()));
        addComponents(hlayout,searchtext);
        //setMargin(true);
        setSpacing(true);
    }

    private void initfilterby() {
        filterstatus.addItem(1);
        filterstatus.setItemCaption(1, "Tümü");
        filterstatus.addItem(2);
        filterstatus.setItemCaption(2, "Aktif");
        filterstatus.addItem(3);
        filterstatus.setItemCaption(3, "Pasif");
        filterstatus.setId("2");
        filterstatus.setValue(1);
        filterstatus.setNullSelectionAllowed(false);
    }

    private void initfilterstatus() {
        filterby.addItem(1);
        filterby.setItemCaption(1, "Tümü");
        filterby.addItem(2);
        filterby.setItemCaption(2, "Borçlu");
        filterby.addItem(3);
        filterby.setItemCaption(3, "Alacaklı");
        filterby.setId("3");
        filterby.setValue(1);
        filterby.setNullSelectionAllowed(false);
    }
}
