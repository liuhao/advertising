package com.sparkmedia.van.advertising.utils;

import java.sql.*;

/**
 * Created with IntelliJ IDEA.
 * User: D06LH
 * Date: 12-10-19
 * Time: 下午1:59
 * Create a connection with derby, create tables structure.
 */
public class DBConnectionUtils {
    private static String dbCreate = ProptUtils.get("create");
    private static String dbUser = ProptUtils.get("user");
    private static String dbPassword = ProptUtils.get("password");
    private static String dbLocation = ProptUtils.get("db_location");

    static {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            if (dbCreate.endsWith("true")) {
                createTb();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws Exception {
        //create and connect the database
        return DriverManager.getConnection("jdbc:derby:" + dbLocation + ";create=" + dbCreate, dbUser, dbPassword);
    }

    public static boolean hasTable(String table) throws Exception {
        Connection conn = getConnection();
        Statement s = conn.createStatement();
        boolean state = false;
        DatabaseMetaData meta = conn.getMetaData();
        ResultSet set;
        set = meta.getTables(null, null, table.toUpperCase(), null);
        while (set.next()) {
            state = true;
        }
        return state;
    }

    public static void createTb() throws Exception {
        Connection conn = getConnection();
        Statement s = conn.createStatement();

        //drop AdSite table and create
        if (hasTable("AdSiteTable")) {
            try {
                s.execute("DROP TABLE AdSiteTable");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        s.execute("CREATE TABLE AdSiteTable(" +
                "id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY," +
                "layerId BIGINT," +
                "boxId BIGINT," +
                "uri VARCHAR(512)," +
                "stat INT," +
                "updateDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP)");
        // init AdSiteTable
        s.execute("INSERT INTO AdSiteTable (layerId,boxId,uri,stat) " +
                 "VALUES (1,1,'content/youtube_pic.jpg',0)");
        s.execute("INSERT INTO AdSiteTable (layerId,boxId,uri,stat) " +
                "VALUES (1,2,'content/youtube_video.mp4',1)");

        //drop AdLayer table and create
        if (hasTable("AdLayerTable")) {
            try {
                s.execute("DROP TABLE AdLayerTable");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        s.execute("CREATE TABLE AdLayerTable(" +
                "id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY," +
                "layerName VARCHAR(100)," +
                "boxIds VARCHAR(512))");
        // init AdLayerTable
        s.execute("INSERT INTO AdLayerTable (layerName,boxIds) VALUES ('YouTube','[1,2]')");

        //drop AdBox table and create
        if (hasTable("AdBoxTable")) {
            try {
                s.execute("DROP TABLE AdBoxTable");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        s.execute("CREATE TABLE AdBoxTable(" +
                "id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY," +
                "type VARCHAR(100)," +
                "x INT," +
                "y INT," +
                "w INT," +
                "h INT)");
        // init AdBoxTable
        s.execute("INSERT INTO AdBoxTable (type,x,y,w,h) VALUES ('pic',10,10,100,20)");
        s.execute("INSERT INTO AdBoxTable (type,x,y,w,h) VALUES ('video',400,700,320,160)");

        //drop UserAdmin table and create
        if (hasTable("UserAdminTable")) {
            try {
                s.execute("DROP TABLE UserAdminTable");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        s.execute("CREATE TABLE UserAdminTable(" +
                "id INT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY," +
                "userName VARCHAR(32)," +
                "password VARCHAR(32)," +
                "adminMark INT)");
        //init administrator account
        s.execute("INSERT INTO UserAdminTable (userName,password,adminMark) VALUES ('admin','admin',1)");
    }
}