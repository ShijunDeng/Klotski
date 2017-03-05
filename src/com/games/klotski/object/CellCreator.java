package com.games.klotski.object;
/**
 * 
 * 
 * @author xiaodeng wust_dengshijun@163.com
 * @version 1.1 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2013-03-22 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by xiaodeng
 */
public interface CellCreator {

	/**
	 * 在x和y座标中创建一个Cell对象
	 * @return
	 */
	Cell createCell(int beginX, int beginY,int width,int height);
	Cell createCell(int beginX, int beginY,String imageName);
	/**
	 * 返回一个Cell对象
	 * @return
	 */
	Cell getCell();
	
}
