package com.sparkmedia.van.advertising.action;

/**
 * Created with IntelliJ IDEA.
 * User: D06LH
 * Date: 12-11-14
 * Time: 上午11:45
 * The Form Bean to collect adSiteTypes request parameters.
 */
public class ShowAdSiteTypesFormBean {
    private int curPage = 1;
    private int pageSize = 20;

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
}
