package com.sparkmedia.van.advertising.action;

import com.sparkmedia.van.advertising.dao.IAdSitesDao;
import com.sparkmedia.van.advertising.dao.impl.AdSitesDao;
import com.sparkmedia.van.advertising.entity.AdSite;
import com.sparkmedia.van.advertising.utils.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: D06LH
 * Date: 12-10-17
 * Time: 下午3:31
 * Servlet list the recorder of the advertising site.
 */
public class ShowAdList extends HttpServlet {
    private IAdSitesDao advSiteDao = new AdSitesDao();

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        AdSiteListReqBean bean = new AdSiteListReqBean();
        String address = "/WEB-INF/pages/error.jsp";
        BeanUtilities.populateBean(bean, request);
        try {
            Page<AdSite> page = advSiteDao.query(bean.getCurPage(), bean.getPageSize(), bean.getKeyword());
            session.setAttribute("Page", page);
            address = "/WEB-INF/pages/login.jsp";
        } catch (Exception e) {
            address = "/WEB-INF/pages/error.jsp";
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}
