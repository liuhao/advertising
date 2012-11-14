package com.sparkmedia.van.advertising.action;

import com.google.gson.Gson;
import com.sparkmedia.van.advertising.dao.IAdSiteTypesDao;
import com.sparkmedia.van.advertising.dao.impl.AdSiteTypesDao;
import com.sparkmedia.van.advertising.entity.AdContent;
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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: D06LH
 * Date: 12-11-1
 * Time: 下午5:12
 * Add a AdSiteType recorder to table.
 */
public class AddAdSiteTypeRecorder extends HttpServlet {
    private IAdSiteTypesDao adSiteTypesDao = new AdSiteTypesDao();

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AdContent adContentA = new AdContent();
        adContentA.setName("Left");
        adContentA.setDescription("This picture show in the left of the window");
        adContentA.setH(120);
        adContentA.setW(60);
        adContentA.setX(10);
        adContentA.setY(10);
        AdContent adContentB = new AdContent();
        adContentB.setName("Middle");
        adContentB.setDescription("This picture show in the middle of the window");
        adContentB.setH(120);
        adContentB.setW(100);
        adContentB.setX(100);
        adContentB.setY(80);
        AdContent adContentC = new AdContent();
        adContentC.setName("Right");
        adContentC.setDescription("This picture show in the right of the window");
        adContentC.setH(120);
        adContentC.setW(60);
        adContentC.setX(410);
        adContentC.setY(10);
        List<AdContent> adContents = new ArrayList<AdContent>();
        adContents.add(adContentA);
        adContents.add(adContentB);
        adContents.add(adContentC);
        AdSiteType adSiteType = new AdSiteType();
        adSiteType.setTypeName("YouTube AdSite");
        adSiteType.setAdContents(adContents);
        Gson gson = new Gson();
        String output = gson.toJson(adSiteType.getAdContents());

        try {
            adSiteTypesDao.insert(adSiteType);
        }catch (Exception e) {
            System.out.println("INSERT is false");
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\n" +
                "<HTML>\n" +
                "<HEAD><TITLE>Add a AdType Recorder</TITLE></HEAD>\n" +
                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
                "<H1>" + output + "</H1>\n" +
                "</BODY></HTML>");
    }
}
