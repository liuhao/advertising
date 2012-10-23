package com.sparkmedia.van.advertising.action;

import com.sparkmedia.van.advertising.dao.IAdvertisingSitesDao;
import com.sparkmedia.van.advertising.dao.impl.AdvertisingSitesDao;
import com.sparkmedia.van.advertising.entity.AdvertisingSite;
import com.sparkmedia.van.advertising.utils.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: D06LH
 * Date: 12-10-17
 * Time: 下午3:31
 * Servlet list the content of the advertising Site.
 */
public class ShowAdvertisingList extends HttpServlet {
    private IAdvertisingSitesDao advSiteDao = new AdvertisingSitesDao();

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        AdvSiteListReqBean bean = new AdvSiteListReqBean();
        String address = "/WEB-INF/pages/error.jsp";
        BeanUtilities.populateBean(bean, request);
        try {
            Page<AdvertisingSite> page = advSiteDao.query(bean.getCurPage(), bean.getPageSize(), bean.getKeyword());
            session.setAttribute("Page", page);
            address = "/WEB-INF/pages/login.jsp";
        } catch (Exception e) {
            address = "/WEB-INF/pages/error.jsp";
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}
