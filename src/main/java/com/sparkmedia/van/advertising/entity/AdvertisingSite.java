package com.sparkmedia.van.advertising.entity;

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
public class AdvertisingSite {
    private long id;
    private String typeName;
    private List<Long> contentId;
    private Enum stat;

    public AdvertisingSite() {
    }
}
