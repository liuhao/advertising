package com.sparkmedia.van.advertising.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: D06LH
 * Date: 12-10-29
 * Time: 下午1:50
 * To change this template use File | Settings | File Templates.
 */
public class AdSiteType implements Serializable {
    private long id;
    private String typeName;
    private List<AdContent> adContentList;

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

    public List<AdContent> getAdContentList() {
        return adContentList;
    }

    public void setAdvContentList(List<AdContent> adContentList) {
        this.adContentList = adContentList;
    }

    public void objToString() {
        ;
    }

    public AdSiteType stringToObj() {
        return this;
    }
}
