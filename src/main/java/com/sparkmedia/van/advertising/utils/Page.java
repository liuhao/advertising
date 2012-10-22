package com.sparkmedia.van.advertising.utils;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: D06LH
 * Date: 12-10-19
 * Time: 下午12:28
 * To change this template use File | Settings | File Templates.
 */
public class Page<T> {

    private int curPage;

    private int pageCount;

    private int totalRecords;

    private List<T> results;

    public Page(){}

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords,int pageSize) {
        this.totalRecords = totalRecords;
        if(totalRecords==0){
            this.pageCount = 0;
        }else{
            this.pageCount = (totalRecords-1)/pageSize+1;
        }
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }


}
