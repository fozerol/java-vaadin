/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fatih.veresiye.ui;

import com.vaadin.ui.Component;
import com.vaadin.ui.HasComponents;
import java.util.Iterator;

/**
 *
 * @author fatih
 */
public class FindComponent {
    
    public Component findById(HasComponents root, String id) {
        System.out.println("findById called on " + root);

        Iterator<Component> iterate = root.iterator();
        while (iterate.hasNext()) {
            Component c = iterate.next();
            if (id.equals(c.getId())) {
                return c;
            }
            if (c instanceof HasComponents) {
                Component cc = findById((HasComponents) c, id);
                if (cc != null)
                    return cc;
            }
        }
        return null;
    }
}
    
