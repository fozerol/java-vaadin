/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fatih.veresiye.backend;

import com.fatih.veresiye.entity.Taction;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
/**
 *
 * @author fatih
 */
public class TransactionService {

    private SessionFactory sessionfactory;
    private Session session;
    private Taction transaction;
    List<Taction> transactionlist;
public TransactionService(SessionFactory sessionfactory)
{
    this.sessionfactory=sessionfactory;
}
    
    public List<Taction> getAll(int customerid) {
    //session=new Configuration().configure().buildSessionFactory().openSession();           
    session=sessionfactory.openSession();
    Query query = session.createSQLQuery("select t.*,'Borc' income_text from transactions t where 1=1 and customerid="+customerid).addEntity(Taction.class);;
    //String hql = "from Customer";
    //Query query = session.createQuery(hql);
    transactionlist = query.list();
    session.close();
    return transactionlist;
    }
    public boolean Save(Taction transaction){
        session=sessionfactory.openSession();
        this.transaction = transaction;
        
        Transaction t=session.beginTransaction();  
    try {
        //session.persist(customer);
        session.saveOrUpdate(transaction);
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
    public boolean Delete(Taction transaction){
        session=sessionfactory.openSession();
        this.transaction=transaction;
        Transaction t=session.beginTransaction();  
    try {
        session.delete(transaction);
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
