package com.sparkmedia.van.advertising.action;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sparkmedia.van.advertising.dao.IAdLayerDao;
import com.sparkmedia.van.advertising.dao.impl.AdLayerDao;
import com.sparkmedia.van.advertising.entity.AdBox;
import com.sparkmedia.van.advertising.entity.AdLayer;
import com.sparkmedia.van.advertising.utils.BeanUtilities;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: D06LH
 * Date: 12-11-1
 * Time: 下午5:12
 * Add a AdLayer recorder to table.
 */
public class AddAdSiteType extends HttpServlet {
    private IAdLayerDao adSiteTypesDao = new AdLayerDao();

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*
        AdBox adContentA = new AdBox();
        adContentA.setName("Left");
        adContentA.setType("This picture show in the left of the window");
        adContentA.setH(120);
        adContentA.setW(60);
        adContentA.setX(10);
        adContentA.setY(10);
        AdBox adContentB = new AdBox();
        adContentB.setName("Middle");
        adContentB.setType("This picture show in the middle of the window");
        adContentB.setH(120);
        adContentB.setW(100);
        adContentB.setX(100);
        adContentB.setY(80);
        AdBox adContentC = new AdBox();
        adContentC.setName("Right");
        adContentC.setType("This picture show in the right of the window");
        adContentC.setH(120);
        adContentC.setW(60);
        adContentC.setX(410);
        adContentC.setY(10);
        List<AdBox> adContents = new ArrayList<AdBox>();
        adContents.add(adContentA);
        adContents.add(adContentB);
        adContents.add(adContentC);


        AddAdSiteTypeFormBean beanShow = new AddAdSiteTypeFormBean();
        BeanUtilities.populateBean(beanShow, request);
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<AdBox>>(){}.getType();
        List<AdBox> contents = gson.fromJson(beanShow.getAdContents(), collectionType);

        AdLayer adLayer = new AdLayer();
        adLayer.setLayerName(beanShow.getTypeName());
        adLayer.setAdBlocks(contents);

        try {
            adSiteTypesDao.insert(adLayer);
        }catch (Exception e) {
            System.out.println("INSERT is false");
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\n" +
                "<HTML>\n" +
                "<HEAD><TITLE>Add a AdType Recorder</TITLE></HEAD>\n" +
                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
                "<H1></H1>\n" +
                "</BODY></HTML>");
        */
    }
}
