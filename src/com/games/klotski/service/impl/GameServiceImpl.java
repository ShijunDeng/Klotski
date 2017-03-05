package com.games.klotski.service.impl;

import java.util.Random;
import java.util.Map.Entry;


import com.games.klotski.commons.GameConfiguration;
import com.games.klotski.commons.Point;
import com.games.klotski.person.Person;
import com.games.klotski.service.GameModel;
import com.games.klotski.service.GameService;

/**
 * 主体功能实现
 * 
 * 
 * @author xiaodeng wust_dengshijun@163.com
 * @version 1.1 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2013-03-22 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by xiaodeng
 */
public class GameServiceImpl implements GameService {
	private GameConfiguration config;// 配置
	private GameModel gameModel;// 游戏模式,随机产生
	private long grade = 0;// 加入分数属性,初始值为0
	private boolean win=false;
	public GameServiceImpl(GameConfiguration config) {
		this.config = config;
	}

	public void start() {
		this.gameModel = createGameModel(config);

	}

	public GameModel createGameModel(GameConfiguration config) {
		Random random = new Random();
		switch (random.nextInt(config.getMODELNUM())) {
		case 0:
			return new SimpleGameModelA();
		case 1:
			return new SimpleGameModelB();
		case 2:
			return new SimpleGameModelB();
		case 3:
			return new SimpleGameModelB();
		case 4:
			return new SimpleGameModelA();
		default:
			return null;
		}
	}

	public GameModel getGameModel() {
		return gameModel;
	}

	public void setGameModel(GameModel gameModel) {
		this.gameModel = gameModel;
	}

	public long countGrade() {
		return this.grade;
	}

	public long getGrade() {
		return grade;
	}

	public void setGrade(long grade) {
		this.grade = grade;
	}

	@Override
	public boolean win() {
		if (this.getGameModel()!=null&&this.getGameModel().caocao().getCell().equals(this.getGameModel().getExit())) {
			this.win=true;
		}
		return win;
	}
	
	public void setWin(boolean win) {
		this.win=win;
	}

	@Override
	/**
	 * 找出点的是哪个
	 */
	public Person findPerson(int x, int y) {
		for( Entry<String, Person> person:GameModel.getPersons().entrySet()){
			if (person.getValue().isInnerPoint(new Point(x, y))) {
				return person.getValue();
			}
		}
		return null;
	}
	/**
	 * 
	 * @param person
	 * @return 检测是否越界
	 */
	public boolean outOfBound(Person person){
		return  person.getCell().getBeginX()<this.gameModel.getGameConfig().getXStart()||
				person.getCell().getBeginX()+person.getCell().getWidth()>this.gameModel.getGameConfig().getXEnd()||
				person.getCell().getBeginY()<this.gameModel.getGameConfig().getYStart()||
				person.getCell().getBeginY()+person.getCell().getHeight()>this.gameModel.getGameConfig().getYEnd();
	}
	
}
