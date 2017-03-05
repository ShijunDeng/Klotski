package com.games.klotski.service;

import com.games.klotski.commons.GameConfiguration;
import com.games.klotski.util.MusicUtils;

/**
 * ���ֲ���
 * 
 * @author xiaodeng wust_dengshijun@163.com
 * @version 1.1 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2013-03-24 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by xiaodeng
 */
public class MusicService {
	/**
	 * ����GameConfiguration��isPlayMusic()����ֵ��������,isPlayMusic()����true�Ͳ���,���򲻲���
	 * 
	 * @param name
	 *            Ҫ���ŵĵ���������
	 * 
	 */
	public static void play(String name) {
		if (true == GameConfiguration.isPlayMusic()) {
			MusicUtils.play(name);
		}
	}
}
