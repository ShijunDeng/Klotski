package com.games.klotski.util;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.games.klotski.dao.impl.ConfigDaoImpl;
import com.games.klotski.exception.GameException;
import com.games.klotski.object.Cell;
import com.games.klotski.person.Person;

/**
 * @author xiaodeng wust_dengshijun@163.com
 * @version 1.1 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2013-03-22 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by xiaodeng
 */
public class ImageUtils {
	final private static String HEAFIMAGETYPE = ".jpg";
/**
 * 
 * @param imageName
 * @return ��ȡĬ������ΪHEAFIMAGETYPE��ͼƬ
 */
	public static BufferedImage getImage(String imageName) {
		try {
			// ʹ��ImageIO��ȡͼƬ
			return ImageIO.read(new File(new ConfigDaoImpl().getImagesPath()
					+ File.separator + imageName + HEAFIMAGETYPE));
		} catch (IOException e) {
			// ��ȡͼƬ�����쳣���׳�GameException
			throw new GameException("read image error:no such resource");
		}
	}
/**
 * 
 * @param imageName
 * @param type ��ȡָ�����͵�ͼƬ
 * @return
 */
	public static BufferedImage getImage(String imageName,String type) {
		try {
			// ʹ��ImageIO��ȡͼƬ
			return ImageIO.read(new File(new ConfigDaoImpl().getImagesPath()
					+ File.separator + imageName + type));
		} catch (IOException e) {
			// ��ȡͼƬ�����쳣���׳�GameException
			e.printStackTrace();
			throw new GameException("read image error:no such resource");
		}
	}
	/**
	 * 
	 * @param imageName
	 * @return �õ�Ĭ������ΪHEAFIMAGETYPE��ImageIcon
	 */
	public static ImageIcon getImageIcon(String imageName) {
		return new ImageIcon("resource" + File.separator + "images"
				+ File.separator + imageName + HEAFIMAGETYPE);
	}
/**
 * 
 * @param imageName
 * @param type �õ�ָ������Ϊtype��ImageIcon
 * @return
 */
	public static ImageIcon getImageIcon(String imageName,String type) {
		return new ImageIcon("resource" + File.separator + "images"
				+ File.separator + imageName + type);
	}

	/**
	 * 
	 * @param g
	 * @param cell
	 *            :�ڽ����ϻ�һ��cell����
	 */
	public static void paintCell(Graphics g, Cell cell) {
		if (cell == null)
			return;
		g.drawImage(cell.getImage(), cell.getBeginX(), cell.getBeginY(),
				cell.getWidth(), cell.getHeight(), null);

	}

	/**
	 * 
	 * @param g
	 * @param person
	 *            :�ڽ����ϻ�һ��person����
	 */
	public static void paintCell(Graphics g, Person person) {
		Cell cell = person.getCell();
		paintCell(g, cell);

	}
	
	public static Cursor createCursor(String cursorName) {
		Image cursorImage = getImage(cursorName);
		return Toolkit.getDefaultToolkit().createCustomCursor(cursorImage,
				new Point(10, 10), "myCursor");
	}
	public static Cursor createCursor(String cursorName,String type) {
		Image cursorImage = getImage(cursorName,type);
		return Toolkit.getDefaultToolkit().createCustomCursor(cursorImage,
				new Point(10, 10), "myCursor");
	}
}
