package com.games.klotski.service;

import com.games.klotski.person.Person;


/**
 * @author xiaodeng wust_dengshijun@163.com
 * @version 1.1 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2013-03-22 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by xiaodeng
 */
public interface GameService {
	
	/**
	 * 开始
	 */
	void start();
	
	/**
	 * 
	 * @return 判断输赢
	 */
	boolean win();
	/**
	 * 
	 * @param x
	 * @param y
	 * @return 找出(x,y)处的人物
	 */
	Person findPerson(int x,int y);

}
