/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fatih.veresiye.ui.component;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.VerticalLayout;


/**
 *
 * @author fatih
 */
public class SearchBy extends VerticalLayout {
    private ComboBox searchby;
    private CheckBox fullsearch;

    public SearchBy() {
        searchby = new ComboBox("Arama Alanı");
        fullsearch = new CheckBox("Tam/Kısmi Arama");
        setWidth("200px");
        fullsearch.setId("4");
        initfullsearch();
        addComponents(searchby,fullsearch);
        this.setComponentAlignment(fullsearch, Alignment.BOTTOM_LEFT);
    }

    private void initfullsearch() {
        searchby.addItem(1);
        searchby.setItemCaption(1, "Tümü");
        searchby.addItem(2);
        searchby.setItemCaption(2, "Ad");
        searchby.addItem(3);
        searchby.setItemCaption(3, "Ünvan");
        searchby.setId("1");
        searchby.setValue(1);
        searchby.setNullSelectionAllowed(false);
    }
}
