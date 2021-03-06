package com.sparkmedia.van.advertising.action;

import com.sparkmedia.van.advertising.dao.IAdLayerDao;
import com.sparkmedia.van.advertising.dao.impl.AdLayerDao;
import com.sparkmedia.van.advertising.entity.AdLayer;
import com.sparkmedia.van.advertising.utils.BeanUtilities;
import com.sparkmedia.van.advertising.utils.Page;

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
 * Servlet list the recorders of the advertising site.
 */
public class ShowAdSiteTypes extends HttpServlet {
    private IAdLayerDao adSiteTypesDao = new AdLayerDao();

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Page<AdLayer> page = (Page<AdLayer>) session.getAttribute("Page");
        ShowAdSitesFormBean beanShow = new ShowAdSitesFormBean();
        String address;
        BeanUtilities.populateBean(beanShow, request);
        try {
            if (page == null) {
                page = adSiteTypesDao.query(beanShow.getCurPage(), beanShow.getPageSize());
                session.setAttribute("Page", page);
            }
            address = "/WEB-INF/pages/AdLayers.jsp";
        } catch (Exception e) {
            address = "/WEB-INF/pages/Error.jsp";
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}
