package com.games.klotski.commons;

/**
 * 配置
 * 
 * 
 * @author xiaodeng wust_dengshijun@163.com
 * @version 1.1 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2013-03-22 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by xiaodeng
 */
public class GameConfiguration {
	private int baseImageLength = 120;// 图片最小的宽度
	private long limitedTime=150;//限制时长(秒)  未采用
	private final int MODELNUM = 4;// 模式的种类数
	private int columnsNum=4;//五行四列
	private int rowsNum=5;
	//游戏界面的起始
	private final int xStart=0;
	private final int yStart=0;
	private static boolean playMusic=true;//是否播放音乐
	private static boolean isAuto=false;//是否自动寻路

	public static boolean isAuto() {
		return isAuto;
	}
	public static void setAuto(boolean isAuto) {
		GameConfiguration.isAuto = isAuto;
	}
	public GameConfiguration(){
	}
	public GameConfiguration(int baseImageLength, long limitedTime) {
		super();
		this.baseImageLength = baseImageLength;
		this.limitedTime = limitedTime;
	}

	public int getColumnsNum() {
		return columnsNum;
	}
	public void setColumnsNum(int columnsNum) {
		this.columnsNum = columnsNum;
	}
	public int getRowsNum() {
		return rowsNum;
	}
	public void setRowsNum(int rows) {
		this.rowsNum = rows;
	}
	//游戏人物的单位像素  如  本游戏采用120像素为一个单位,也即一个格子的长宽
	public int getBaseImageLength() {
		return baseImageLength;
	}

	public void setBaseImageLength(int baseImageLength) {
		this.baseImageLength = baseImageLength;
	}

	public long getLimitedTime() {
		return limitedTime;
	}

	public void setLimitedTime(long limitedTime) {
		this.limitedTime = limitedTime;
	}

	public int getMODELNUM() {
		return MODELNUM;
	}
	public int getXStart() {
		return xStart;
	}
	public int getYStart() {
		return yStart;
	}
	//游戏右边结束位置
	public int getXEnd() {
		return columnsNum*baseImageLength;
	}
	public int getYEnd() {
		return rowsNum*baseImageLength;
	}
	//是否播放音乐
	public static boolean isPlayMusic() {
		return playMusic;
	}
	public static void setPlayMusic(boolean playMusic) {
		GameConfiguration.playMusic = playMusic;
	}

}
