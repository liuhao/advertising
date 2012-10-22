package com.sparkmedia.van.advertising.dao;

import com.sparkmedia.van.advertising.entity.AdvertisingContent;
import com.sparkmedia.van.advertising.utils.Page;

/**
 * Created with IntelliJ IDEA.
 * User: D06LH
 * Date: 12-10-19
 * Time: 下午1:43
 * To change this template use File | Settings | File Templates.
 */
public interface IAdvertisingContentsDao {
    public void insert(AdvertisingContent advContent);
    public void delete(AdvertisingContent advContent);
    public int deleteAll();
    public Page<AdvertisingContent> query(int curPage, int pageSize, String keyword) throws Exception;
    public AdvertisingContent get(long advContentId);
}
