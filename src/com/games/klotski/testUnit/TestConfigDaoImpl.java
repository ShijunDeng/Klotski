package com.games.klotski.testUnit;

import org.junit.Test;

import com.games.klotski.dao.impl.ConfigDaoImpl;

/**
 * 
 *  ≤‚ ‘ConfigDaoImpl
 * 
 * @author xiaodeng wust_dengshijun@163.com
 * @version 1.1 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2013-03-22 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by xiaodeng
 */
public class TestConfigDaoImpl {
	@Test
	public void testConfigDaoImpl() {
		ConfigDaoImpl config = new ConfigDaoImpl();
		System.out.println(config.getImagesPath());
		System.out.println(config.getResourcePath());
		System.out.println(config.getSoundsPath());
	}
}
