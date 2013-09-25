package com.sparkmedia.van.advertising.dao.impl;

import com.sparkmedia.van.advertising.dao.*;
import com.sparkmedia.van.advertising.entity.*;
import com.sparkmedia.van.advertising.utils.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: D06LH
 * Date: 12-10-19
 * Time: 下午1:56
 * Add, del, update advertising site recorder,
 */
public class AdSiteDao implements IAdSiteDao {

    @Override
    public void insert(AdSite adSite) throws Exception {
        Connection conn = null;
        try {
            conn = DBConnectionUtils.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO AdSiteTable (layerId, blockId, uri, stat) VALUES (?,?,?,?)");
            ps.setString(1, String.valueOf(adSite.getLayerId()));
            ps.setString(2, String.valueOf(adSite.getBoxId()));
            ps.setString(3, String.valueOf(adSite.getUri()));
            ps.setString(4, String.valueOf(adSite.getStat()));
            ps.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            if (null != conn)
                conn.close();
        }
    }

    @Override
    public int delete(long adSiteId) throws Exception {
        Connection conn = null;
        int count = 0;
        try {
            conn = DBConnectionUtils.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement("DELETE FROM AdSiteTable WHERE id in (" + adSiteId + ")");
            count = ps.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            if (null != conn)
                conn.close();
        }
        return count;
    }

    @Override
    public AdSite get(long adSiteId) throws Exception {
        Connection conn = null;
        AdSite adSite = null;
        try {
            conn = DBConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM AdSiteTable WHERE id=?");
            ps.setLong(1, adSiteId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                adSite = new AdSite();
                adSite.setLayerId(Integer.parseInt(rs.getString("layerId")));
                adSite.setBoxId(Integer.parseInt(rs.getString("boxId")));
                adSite.setUri(rs.getString("uri"));
                adSite.setStat(Integer.parseInt(rs.getString("stat")));
                //Calendar cal = Calendar.getInstance();
                //rs.getTimestamp("updateDate", cal);
                //adSite.setUpdateDate(cal);
                adSite.setUpdateDate(rs.getTimestamp("updateDate").toString());
            }
            return adSite;
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            if (null != conn)
                conn.close();
        }
    }

    @Override
    public Page<AdSite> query(int curPage, int pageSize, long layerId, long boxId) throws Exception {
        Connection conn = null;
        Page<AdSite> page = new Page<AdSite>();
        try {
            conn = DBConnectionUtils.getConnection();
            int counter = 0;
            StringBuffer whereSql = new StringBuffer(" 1=1 ");  // in order to make the success string in a uniform.
            if (layerId != 0) {
                whereSql.append(" and layerId like ? ");
            }
            PreparedStatement ps = conn.prepareStatement("select count(*) from AdSiteTable where " + whereSql.toString());
            if (layerId != 0) {
                ps.setString(1, "%" + layerId + "%");
            }
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                page.setTotalRecords(rs.getInt(1), pageSize);
                if (page.getTotalRecords() == 0) {
                    return page;
                }
            }
            ps = conn.prepareStatement("SELECT * FROM AdSiteTable WHERE " + whereSql.toString() +
                    " ORDER BY updateDate DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");
            if (layerId != 0) {
                ps.setString(++counter, "%" + layerId + "%");
            }
            ps.setInt(++counter, (curPage - 1) * pageSize);
            ps.setInt(++counter, pageSize);
            rs = ps.executeQuery();

            List<AdSite> results = new ArrayList<AdSite>();
            page.setResults(results);
            page.setCurPage(curPage);
            AdSite adSite = new AdSite();
            while (rs.next()) {
                adSite.setId(rs.getLong("id"));
                adSite.setLayerId(rs.getLong("layerId"));
                adSite.setBoxId(rs.getLong("boxId"));
                adSite.setStat(rs.getInt("stat"));
                //Calendar cal = Calendar.getInstance();
                //rs.getTimestamp("updateDate", cal);
                //adSite.setUpdateDate(cal);
                adSite.setUpdateDate(rs.getTimestamp("updateDate").toString());
                results.add(adSite);
            }
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            if (null != conn)
                conn.close();
        }
        return page;
    }

}
