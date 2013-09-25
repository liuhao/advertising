package com.sparkmedia.van.advertising.dao;

import com.sparkmedia.van.advertising.entity.AdLayer;
import com.sparkmedia.van.advertising.utils.Page;

/**
 * Created with IntelliJ IDEA.
 * User: D06LH
 * Date: 12-10-19
 * Time: 下午1:43
 * Maintain the AdLayerTable.
 */
public interface IAdLayerDao {
    public void insert(AdLayer adLayer) throws Exception;
    public int delete(long adLayerId) throws Exception;
    public AdLayer get(long adLayerId) throws Exception;
    public Page<AdLayer> query(int curPage, int pageSize) throws Exception;
}
