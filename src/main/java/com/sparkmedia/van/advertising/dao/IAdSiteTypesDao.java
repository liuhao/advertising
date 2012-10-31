package com.sparkmedia.van.advertising.dao;

import com.sparkmedia.van.advertising.entity.AdSiteType;
import com.sparkmedia.van.advertising.utils.Page;

/**
 * Created with IntelliJ IDEA.
 * User: D06LH
 * Date: 12-10-19
 * Time: 下午1:43
 * Maintain the AdSiteTypes table.
 */
public interface IAdSiteTypesDao {
    public void insert(AdSiteType adSiteType) throws Exception;
    public int delete(long adSiteTypeId) throws Exception;
    public AdSiteType get(long adSiteTypeId) throws Exception;
    public Page<AdSiteType> query(int curPage, int pageSize) throws Exception;
}
