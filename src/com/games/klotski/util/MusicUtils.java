package com.games.klotski.util;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import com.games.klotski.exception.GameException;

/**
 *  music util
 * 
 * @author xiaodeng wust_dengshijun@163.com
 * @version 1.1 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2013-03-23 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by xiaodeng
 */
public class MusicUtils {
	static Map<String, AudioClip> sounds = new HashMap<String, AudioClip>();

	@SuppressWarnings("deprecation")
	public static void play(String name) {
		AudioClip sound = sounds.get(name);
		if (sound == null) {
			try {
				sound = Applet.newAudioClip((new File("resource"
						+ File.separator + "sounds" + File.separator + name
						+ ".wav")).toURL());
				sounds.put(name, sound);
			} catch (MalformedURLException e) {
				e.printStackTrace();
				throw new GameException("GameException:no such resource:"
						+ name);
			}
		}
		sound.play();
	}
}
