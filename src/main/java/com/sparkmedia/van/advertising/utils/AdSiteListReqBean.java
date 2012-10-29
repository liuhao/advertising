package com.sparkmedia.van.advertising.utils;

/**
 * Created with IntelliJ IDEA.
 * User: D06LH
 * Date: 12-10-22
 * Time: 下午3:29
 * this bean class handle request data, then send them to respond jsp page.
 */
public class AdSiteListReqBean {
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
