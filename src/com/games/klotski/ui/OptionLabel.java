package com.games.klotski.ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.games.klotski.service.MusicService;
import com.games.klotski.util.ImageUtils;
/**
 * ��ʾ�����ı�ǩ
 * 
 * 
 * @author xiaodeng wust_dengshijun@163.com
 * @version 1.1 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2013-03-24 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by xiaodeng
 */
public class OptionLabel extends JLabel {
	private static final long serialVersionUID = 201303241328L;
	private ImageIcon mouseIn;//����ƽ�����ʱ����ʾ��ͼƬ
	private ImageIcon mouseOut;//����Ƴ�ȥ��ʱ����ʾ��ͼƬ

	public OptionLabel() {
		super();
	}

	public OptionLabel(String mouseInImageName, String mouseOutImageName) {
		super();
		this.mouseIn=ImageUtils.getImageIcon(mouseInImageName);
		this.mouseOut=ImageUtils.getImageIcon(mouseOutImageName);
		this.setIcon(mouseOut);
		addListener();
	}

	private void addListener() {
		//OptionLabel����
		this.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e) {
				MusicService.play("click_01");
				setIcon(mouseIn);
			}

			public void mouseExited(MouseEvent e) {
				setIcon(mouseOut);
			}
			
		});//end of OptionLabel addMouseListener
	}
	//��һ��ͼƬ
	public void changeImage(String mouseInImageName, String mouseOutImageName){
		this.mouseIn=ImageUtils.getImageIcon(mouseInImageName);
		this.mouseOut=ImageUtils.getImageIcon(mouseOutImageName);
		this.setIcon(mouseOut);
	}
	public ImageIcon getMouseIn() {
		return mouseIn;
	}

	public void setMouseIn(ImageIcon mouseIn) {
		this.mouseIn = mouseIn;
	}

	public ImageIcon getMouseOut() {
		return mouseOut;
	}

	public void setMouseOut(ImageIcon mouseOut) {
		this.mouseOut = mouseOut;
	}

}
