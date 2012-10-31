package com.sparkmedia.van.advertising.dao.impl;

import com.sparkmedia.van.advertising.dao.IAdSiteTypesDao;
import com.sparkmedia.van.advertising.entity.AdContent;
import com.sparkmedia.van.advertising.entity.AdSiteType;
import com.sparkmedia.van.advertising.utils.DBConnectionUtils;
import com.sparkmedia.van.advertising.utils.Page;

import org.apache.commons.lang.StringUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
 * Time: 下午2:15
 * Transfer AdSiteType obj to json obj, then use json obj handle database recorder.
 */
public class AdSiteTypesDao implements IAdSiteTypesDao {

    @Override
    public void insert(AdSiteType adSiteType) throws Exception {
        Connection conn = null;
        try {
            conn = DBConnectionUtils.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement("INSERT INTO AdSiteTypeTable (typeName, adContents) VALUES (?,?)");
            ps.setString(1, adSiteType.getTypeName());
            Gson gson = new Gson();
            ps.setString(2, gson.toJson(adSiteType.getAdContents()));
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
    public int delete(long adSiteTypeId) throws Exception {
        Connection conn = null;
        int count = 0;
        try {
            conn = DBConnectionUtils.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement("DELETE FROM AdSiteTypeTable WHERE id in ("+adSiteTypeId+")");
            count = ps.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            throw e;
        }finally{
            if(null!=conn)
                conn.close();
        }
        return count;
    }

    @Override
    public AdSiteType get(long adSiteTypeId) throws Exception {
        Connection conn = null;
        AdSiteType adSiteType = null;
        try {
            conn = DBConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM AdSiteTypeTable WHERE id=?");
            ps.setLong(1, adSiteTypeId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                adSiteType = new AdSiteType();
                adSiteType.setTypeName(rs.getString("typeName"));
                Gson gson = new Gson();
                Type collectionType = new TypeToken<List<AdContent>>(){}.getType();
                List<AdContent> contents = gson.fromJson(rs.getString("adContents"), collectionType);
                adSiteType.setAdContents(contents);
            }
            return adSiteType;
        } catch (Exception e) {
            throw new Exception(e);
        }finally{
            if(null!=conn)
                conn.close();
        }
    }

    @Override
    public Page<AdSiteType> query(int curPage, int pageSize) throws Exception {
        Connection conn = null;
        Page<AdSiteType> page = new Page<AdSiteType>();
        try {
            conn = DBConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM AdSiteTypeTable");
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                page.setTotalRecords(rs.getInt(1), pageSize);
                if(page.getTotalRecords()==0){
                    return page;
                }
            }
            ps = conn.prepareStatement("SELECT * FROM AdSiteTypeTable ORDER BY id DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");
            ps.setInt(1, (curPage-1)*pageSize);
            ps.setInt(2, pageSize);
            rs = ps.executeQuery();

            // transfer Result Set to Result List
            List<AdSiteType> results = new ArrayList<AdSiteType>();
            page.setResults(results);
            page.setCurPage(curPage);
            AdSiteType adSiteType;
            Gson gson = new Gson();
            Type collectionType = new TypeToken<List<AdContent>>(){}.getType();
            while(rs.next()){
                adSiteType = new AdSiteType();
                adSiteType.setId(rs.getLong("id"));
                adSiteType.setTypeName(rs.getString("typeName"));
                List<AdContent> contents = gson.fromJson(rs.getString("adContents"), collectionType);
                adSiteType.setAdContents(contents);
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
