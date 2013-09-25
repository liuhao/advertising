package com.sparkmedia.van.advertising.action;

import com.sparkmedia.van.advertising.dao.IAdSiteDao;
import com.sparkmedia.van.advertising.dao.impl.AdSiteDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created with IntelliJ IDEA.
 * User: D06LH
 * Date: 12-11-8
 * Time: 上午10:12
 * Add AdSite recorder to table.
 */
public class AddAdSite extends HttpServlet {
    private IAdSiteDao adSiteTypesDao = new AdSiteDao();

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
