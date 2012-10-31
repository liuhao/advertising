package com.sparkmedia.van.advertising.action;

import com.sparkmedia.van.advertising.dao.IAdSiteTypesDao;
import com.sparkmedia.van.advertising.dao.impl.AdSiteTypesDao;
import com.sparkmedia.van.advertising.entity.AdSiteType;
import com.sparkmedia.van.advertising.utils.AdSiteListReqBean;
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
 * Servlet list the recorder of the advertising site.
 */
public class ShowAdTypeList extends HttpServlet {
    private IAdSiteTypesDao adSiteTypesDao = new AdSiteTypesDao();

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        AdSiteListReqBean bean = new AdSiteListReqBean();
        String address = "/WEB-INF/pages/error.jsp";
        BeanUtilities.populateBean(bean, request);
        try {
            Page<AdSiteType> page = adSiteTypesDao.query(bean.getCurPage(), bean.getPageSize());
            session.setAttribute("Page", page);
            address = "/WEB-INF/pages/typeSetting.jsp";
        } catch (Exception e) {
            address = "/WEB-INF/pages/error.jsp";
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}