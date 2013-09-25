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
    private long layerId = 0;
    private long boxId = 0;

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

    public long getLayerId() {
        return layerId;
    }

    public void setLayerId(long layerId) {
        this.layerId = layerId;
    }

    public long getBoxId() {
        return boxId;
    }
    public void setBoxId(long boxId) {
        this.boxId = boxId;
    }
}
