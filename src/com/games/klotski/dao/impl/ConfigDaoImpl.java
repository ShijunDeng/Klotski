package com.games.klotski.dao.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Properties;

import com.games.klotski.dao.ConfigDao;

/**
 * 
 读取相关配置文件信息
 * 
 * @author xiaodeng wust_dengshijun@163.com
 * @version 1.1 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2013-03-22 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by xiaodeng
 */
public class ConfigDaoImpl implements ConfigDao {
	private static Properties resourceConfig = new Properties();
	static {

		URL url = ConfigDaoImpl.class.getClassLoader().getResource(
				"config.properties");

		String str = url.getPath();
		try {
			str = java.net.URLDecoder.decode(str, "utf-8");// 解决路径中空格变为%20问题
		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
			throw new ExceptionInInitializerError(e2);
		}

		try {
			InputStream in = new FileInputStream(str);
			try {
				resourceConfig.load(in);
			} catch (IOException e) {
				e.printStackTrace();
				throw new ExceptionInInitializerError(e);
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			throw new ExceptionInInitializerError(e1);
		}
	}

	public String getResourcePath() {
		if (resourceConfig == null) {
			return null;
		}
		String resourcePath = resourceConfig.getProperty("resourcePath");
		return resourcePath;
	}

	public String getImagesPath() {
		if (resourceConfig == null) {
			return null;
		}
		String imagesPath = resourceConfig.getProperty("imagesPath");
		return imagesPath;
	}

	public String getSoundsPath() {
		if (resourceConfig == null) {
			return null;
		}
		String soundsPath = resourceConfig.getProperty("soundsPath");
		return soundsPath;
	}
}
