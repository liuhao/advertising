package com.sparkmedia.van.advertising.entity;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: D06LH
 * Date: 12-10-29
 * Time: 下午1:50
 * Define the type of different AdSite.
 */
public class AdLayer {
    private long id;
    private String layerName;
    private List<Long> adBoxIds;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLayerName() {
        return layerName;
    }

    public void setLayerName(String layerName) {
        this.layerName = layerName;
    }

    public List<Long> getAdBoxIds() {
        return adBoxIds;
    }

    public void setAdBoxIds(List<Long> adBoxIds) {
        this.adBoxIds = adBoxIds;
    }
}
