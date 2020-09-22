package com.cictec.web.fuel.util;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * 读取配置文件工具类
 * @author qianbaohua
 *
 */
public class PropertiesUtil {
	
	

	protected static Properties p = null;
	public static final String INIT_FILE = "/conf/system.properties";
	

	/**
	 * 
	 * 到指定的 INIT_FILE中获取常量
	 * 
	 * @param @param key
	 * @param @return 常量 eg. getProperty("datasource.username")
	 */
	public static String getProperty(String key) {
		if(p==null)
			init(INIT_FILE);
		return p.getProperty(key);
	}

	/**
	 * 静态读入属性文件到Properties p变量中
	 */
	protected static void init( String propertyFileName) {
		p=new Properties();
		InputStream in = null;
		try {
			in = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(INIT_FILE);
			if (in != null)
				p.load(in);
		} catch (IOException e) {
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}
	}

	/**
	 * 
	 * 到指定的配置文件中读取常量
	 * 
	 * @param @param fileName 文件名称 eg.init.properties
	 * @param @param key
	 * @return String value值
	 */
	public static String getProperty(String fileName, String key) {
		if(p==null)
			init(fileName);
		return p.getProperty(key);
	}


}
