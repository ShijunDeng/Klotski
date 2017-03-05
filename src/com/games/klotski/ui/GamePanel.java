package com.games.klotski.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import com.games.klotski.listener.DragListener;
import com.games.klotski.service.impl.GameServiceImpl;
import com.games.klotski.util.ImageUtils;

/**
 * 游戏面板
 * 
 * 
 * @author xiaodeng wust_dengshijun@163.com
 * @version 1.1 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2013-03-22 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by xiaodeng
 */
public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private BufferedImage gameoverImage;
	private GameServiceImpl gameService;
	private Map<String, PersonLabel> personLabels;
	private Cursor cusor=new Cursor(Cursor.DEFAULT_CURSOR);;
	public Map<String, PersonLabel> getPersonLabels() {
		return personLabels;
	}
	/**
	 * 
	 * @param name
	 * @return 返回名字为name的PersonLabel
	 */
	public Entry<String, PersonLabel> getPersonLabel(String name){
		for(Map.Entry<String, PersonLabel>personLabel:personLabels.entrySet()){
			if(personLabel.getValue().getName()!=null&&personLabel.getValue().getName().equals(name))
			{
				return personLabel;
			}
		}
		return null;
	}
	public void setPersonLabels(Map<String, PersonLabel> personLabels2) {
		this.personLabels = personLabels2;
	}

	public GameServiceImpl getGameService() {
		return gameService;
	}

	public GamePanel(GameServiceImpl gameService) {
		this.setBackground(new Color(55, 77, 118));
		this.setBorder(new EtchedBorder());
		this.gameService = gameService;
		this.setLayout(null);
		this.setVisible(true);
	}

	// BeginListener中调用
	public void initPersonLabel() {
		for(Map.Entry<String, PersonLabel>personLabel:personLabels.entrySet()){
			DragListener dragListener = new DragListener(personLabel.getValue(),
					gameService);
			personLabel.getValue().addMouseMotionListener(dragListener);
			personLabel.getValue().addMouseListener(dragListener);
			this.add(personLabel.getValue());
			personLabel.getValue().setBounds();
			personLabel.getValue().setVisible(true);
		}
		this.revalidate();
	}

	public void repaintPersonLabels() {
		for(Map.Entry<String, PersonLabel>personLabel:personLabels.entrySet()){
			personLabel.getValue().setBounds();
		}
		this.revalidate();
	}

	public void setOverImage(BufferedImage gameoverImage) {
		this.gameoverImage = gameoverImage;
	}

	public BufferedImage getOverImage() {
		return gameoverImage;
	}

	@Override
	protected void paintComponent(Graphics g) {

		//画边缘线
		g.drawImage(ImageUtils.getImage("background_image"), 0, 0, getWidth(),
				getHeight(), null);
		g.drawImage(ImageUtils.getImage("rightBorder"), 480,
				0,20, getHeight(), null);
		g.drawImage(ImageUtils.getImage("downBorder"), 0,
				600,120, 40, null);
		g.drawImage(ImageUtils.getImage("downBorder"), 360,
				600,120, 40, null);
	}

	public Cursor getCusor() {
		return cusor;
	}

	public void setCusor(Cursor cusor) {
		this.cusor = cusor;
	}
	
	
}
