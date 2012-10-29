package com.sparkmedia.van.advertising.dao;

import com.sparkmedia.van.advertising.entity.AdSite;
import com.sparkmedia.van.advertising.utils.Page;

/**
 * Created with IntelliJ IDEA.
 * User: D06LH
 * Date: 12-10-18
 * Time: 下午2:47
 * To change this template use File | Settings | File Templates.
 */
public interface IAdSitesDao {
    public void insert(AdSite advSite) throws Exception;
    public void delete(AdSite advSite);
    public int deleteAll();
    public Page<AdSite> query(int curPage, int pageSize, String keyword) throws Exception;
    public AdSite get(long advSiteId);
}
