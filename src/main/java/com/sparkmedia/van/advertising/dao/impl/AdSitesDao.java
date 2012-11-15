package com.sparkmedia.van.advertising.dao.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sparkmedia.van.advertising.dao.*;
import com.sparkmedia.van.advertising.entity.*;
import com.sparkmedia.van.advertising.utils.*;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: D06LH
 * Date: 12-10-19
 * Time: 下午1:56
 * Add, del, update advertising site recorder,
 */
public class AdSitesDao implements IAdSitesDao {

    @Override
    public void insert(AdSite adSite) throws Exception {
        Connection conn = null;
        try {
            conn = DBConnectionUtils.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement("INSERT INTO AdSiteTable (typeName, ContentIds, Stat) VALUES (?,?,?)");
            ps.setString(1, String.valueOf(adSite.getTypeId()));
            Gson gson = new Gson();
            ps.setString(2, gson.toJson(adSite.getAdContents()));
            ps.setString(3, String.valueOf(adSite.getStat()));
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
                adSite.setTypeId(Integer.parseInt(rs.getString("typeId")));
                Gson gson = new Gson();
                Type collectionType = new TypeToken<List<AdContent>>() {
                }.getType();
                List<AdContent> contents = gson.fromJson(rs.getString("adContents"), collectionType);
                adSite.setAdContents(contents);
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
    public Page<AdSite> query(int curPage, int pageSize, long typeId) throws Exception {
        Connection conn = null;
        Page<AdSite> page = new Page<AdSite>();
        try {
            conn = DBConnectionUtils.getConnection();
            int counter = 0;
            StringBuffer whereSql = new StringBuffer(" 1=1 ");  // in order to make the success string in a uniform.
            if (typeId != 0) {
                whereSql.append(" and typeId like ? ");
            }
            PreparedStatement ps = conn.prepareStatement("select count(*) from icon where " + whereSql.toString());
            if (typeId != 0) {
                ps.setString(1, "%" + typeId + "%");
            }
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                page.setTotalRecords(rs.getInt(1), pageSize);
                if (page.getTotalRecords() == 0) {
                    return page;
                }
            }
            ps = conn.prepareStatement("SELECT * FROM icon WHERE " + whereSql.toString() +
                    " ORDER BY update_date DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");
            if (typeId != 0) {
                ps.setString(++counter, "%" + typeId + "%");
            }
            ps.setInt(++counter, (curPage - 1) * pageSize);
            ps.setInt(++counter, pageSize);
            rs = ps.executeQuery();
            List<AdSite> results = new ArrayList<AdSite>();
            Type collectionType = new TypeToken<List<AdContent>>(){}.getType();
            page.setResults(results);
            page.setCurPage(curPage);
            AdSite adSite = new AdSite();
            Gson gson = new Gson();
            while (rs.next()) {
                adSite.setId(rs.getLong("id"));
                adSite.setUpdateDate(rs.getDate("updateDate"));
                adSite.setTypeId(rs.getLong("typeId"));
                adSite.setStat(rs.getInt("stat"));
                List<AdContent> contents = gson.fromJson(rs.getString("adContents"), collectionType);
                adSite.setAdContents(contents);
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
