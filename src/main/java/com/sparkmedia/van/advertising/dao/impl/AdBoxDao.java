package com.sparkmedia.van.advertising.dao.impl;

import com.sparkmedia.van.advertising.dao.IAdBoxDao;
import com.sparkmedia.van.advertising.entity.AdBox;
import com.sparkmedia.van.advertising.utils.DBConnectionUtils;
import com.sparkmedia.van.advertising.utils.Page;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: d06lh
 * Date: 8/29/13
 * Time: 4:17 PM
 * Handle AdBoxTable recorder.
 */
public class AdBoxDao implements IAdBoxDao {
    @Override
    public void insert(AdBox adBox) throws Exception {
        Connection conn = null;
        try {
            conn = DBConnectionUtils.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement("INSERT INTO AdBoxTable (type, x, y, w, h) VALUES (?,?,?,?,?)");
            ps.setString(1, adBox.getType());
            ps.setString(2, String.valueOf(adBox.getX()));
            ps.setString(2, String.valueOf(adBox.getY()));
            ps.setString(2, String.valueOf(adBox.getW()));
            ps.setString(2, String.valueOf(adBox.getH()));
            ps.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            System.out.println("adBox INSERT is false");
            conn.rollback();
            throw e;
        } finally {
            if (null != conn)
                conn.close();
        }
    }

    @Override
    public int delete(long adBoxId) throws Exception {
        Connection conn = null;
        int count = 0;
        try {
            conn = DBConnectionUtils.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement("DELETE FROM AdBoxTable WHERE id in (" + adBoxId + ")");
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
    public AdBox get(long adBoxId) throws Exception {
        Connection conn = null;
        AdBox adBox = null;
        try {
            conn = DBConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM AdBoxTable WHERE id=?");
            ps.setLong(1, adBoxId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                adBox = new AdBox();
                adBox.setType(rs.getString("type"));
                adBox.setX(rs.getInt("x"));
                adBox.setY(rs.getInt("y"));
                adBox.setW(rs.getInt("w"));
                adBox.setH(rs.getInt("h"));
            }
            return adBox;
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            if (null != conn)
                conn.close();
        }
    }

    @Override
    public Page<AdBox> query(int curPage, int pageSize, String type) throws Exception {
        Connection conn = null;
        Page<AdBox> page = new Page<AdBox>();
        try {
            conn = DBConnectionUtils.getConnection();
            int counter = 0;
            StringBuffer whereSql = new StringBuffer(" 1=1 ");  // in order to make the success string in a uniform.
            if (type != null) {
                whereSql.append(" and type like ? ");
            }
            PreparedStatement ps = conn.prepareStatement("select count(*) from AdBoxTable where " + whereSql.toString());
            if (type != null) {
                ps.setString(1, "%" + type + "%");
            }
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                page.setTotalRecords(rs.getInt(1), pageSize);
                if (page.getTotalRecords() == 0) {
                    return page;
                }
            }
            ps = conn.prepareStatement("SELECT * FROM AdBoxTable WHERE " + whereSql.toString() +
                    " ORDER BY updateDate DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");
            if (type != null) {
                ps.setString(++counter, "%" + type + "%");
            }
            ps.setInt(++counter, (curPage - 1) * pageSize);
            ps.setInt(++counter, pageSize);
            rs = ps.executeQuery();

            List<AdBox> results = new ArrayList<AdBox>();
            page.setResults(results);
            page.setCurPage(curPage);
            AdBox adBox = new AdBox();
            while (rs.next()) {
                adBox.setId(rs.getLong("id"));
                adBox.setType(rs.getString("type"));
                adBox.setX(rs.getInt("x"));
                adBox.setY(rs.getInt("y"));
                adBox.setW(rs.getInt("w"));
                adBox.setH(rs.getInt("h"));
                results.add(adBox);
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
