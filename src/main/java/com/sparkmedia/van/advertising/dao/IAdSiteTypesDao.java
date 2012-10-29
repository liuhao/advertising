package com.sparkmedia.van.advertising.dao;

import com.sparkmedia.van.advertising.entity.AdSiteType;
import com.sparkmedia.van.advertising.utils.Page;

/**
 * Created with IntelliJ IDEA.
 * User: D06LH
 * Date: 12-10-19
 * Time: 下午1:43
 * To change this template use File | Settings | File Templates.
 */
public interface IAdSiteTypesDao {
    public void insert(AdSiteType advSiteType) throws Exception;
    public void delete(long advSiteTypeId);
    public AdSiteType get(long advSiteTypeId);
    public Page<AdSiteType> query(int curPage, int pageSize, String keyword) throws Exception;
}
