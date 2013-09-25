package com.sparkmedia.van.advertising.dao;

import com.sparkmedia.van.advertising.entity.AdSite;
import com.sparkmedia.van.advertising.utils.Page;

/**
 * Created with IntelliJ IDEA.
 * User: D06LH
 * Date: 12-10-18
 * Time: 下午2:47
 * Maintain the AdSiteTable.
 */
public interface IAdSiteDao {
    public void insert(AdSite adSite) throws Exception;
    public int delete(long adSiteId) throws Exception;
    public AdSite get(long adSiteId) throws Exception;
    public Page<AdSite> query(int curPage, int pageSize, long layerId, long boxId) throws Exception;
}
