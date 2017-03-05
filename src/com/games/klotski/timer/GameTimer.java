package com.games.klotski.timer;

import java.util.TimerTask;

import javax.swing.JLabel;



/**
 * 计时器
 * 
 ** 
 * @author xiaodeng wust_dengshijun@163.com
 * @version 1.1 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2013-03-22 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by xiaodeng
 */
public class GameTimer extends TimerTask {
	// 当前用掉的时候
	private long time;
	// 游戏设定的时间
	private static long gameTime;

	public static long getGameTime() {
		return gameTime;
	}

	public static void setGameTime(long gameTime) {
		GameTimer.gameTime = gameTime;
	}

	private JLabel timeLabel;

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public GameTimer( long gameTime, JLabel timeLabel) {
		this.time = 0;
		GameTimer.gameTime = gameTime;
		this.timeLabel = timeLabel;
	}

	public void run() {
		// 游戏时间	
		this.timeLabel.setText(String.valueOf(GameTimer.gameTime + this.time));
		this.timeLabel.repaint();
		this.time += 1;
	}

}
