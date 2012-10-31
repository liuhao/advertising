package com.sparkmedia.van.advertising.entity;

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
    private long typeId;
    private List<AdContent> adContents;
    private int stat;
    private Date updateDate;

    private static final long serialVersionUID = 7312438658961498567L;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }

    public List<AdContent> getAdContents() {
        return adContents;
    }

    public void setAdContents(List<AdContent> adContents) {
        this.adContents = adContents;
    }

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
