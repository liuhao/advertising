package com.sparkmedia.van.advertising.utils;

/**
 * Created with IntelliJ IDEA.
 * User: D06LH
 * Date: 12-11-14
 * Time: 上午11:45
 * To change this template use File | Settings | File Templates.
 */
public class AdSiteTypeListReqBean {
    private int curPage = 1;
    private int pageSize = 20;
    private String keyword = "No keyword specified";

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
