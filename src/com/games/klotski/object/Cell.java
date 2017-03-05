package com.games.klotski.object;

import java.awt.Image;
import java.awt.Rectangle;

import com.games.klotski.commons.Point;

/**
 * 一个方块对象
 * 
 * @author xiaodeng wust_dengshijun@163.com
 * @version 1.1 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2013-03-22 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by xiaodeng
 */
public class Cell {

	// 方块的图片
	private Image image;
	// 开始横坐标
	private int beginX;
	// 开始纵坐标
	private int beginY;
	// 方块的宽
	private int width;
	// 方块的高
	private int height;
	
	private Point indexPoint=new Point(0, 0);
	public Cell(Image image, int beginX, int beginY, int width, int height) {
		this.image = image;
		this.beginX = beginX;
		this.beginY = beginY;
		this.width = width;
		this.height = height;
	}

	public Cell(Image image, int beginX, int beginY) {
		this(image, beginX, beginY, image.getWidth(null), image.getHeight(null));

	}

	public Cell(int beginX, int beginY, int width, int height) {
		this(null, beginX, beginY, width, height);
	}

	public Cell(Cell cell) {
		this(cell.image, cell.beginX, cell.beginY, cell.width, cell.height);
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getBeginX() {
		return beginX;
	}

	public void setBeginX(int beginX) {
		this.beginX = beginX;
	}

	public int getBeginY() {
		return beginY;
	}

	public void setBeginY(int beginY) {
		this.beginY = beginY;
	}

	public void setLocation(int x, int y) {
		setBeginX(x);
		setBeginY(y);
	}

	public Point getLocation() {
		return new Point(this.beginX, this.beginY);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	
	public Point getIndexPoint() {
		return indexPoint;
	}

	
	public void setIndexPoint(Point indexPoint) {
		this.indexPoint.setX(indexPoint.getX());
		this.indexPoint.setY(indexPoint.getY());
		//System.out.println("x,y"+this.getXIndex()+" "+this.getYIndex());
	}

	//得到抽象为点的坐标如（1,2）
	public int getXIndex(){
		return this.indexPoint.getX();
	}
	
	public int getYIndex(){
		return this.indexPoint.getY();
	}
	
	public void setXIndex(int xIndex){
		 this.indexPoint.setX(xIndex);
	}
	
	public void setYIndex(int yIndex){
		 this.indexPoint.setY(yIndex);
	}
	
	/**
	 * 
	 * @param x
	 *            :让Cell的x座标加上参数x
	 */
	public void setCellXLocation(int x) {
		this.beginX += x;
	}

	/**
	 * 
	 * @param y
	 *            :让Cell的y座标加上参数y
	 */
	public void setCellYLocation(int y) {
		this.beginY += y;
	}
	//判断两个方块是否一样：位置
	public boolean equals(Object obj) {
		if (obj instanceof Cell) {
			Cell s = (Cell) obj;
			if ((s.getBeginX() == this.getBeginX() && (s.getBeginY() == this
					.getBeginY()))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param person
	 * @return 检测位置是否重叠
	 */
	public boolean intersects(Cell cell) {
		Rectangle ra = new Rectangle(this.beginX, this.beginY, this.width,
				this.height);
		Rectangle rb = new Rectangle(cell.beginX, cell.beginY, cell.width,
				cell.height);
		return ra.intersects(rb);

	}
	/**
	 * 
	 * @param person
	 * @return 检测位置是否重叠
	 */
	public boolean intersects1(Cell cell) {
		  int tw = this.width;
	        int th = this.height;
	        int rw = cell.width;
	        int rh = cell.height;
	        if (rw <= 0 || rh <= 0 || tw <= 0 || th <= 0) {
	            return false;
	        }
	        int tx = this.beginX;
	        int ty = this.beginY;
	        int rx = cell.beginX;
	        int ry = cell.beginY;
	        rw += rx;
	        rh += ry;
	        tw += tx;
	        th += ty;
	        //      overflow || intersect
	        return ((rw < rx || rw > tx) &&
	                (rh < ry || rh > ty) &&
	                (tw < tx || tw > rx) &&
	                (th < ty || th > ry));
		

	}
}
