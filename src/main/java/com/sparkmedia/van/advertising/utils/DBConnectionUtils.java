package com.sparkmedia.van.advertising.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: D06LH
 * Date: 12-10-19
 * Time: 下午1:59
 * Create a connection with derby, create tables structure.
 */
public class DBConnectionUtils {

    public static Properties props = new Properties();

    static {
        try {
            props.load(DBConnectionUtils.class.getClassLoader().getResourceAsStream("config.properties"));
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            if (props.getProperty("create").toString().endsWith("true")) {
                createTb();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws Exception {
        //create and connect the database
        return DriverManager.getConnection("jdbc:derby:" + props.getProperty("db_location")
                + ";create=" + props.getProperty("create"), props);
    }

    public static void createTb() throws Exception {
        Connection conn = getConnection();
        Statement s = conn.createStatement();

        //drop AdvSites table and create
        try {
            s.execute("DROP TABLE AdSites");
        } catch (Exception e) {
        }
        s.execute("CREATE TABLE AdSites(" +
                "Id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY," +
                "TypeId BIGINT," +
                "ContentUris VARCHAR(500)," +
                "Stat INT," +
                "UpdateDate DATE DEFAULT CURRENT_DATE)");

        //drop AdSiteTypes table and create
        try {
            s.execute("DROP TABLE AdSiteTypes");
        } catch (Exception e) {
        }
        s.execute("CREATE TABLE AdSiteTypes(" +
                "Id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY," +
                "TypeName VARCHAR(100)," +
                "ContentInfo VARCHAR(512)");

        //drop user table and create
        try {
            s.execute("DROP TABLE UserAdmin");
        } catch (Exception e) {
        }
        s.execute("CREATE TABLE UserAdmin(" +
                "Id INT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY," +
                "UserName VARCHAR(30)," +
                "Password VARCHAR(32)," +
                "Admin INT)");
        //init administrator account
        s.execute("INSERT INTO UserAdmin(UserName,Password,Admin) VALUES('admin','admin',1)");
    }
}