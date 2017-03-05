package com.games.klotski.commons;

/**
 * ����
 * 
 * 
 * @author xiaodeng wust_dengshijun@163.com
 * @version 1.1 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2013-03-22 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by xiaodeng
 */
public class GameConfiguration {
	private int baseImageLength = 120;// ͼƬ��С�Ŀ��
	private long limitedTime=150;//����ʱ��(��)  δ����
	private final int MODELNUM = 4;// ģʽ��������
	private int columnsNum=4;//��������
	private int rowsNum=5;
	//��Ϸ�������ʼ
	private final int xStart=0;
	private final int yStart=0;
	private static boolean playMusic=true;//�Ƿ񲥷�����
	private static boolean isAuto=false;//�Ƿ��Զ�Ѱ·

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
	//��Ϸ����ĵ�λ����  ��  ����Ϸ����120����Ϊһ����λ,Ҳ��һ�����ӵĳ���
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
	//��Ϸ�ұ߽���λ��
	public int getXEnd() {
		return columnsNum*baseImageLength;
	}
	public int getYEnd() {
		return rowsNum*baseImageLength;
	}
	//�Ƿ񲥷�����
	public static boolean isPlayMusic() {
		return playMusic;
	}
	public static void setPlayMusic(boolean playMusic) {
		GameConfiguration.playMusic = playMusic;
	}

}
