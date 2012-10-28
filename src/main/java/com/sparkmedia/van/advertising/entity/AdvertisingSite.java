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
public class AdvertisingSite implements Serializable {
    private long id;
    private String typeName;
    private int contentNumber;
    private List<Long> contentId;
    private Enum stat;
    private Date updateDate;

    private static final long serialVersionUID = 7312438658961498567L;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getContentNumber() {
        return contentNumber;
    }

    public void setGetContentNumber(int contentNumber) {
        this.contentNumber = contentNumber;
    }

    public List<Long> getContentId() {
        return contentId;
    }

    public void setContentId(List<Long> contentId) {
        this.contentId = contentId;
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
