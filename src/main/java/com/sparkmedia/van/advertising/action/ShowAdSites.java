package com.sparkmedia.van.advertising.action;

import com.sparkmedia.van.advertising.dao.IAdSiteDao;
import com.sparkmedia.van.advertising.dao.impl.AdSiteDao;
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
public class ShowAdSites extends HttpServlet {
    private IAdSiteDao adSiteDao = new AdSiteDao();

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ShowAdSitesFormBean beanShow = new ShowAdSitesFormBean();
        BeanUtilities.populateBean(beanShow, request);
        String address;
        try {
            Page<AdSite> page = adSiteDao.query(beanShow.getCurPage(), beanShow.getPageSize(),
                    beanShow.getLayerId(), beanShow.getBoxId());
            session.setAttribute("Page", page);
            address = "/WEB-INF/pages/AdSites.jsp";
        } catch (Exception e) {
            address = "/WEB-INF/pages/Error.jsp";
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}
