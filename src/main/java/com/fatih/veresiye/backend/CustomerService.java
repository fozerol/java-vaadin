/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fatih.veresiye.backend;

import java.util.List;
import com.fatih.veresiye.entity.Customer;
import com.fatih.veresiye.entity.CustomerGrid;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.transform.Transformers;
/**
 *
 * @author fatih
 */
public class CustomerService {

    private SessionFactory sessionfactory;
    private Session session;
    private Customer customer = new Customer();
    List<CustomerGrid> customerlist = new ArrayList<CustomerGrid>();
    public CustomerService(SessionFactory sessionfactory)
{
    this.sessionfactory=sessionfactory;
}
    
    public List<CustomerGrid> getAll(String sqltext) {
        
    //session=new Configuration().configure().buildSessionFactory().openSession();           
    session=sessionfactory.openSession();
    //select a.*,c.total from customers a,(select sum(income*amount) total,customerid from transactions group by customerid) c where c.customerid=a.id;
    //Query query = session.createSQLQuery("select a.*,c.total from customers a,(select sum(income*amount) total,customerid from transactions group by customerid) c where c.customerid=a.id"+sqltext).addEntity(Customer_ext.class);
    Query query = session.createSQLQuery("select * from customers_ext where 1=1"+sqltext);//.addEntity(Customer.class);
    //Query query = session.createSQLQuery("select * from customers where 1=1"+sqltext).addEntity(Customer.class);
    //String hql = "from Customer";
    //Query query = session.createQuery(hql);
    
    //customerlist = query.list();
    customerlist = (List<CustomerGrid>)(query.setResultTransformer(Transformers.aliasToBean(CustomerGrid.class))).list();
    session.close();
    return customerlist;
    }
    public boolean Save(Customer customer){
        session=sessionfactory.openSession();
        this.customer = customer;
        Transaction t=session.beginTransaction();  
    try {
        //session.persist(customer);
        session.saveOrUpdate(this.customer);
        t.commit();
    }
    catch (Exception e) { 
           if (t != null) {
             t.rollback();
          // session.close();
             throw e;
           }
      } 
        session.close();
        return true;
    }
    public boolean Delete(Customer customer){
        session=sessionfactory.openSession();
        this.customer = customer;
        
        Transaction t=session.beginTransaction();  
    try {
        //session.persist(customer);
        session.delete(customer);
        t.commit();
    }
    catch (Exception e) { 
           if (t != null) {
             t.rollback();
          // session.close();
             throw e;
           }
      } 
        session.close();
        return true;
    }    
}  
