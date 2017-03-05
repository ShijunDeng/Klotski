package com.games.klotski.object.impl;

import com.games.klotski.object.Cell;
import com.games.klotski.object.CellCreator;
import com.games.klotski.util.ImageUtils;

/**
 * 
 * 
 * @author xiaodeng wust_dengshijun@163.com
 * @version 1.1 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2013-03-22 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by xiaodeng
 */
public class CellCreatorImpl implements CellCreator {
	
	/**
	 * ����ָ��λ�ú�ͼƬ��Cell
	 */
	public Cell createCell(int beginX, int beginY, String imageName) {
		return new Cell(ImageUtils.getImage(imageName), beginX, beginY);
	}

	public Cell getCell() {
		return null;
	}
	/**
	 * ����ָ��λ�úʹ�С��Cell
	 */
	public Cell createCell(int beginX, int beginY, int width, int height) {
		return new Cell(beginX, beginY, width, height);
	}

}
