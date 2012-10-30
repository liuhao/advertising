package com.sparkmedia.van.advertising.dao.impl;

import com.sparkmedia.van.advertising.dao.IAdSiteTypesDao;
import com.sparkmedia.van.advertising.entity.AdContent;
import com.sparkmedia.van.advertising.entity.AdSiteType;
import com.sparkmedia.van.advertising.utils.DBConnectionUtils;
import com.sparkmedia.van.advertising.utils.Page;

import org.apache.commons.lang.StringUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

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

    @Override
    public void insert(AdSiteType adSiteType) throws Exception {
        Connection conn = null;
        try {
            conn = DBConnectionUtils.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement("insert into AdvSitesType(TypeName, SpotInfo) values(?,?)");
            ps.setString(1, adSiteType.getTypeName());

            List<AdContent> advSpotList = adSiteType.getAdContentList();
            String sep = ";";
            StringBuffer result =  new StringBuffer() ;
            for (AdContent it : advSpotList ) {
                result.append(sep==null?"":sep);
            }
            ps.setString(2, result.toString());
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
        Connection conn = null;
        Page<AdSiteType> page = new Page<AdSiteType>();
        try {
            conn = DBConnectionUtils.getConnection();
            int counter = 0;
            StringBuffer whereSql = new StringBuffer(" 1=1 ");
            if(StringUtils.isNotBlank(keyword)){
                whereSql.append(" and TypeID like ? ");
            }
            PreparedStatement ps = conn.prepareStatement("select count(*) from AdSiteType where "+whereSql.toString());
            if(StringUtils.isNotBlank(keyword)){
                ps.setString(1, "%"+keyword+"%");
            }
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                page.setTotalRecords(rs.getInt(1),pageSize);
                if(page.getTotalRecords()==0){
                    return page;
                }
            }
            ps = conn.prepareStatement("SELECT * FROM icon WHERE "+whereSql.toString()+" ORDER BY update_date DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");
            if(StringUtils.isNotBlank(keyword)){
                ps.setString(++counter, "%"+keyword+"%");
            }
            ps.setInt(++counter, (curPage-1)*pageSize);
            ps.setInt(++counter, pageSize);
            rs = ps.executeQuery();

            // transfer Result Set to Result List
            List<AdSiteType> results = new ArrayList<AdSiteType>();
            page.setResults(results);
            page.setCurPage(curPage);
            AdSiteType adSiteType;
            while(rs.next()){
                adSiteType = new AdSiteType();
                adSiteType.setId(rs.getLong("id"));
                adSiteType.setTypeName(rs.getString("typeName"));
                results.add(adSiteType);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }finally{
            if(null!=conn)
                conn.close();
        }
        return page;
    }
}
