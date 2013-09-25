package com.sparkmedia.van.advertising.dao;

import com.sparkmedia.van.advertising.entity.AdBox;
import com.sparkmedia.van.advertising.utils.Page;

/**
 * Created with IntelliJ IDEA.
 * User: d06lh
 * Date: 8/28/13
 * Time: 4:02 PM
 * Maintain the AdBlockTable.
 */
public interface IAdBoxDao {
    public void insert(AdBox adBox) throws Exception;
    public int delete(long adBoxId) throws Exception;
    public AdBox get(long adBoxId) throws Exception;
    public Page<AdBox> query(int curPage, int pageSize, String type) throws Exception;
}
