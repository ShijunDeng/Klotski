package com.games.klotski.person;

import com.games.klotski.commons.Point;
import com.games.klotski.object.Cell;

/**
 * 
 * 一个人物对象
 * 
 * @author xiaodeng wust_dengshijun@163.com
 * @version 1.1 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2013-03-22 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by xiaodeng
 */

public class Person {
	private String name;//人物名
	private Cell cell;//人物的图像 位置信息

	public Person(String name, Cell cell) {
		this.name = name;
		this.cell = cell;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Cell getCell() {
		return cell;
	}

	public void setCell(Cell cell) {
		this.cell = cell;
	}

	public void setLocation(int x, int y) {
		this.cell.setLocation(x, y);
	}


	public void getLocation() {
		this.cell.getLocation();
	}
	

	public Point getIndexPoint() {
		return this.cell.getIndexPoint();
	}

	
	public void setIndexPoint(Point indexPoint) {
		this.cell.setIndexPoint(indexPoint);
	}

	
	public int getXIndex(){
		return this.cell.getXIndex();
	}
	
	public int getYIndex(){
		return this.cell.getYIndex();
	}
	
	public void setXIndex(int xIndex){
		this.cell.setXIndex(xIndex);
	}
	
	public void setYIndex(int yIndex){
		this.cell.setYIndex(yIndex);
	}
/**
 * 
 * @param xDistance 向X移动xDistance
 */
	public void moveTowardsX(int xDistance) {
		this.cell.setCellXLocation(xDistance);
	}
/**
 * 
 * @param yDistance 向Y移动yDistance
 */
	public void moveTowardsY(int yDistance) {
		this.cell.setCellYLocation(yDistance);
	}

	/**
	 * 
	 * @param person
	 * @return 检测两个人的位置是否重叠
	 */
	public boolean intersects(Person person) {
		return this.cell.intersects(person.getCell());
	}
/**
 * 
 * @param point
 * @return 判断Point的位置是否包含在该人物的内部
 */
	public boolean isInnerPoint(Point point) {
		return point.getX() > this.getCell().getBeginX()
				&& point.getX() < this.getCell().getBeginX()
						+ this.getCell().getWidth()
				&& point.getY() > this.getCell().getBeginY()
				&& point.getY() < this.getCell().getBeginY()
						+ this.getCell().getHeight();
	}

}
