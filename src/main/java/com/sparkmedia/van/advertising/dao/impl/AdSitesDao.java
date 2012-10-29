package com.sparkmedia.van.advertising.dao.impl;

import com.sparkmedia.van.advertising.dao.*;
import com.sparkmedia.van.advertising.entity.*;
import com.sparkmedia.van.advertising.utils.*;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created with IntelliJ IDEA.
 * User: D06LH
 * Date: 12-10-19
 * Time: 下午1:56
 * Add, del, update advertising site recorder,
 */
public class AdSitesDao implements IAdSitesDao {

    @Override
    public void insert(AdSite advSite) throws Exception {
        Connection conn = null;
        try {
            conn = DBConnectionUtils.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement("insert into AdvSites(TypeName, ContentIds, Stat) values(?,?,?)");
            ps.setString(1, String.valueOf(advSite.getTypeId()));
            ps.setString(2, advSite.getContentUri().toString());
            ps.setString(3, advSite.getStat().toString());
            ps.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            throw e;
        }finally{
            if(null!=conn)
                conn.close();
        }
    }

    @Override
    public void delete(AdSite advSite) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int deleteAll() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Page<AdSite> query(int curPage, int pageSize, String keyword) throws Exception {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public AdSite get(long advSiteId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
