package com.games.klotski.service;

import java.util.HashMap;
import java.util.Map;

import com.games.klotski.commons.GameConfiguration;
import com.games.klotski.object.Cell;
import com.games.klotski.object.impl.CellCreatorImpl;
import com.games.klotski.person.Person;


/**
 * 游戏模式：创建各个人物
 * 
 * @author xiaodeng wust_dengshijun@163.com
 * @version 1.1 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2013-03-22 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by xiaodeng
 */
public abstract class GameModel {
	private GameConfiguration gameConfig=new GameConfiguration();
	private CellCreatorImpl cellCreator=new CellCreatorImpl();
	private static Map<String,Person> persons=new HashMap<String,Person>();//存放产生的人物
	protected Cell exit=new Cell(120, 360, 240, 240) ;//出口
	protected Person guanyu;
	protected Person zhangfei;
	protected Person zhaozilong;
	protected Person machao;
	protected Person huangzhong;
	protected Person caocao;
	protected Person[] samurais;
	protected Person[] blanks;
	
	public CellCreatorImpl getCellCreator() {
		return cellCreator;
	}
	
	
	public  static Map<String, Person>  getPersons() {
		return persons;
	}

	public static void addPerson(Person person) {
		GameModel.persons.put(person.getName(),person) ;
	}

	public GameConfiguration getGameConfig() {
		return gameConfig;
	}

	public void setGameConfig(GameConfiguration gameConfig) {
		this.gameConfig = gameConfig;
	}

	public Cell getExit() {
		return exit;
	}


	public void setExit(Cell exit) {
		this.exit = exit;
	}


	//创建各个人物，包括位置，大小等
	public abstract Person guanyu();
	public abstract Person zhangfei();
	public abstract Person zhaozilong();
	public abstract Person machao();
	public abstract Person huangzhong();
	public abstract Person caocao();
	public abstract Person[] samurais();
	//public abstract Person[] blanks();

}
