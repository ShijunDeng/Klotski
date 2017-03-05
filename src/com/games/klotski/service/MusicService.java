package com.games.klotski.service;

import com.games.klotski.commons.GameConfiguration;
import com.games.klotski.util.MusicUtils;

/**
 * 音乐播放
 * 
 * @author xiaodeng wust_dengshijun@163.com
 * @version 1.1 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2013-03-24 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by xiaodeng
 */
public class MusicService {
	/**
	 * 根据GameConfiguration中isPlayMusic()返回值播放音乐,isPlayMusic()返回true就播放,否则不播放
	 * 
	 * @param name
	 *            要播放的的音乐名称
	 * 
	 */
	public static void play(String name) {
		if (true == GameConfiguration.isPlayMusic()) {
			MusicUtils.play(name);
		}
	}
}
