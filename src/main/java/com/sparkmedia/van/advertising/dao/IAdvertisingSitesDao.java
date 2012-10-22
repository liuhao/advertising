package com.sparkmedia.van.advertising.dao;

import com.sparkmedia.van.advertising.entity.AdvertisingSite;
import com.sparkmedia.van.advertising.utils.Page;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: D06LH
 * Date: 12-10-18
 * Time: 下午2:47
 * To change this template use File | Settings | File Templates.
 */
public interface IAdvertisingSitesDao {
    public void insert(AdvertisingSite advSite);
    public void delete(AdvertisingSite advSite);
    public int deleteAll();
    public Page<AdvertisingSite> query(int curPage, int pageSize, String keyword) throws Exception;
    public AdvertisingSite get(long advSiteId);
}
