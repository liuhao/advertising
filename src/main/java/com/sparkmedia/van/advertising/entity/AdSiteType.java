package com.sparkmedia.van.advertising.entity;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: D06LH
 * Date: 12-10-29
 * Time: 下午1:50
 * Define the type of different AdSite.
 */
public class AdSiteType {
    private long id;
    private String typeName;
    private List<AdContent> adContents;

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

    public List<AdContent> getAdContents() {
        return adContents;
    }

    public void setAdContents(List<AdContent> adContents) {
        this.adContents = adContents;
    }
}
