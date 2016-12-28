/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fatih.veresiye.backend;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author fatih
 */
public class HibernateSessionFactory {
    private final  Configuration configuration = new Configuration().configure();
    private SessionFactory sessionfactory = configuration.configure().buildSessionFactory();    

    public SessionFactory getHibernateSessionFactory() {
        return sessionfactory;
    }
}

