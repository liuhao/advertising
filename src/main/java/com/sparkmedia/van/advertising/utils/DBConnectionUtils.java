package com.sparkmedia.van.advertising.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

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

    public static void createTb() throws Exception {
        Connection conn = getConnection();
        Statement s = conn.createStatement();

        //drop AdvSites table and create
        try {
            s.execute("DROP TABLE AdSiteTable");
        } catch (Exception e) {
        }
        s.execute("CREATE TABLE AdSiteTable(" +
                "id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY," +
                "typeId BIGINT," +
                "adContents VARCHAR(512)," +
                "stat INT," +
                "updateDate DATE DEFAULT CURRENT_DATE)");

        //drop AdSiteTypes table and create
        try {
            s.execute("DROP TABLE AdSiteTypeTable");
        } catch (Exception e) {
        }
        s.execute("CREATE TABLE AdSiteTypeTable(" +
                "id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY," +
                "typeName VARCHAR(100)," +
                "adContents VARCHAR(512))");
        //drop user table and create
        try {
            s.execute("DROP TABLE UserAdminTable");
        } catch (Exception e) {
        }
        s.execute("CREATE TABLE UserAdminTable(" +
                "id INT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY," +
                "userName VARCHAR(30)," +
                "password VARCHAR(32)," +
                "adminMark INT)");
        //init administrator account
        s.execute("INSERT INTO UserAdminTable (userName,password,adminMark) VALUES ('admin','admin',1)");
    }
}