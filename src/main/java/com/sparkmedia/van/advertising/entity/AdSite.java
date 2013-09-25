package com.sparkmedia.van.advertising.entity;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: D06LH
 * Date: 12-10-17
 * Time: 下午2:47
 * Advertising Site include a group of AdvertisingContents,
 * it generally represent a complete package for the customers.
 */
public class AdSite {
    private long id;
    private long layerId;
    private long boxId;
    private String uri;
    private int stat;
    private String updateDate;

    private static final long serialVersionUID = 7312438658961498567L;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
