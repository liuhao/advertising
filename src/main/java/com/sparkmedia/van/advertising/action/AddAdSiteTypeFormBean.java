package com.sparkmedia.van.advertising.action;

/**
 * Created with IntelliJ IDEA.
 * User: D06LH
 * Date: 12-11-15
 * Time: 上午11:37
 * The Form Bean to collect adSiteTypes request parameters.
 */
public class AddAdSiteTypeFormBean {
    private String typeName;
    private String adContents;  // JSON format of ad contents list.

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getAdContents() {
        return adContents;
    }

    public void setAdContents(String adContents) {
        this.adContents = adContents;
    }
}
