package com.games.klotski.commons;

/**
 * 
 * 一个点类
 * @author xiaodeng wust_dengshijun@163.com
 * @version 1.1 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2013-03-22 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by xiaodeng
 */
public class Point {
	//点的坐标
	private int x;
	private int y;

	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;

	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	/**
	 * 重写Object中的equals方法，判断两个点是否相等
	 */
	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point point = (Point) obj;
			return point.getX() == this.getX() && point.getY() == this.getY();
		}
		return false;
	}

	/**
	 * 
	 * @param point
	 * @return ：point在this的左上角,返回true，否则返回false
	 */
	public boolean isLeft(Point point) {
		return point.getX() > this.getX() && point.getY() == this.getY();

	}

	/**
	 * 
	 * @param point
	 * @return ：point在this的左下角,返回true，否则返回false
	 */
	public boolean isRight(Point point) {
		return point.getX() < this.getX() && point.getY() == this.getY();
	}

	/**
	 * 
	 * @param point
	 * @return ：point在this的右上角,返回true，否则返回false
	 */
	public boolean isUp(Point point) {
		return point.getX() == this.getX() && point.getY() > this.getY();
	}

	/**
	 * 
	 * @param point
	 * @return ：point在this的右下角,返回true，否则返回false
	 */
	public boolean isDown(Point point) {
		return point.getX() == this.getX() && point.getY() < this.getY();

	}

	/**
	 * 
	 * @param point
	 * @return ：point在this的左上角,返回true，否则返回false
	 */
	public boolean isLeftUp(Point point) {
		return point.getX() > this.getX() && point.getY() > this.getY();
	}

	/**
	 * 
	 * @param point
	 * @return ：point在this的左下角,返回true，否则返回false
	 */
	public boolean isLeftDown(Point point) {
		return point.getX() > this.getX() && point.getY() < this.getY();
	}

	/**
	 * 
	 * @param point
	 * @return ：point在this的右上角,返回true，否则返回false
	 */
	public boolean isRightUp(Point point) {
		return point.getX() < this.getX() && point.getY() > this.getY();
	}

	/**
	 * 
	 * @param point
	 * @return ：point在this的右下角,返回true，否则返回false
	 */
	public boolean isRightDown(Point point) {
		return point.getX() < this.getX() && point.getY() < this.getY();

	}

	public boolean inSameXline(Point point) {
		return point.getY() == this.getY();
	}

	public boolean inSameYline(Point point) {
		return point.getX() == this.getX();
	}
}
