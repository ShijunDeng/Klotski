package com.games.klotski.ui;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.games.klotski.commons.Point;
import com.games.klotski.object.Cell;
import com.games.klotski.person.Person;

/**
 * 一个人物标签
 * 
 * @author xiaodeng wust_dengshijun@163.com
 * @version 1.1 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2013-03-22 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by xiaodeng
 */
public class PersonLabel extends JLabel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 20130322222L;
	private Person person;

	public PersonLabel(Person person) {
		this.person = person;
		Image image = person.getCell().getImage();
		if (image != null)
			this.setIcon(new ImageIcon(image));
		this.setVisible(true);
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	//依照人物现在的参数设置人物位置
	public void setBounds() {
		this.setBounds(this.person.getCell().getBeginX(), this.person.getCell()
				.getBeginY(), this.person.getCell().getWidth(), this.person
				.getCell().getHeight());
		this.setVisible(true);
	}

	/**
	 * 依照指定的参数设置人物位置
	 */
	public void setLocation(int x, int y) {
		super.setLocation(x, y);
		this.getPerson().setLocation(x, y);
	}

	public void getLocation(int x, int y) {
		this.getPerson().getLocation();
	}

	public boolean intersects(PersonLabel personLabel) {
		return this.person.intersects(personLabel.person);
	}
	
	
	public Point getIndexPoint() {
		return this.person.getIndexPoint();
	}

	
	public void setIndexPoint(Point indexPoint) {
		this.person.setIndexPoint(indexPoint);
	}

	
	public int getXIndex(){
		return this.person.getXIndex();
	}
	
	public int getYIndex(){
		return this.person.getYIndex();
	}
	
	public void setXIndex(int xIndex){
		this.person.setXIndex(xIndex);
	}
	
	public void setYIndex(int yIndex){
		this.person.setYIndex(yIndex);
	}

	public Cell getCell() {
		return this.person.getCell();
	}
}
