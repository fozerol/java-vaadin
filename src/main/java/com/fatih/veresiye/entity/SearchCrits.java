/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fatih.veresiye.entity;

/**
 *
 * @author fatih
 */
public class SearchCrits {
    private boolean perfectsearch;
    private int status;
    private int debpted;
    private int searchby;
    private String searchtext;

    public SearchCrits(boolean perfectsearch, int status, int debpted, int searchby, String searchtext) {
        this.perfectsearch = perfectsearch;
        this.status = status;
        this.debpted = debpted;
        this.searchby = searchby;
        this.searchtext = searchtext;
    }

    public boolean isPerfectsearch() {
        return perfectsearch;
    }

    public void setPerfectsearch(boolean perfectsearch) {
        this.perfectsearch = perfectsearch;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDebpted() {
        return debpted;
    }

    public void setDebpted(int debpted) {
        this.debpted = debpted;
    }

    public int getSearchby() {
        return searchby;
    }

    public void setSearchby(int searchby) {
        this.searchby = searchby;
    }

    public String getSearchtext() {
        return searchtext;
    }

    public void setSearchtext(String searchtext) {
        this.searchtext = searchtext;
    }
    public String getSearchSql (){
   String sqltext = new String("");
   if (status == 2)
       sqltext += " and status=true";
   else if  (status == 3)
        sqltext += " and status=false";
   /*if (debpted == 1)
       sqltext = sqltext + "and =true";*/
   if (searchby == 1 & perfectsearch)
       sqltext += " and (name='"+searchtext+"' or ownername='"+searchtext+"')";
   else if (searchby == 1 & !perfectsearch)
       sqltext += " and (name like '%"+searchtext+"%' or ownername like '%"+searchtext+"%')";
   else if (searchby == 2 & perfectsearch)
       sqltext += " and name='"+searchtext+"'";
   else if (searchby == 2 & !perfectsearch)
       sqltext += " and name like '%"+searchtext+"%'";
   else if (searchby == 3 & perfectsearch)
       sqltext += " and ownername='"+searchtext+"'";
   else if (searchby == 3 & !perfectsearch)
       sqltext += " and ownername like '%"+searchtext+"%'";
   return sqltext;
    }
}
