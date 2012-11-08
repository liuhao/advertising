package com.sparkmedia.van.advertising.utils;

import java.util.Properties;

public class ProptUtils {

	private static Properties props = new Properties();
	
	static{
		try {
			props.load(DBConnectionUtils.class.getClassLoader().getResourceAsStream("config.properties"));
		} catch (Exception e) {
		}  
	}
	
	public static String get(String key){
		return props.getProperty(key);
	}
	
}
