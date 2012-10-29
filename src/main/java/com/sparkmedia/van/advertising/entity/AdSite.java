package com.sparkmedia.van.advertising.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: D06LH
 * Date: 12-10-17
 * Time: 下午2:47
 * Advertising Site include a group of AdvertisingContents,
 * it generally represent a complete package for the customers.
 */
public class AdSite implements Serializable {
    private long id;
    private long typeId;
    private List<String> contentUri;
    private Enum stat;
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

    public List<String> getContentUri() {
        return contentUri;
    }

    public void setContentUri(List<String> contentUri) {
        this.contentUri = contentUri;
    }

    public Enum getStat() {
        return stat;
    }

    public void setStat(Enum stat) {
        this.stat = stat;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
