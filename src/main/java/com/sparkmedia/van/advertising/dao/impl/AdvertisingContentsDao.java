package com.sparkmedia.van.advertising.dao.impl;

import com.sparkmedia.van.advertising.dao.IAdvertisingContentsDao;
import com.sparkmedia.van.advertising.entity.AdvertisingContent;
import com.sparkmedia.van.advertising.utils.Page;

/**
 * Created with IntelliJ IDEA.
 * User: D06LH
 * Date: 12-10-19
 * Time: 下午2:15
 * To change this template use File | Settings | File Templates.
 */
public class AdvertisingContentsDao implements IAdvertisingContentsDao {

    private AdvertisingContentsDao() {
    }

    @Override
    public void insert(AdvertisingContent advContent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(AdvertisingContent advContent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int deleteAll() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Page<AdvertisingContent> query(int curPage, int pageSize, String keyword) throws Exception {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public AdvertisingContent get(long advContentId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
