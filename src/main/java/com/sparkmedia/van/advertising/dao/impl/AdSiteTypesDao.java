package com.sparkmedia.van.advertising.dao.impl;

import com.sparkmedia.van.advertising.dao.IAdSiteTypesDao;
import com.sparkmedia.van.advertising.entity.AdSiteType;
import com.sparkmedia.van.advertising.utils.DBConnectionUtils;
import com.sparkmedia.van.advertising.utils.Page;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created with IntelliJ IDEA.
 * User: D06LH
 * Date: 12-10-19
 * Time: 下午2:15
 * Add,del,update advertising site type.
 * different site has different layout, for example, youtube ad site has two spot to place advertising:
 * left window and right window.
 */
public class AdSiteTypesDao implements IAdSiteTypesDao {

    private AdSiteTypesDao() {
    }

    @Override
    public void insert(AdSiteType advSiteType) throws Exception {
        Connection conn = null;
        try {
            conn = DBConnectionUtils.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement("insert into AdvSitesType(TypeName, SpotInfo) values(?,?)");
            ps.setString(1, advSiteType.getTypeName());
            ps.setString(2, advSiteType.getAdvContentList().toString());
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
    public void delete(long advSiteTypeId) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public AdSiteType get(long advSiteTypeId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Page<AdSiteType> query(int curPage, int pageSize, String keyword) throws Exception {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
