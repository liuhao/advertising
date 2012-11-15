package com.sparkmedia.van.advertising.action;

/**
 * Created with IntelliJ IDEA.
 * User: D06LH
 * Date: 12-10-22
 * Time: 下午3:29
 * this bean class handle request data, then send them to respond jsp page.
 */
public class ShowAdSitesFormBean {
    private int curPage = 1;
    private int pageSize = 20;
    private long typeId = 0;

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

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }
}
