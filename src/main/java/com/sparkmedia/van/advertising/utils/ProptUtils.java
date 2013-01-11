package com.sparkmedia.van.advertising.utils;

import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: D06LH
 * Date: 12-10-19
 * Time: 下午1:59
 * Read config.properties file.
 */
public class ProptUtils {
	private static Properties props = new Properties();
	static{
		try {
            // Find resource at the path of this Class.
            props.load(ProptUtils.class.getResourceAsStream("/config.properties"));
        } catch (Exception e) {
            // NullPointerException - If name is null
		}  
	}
	
	public static String get(String key){
		return props.getProperty(key);
	}
}
