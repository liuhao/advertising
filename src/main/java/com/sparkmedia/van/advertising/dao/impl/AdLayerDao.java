package com.sparkmedia.van.advertising.dao.impl;

import com.sparkmedia.van.advertising.dao.IAdLayerDao;
import com.sparkmedia.van.advertising.entity.AdBox;
import com.sparkmedia.van.advertising.entity.AdLayer;
import com.sparkmedia.van.advertising.utils.DBConnectionUtils;
import com.sparkmedia.van.advertising.utils.Page;

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
 * Transfer AdLayer obj to json obj, then use json obj handle database recorder.
 */
public class AdLayerDao implements IAdLayerDao {

    @Override
    public void insert(AdLayer adLayer) throws Exception {
        Connection conn = null;
        try {
            conn = DBConnectionUtils.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement("INSERT INTO AdLayerTable (layerName, adBoxIds) VALUES (?,?)");
            ps.setString(1, adLayer.getLayerName());
            ps.setString(2, adLayer.getAdBoxIds().toString());
            ps.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            System.out.println("adLayer INSERT is false");
            conn.rollback();
            throw e;
        }finally{
            if(null!=conn)
                conn.close();
        }
    }

    @Override
    public int delete(long adLayerId) throws Exception {
        Connection conn = null;
        int count = 0;
        try {
            conn = DBConnectionUtils.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement("DELETE FROM AdLayerTable WHERE id in ("+adLayerId+")");
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
    public AdLayer get(long adLayerId) throws Exception {
        Connection conn = null;
        AdLayer adLayer = null;
        try {
            conn = DBConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM AdLayerTable WHERE id=?");
            ps.setLong(1, adLayerId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                adLayer = new AdLayer();
                adLayer.setLayerName(rs.getString("layerName"));
                Gson gson = new Gson();
                Type collectionType = new TypeToken<List<Long>>(){}.getType();
                List<Long> boxIds = gson.fromJson(rs.getString("boxIds"), collectionType);
                adLayer.setAdBoxIds(boxIds);
            }
            return adLayer;
        } catch (Exception e) {
            throw new Exception(e);
        }finally{
            if(null!=conn)
                conn.close();
        }
    }

    @Override
    public Page<AdLayer> query(int curPage, int pageSize) throws Exception {
        Connection conn = null;
        Page<AdLayer> page = new Page<AdLayer>();
        try {
            conn = DBConnectionUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM AdLayerTable");
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                System.out.println("rs.next() is ture");
                page.setTotalRecords(rs.getInt(1), pageSize);
                if(page.getTotalRecords()==0){
                    return page;
                }
            }
            System.out.println("rs.next() is false");
            ps = conn.prepareStatement("SELECT * FROM AdLayerTable ORDER BY id DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");
            ps.setInt(1, (curPage-1)*pageSize);
            ps.setInt(2, pageSize);
            rs = ps.executeQuery();

            // transfer Result Set to Result List
            List<AdLayer> results = new ArrayList<AdLayer>();
            page.setResults(results);
            page.setCurPage(curPage);
            AdLayer adLayer;
            Gson gson = new Gson();
            Type collectionType = new TypeToken<List<Long>>(){}.getType();
            while(rs.next()){
                adLayer = new AdLayer();
                adLayer.setId(rs.getLong("id"));
                adLayer.setLayerName(rs.getString("typeName"));
                List<Long> contents = gson.fromJson(rs.getString("boxIds"), collectionType);
                adLayer.setAdBoxIds(contents);
                results.add(adLayer);
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
