package com.games.klotski.service.impl;

import com.games.klotski.object.Cell;
import com.games.klotski.person.Person;
import com.games.klotski.service.GameModel;
import com.games.klotski.util.PersonUtils;

/**
 * сно╥дёй╫
 * @author xiaodeng wust_dengshijun@163.com
 * @version 1.1 <br/>
 *          website:<a href="http://www.dengshijun.icoc.cc/">xiaodeng</a> <br>
 *          time:2013-03-22 Copyright (C),2012-2013,xiaodeng <br>
 *          This program is protected by xiaodeng
 */
public class SimpleGameModelB extends GameModel {

	public SimpleGameModelB() {
		guanyu();
		zhangfei();
		zhaozilong();
		machao();
		huangzhong();
		caocao();
		samurais();
		setExit(new Cell(120, 360, 240, 240));
		//blanks();
	}

	@Override
	public Person guanyu() {
		if (this.guanyu == null) {
			String name = "guanyu";
			Cell cell = getCellCreator().createCell( getGameConfig().getBaseImageLength(),
					0, name);
			this.guanyu = new Person(name, cell);
			PersonUtils.countIndex(this.guanyu, getGameConfig().getBaseImageLength());
			GameModel.addPerson(this.guanyu);
		}
		
		return this.guanyu;

	}

	@Override
	public Person zhangfei() {
		if (this.zhangfei == null) {
			String name = "zhangfei";
			Cell cell = getCellCreator().createCell(0,
					0, name);
			this.zhangfei = new Person(name, cell);
			PersonUtils.countIndex(this.zhangfei, getGameConfig().getBaseImageLength());
			GameModel.addPerson(this.zhangfei);
		}
		return this.zhangfei;
	}

	@Override
	public Person zhaozilong() {
		if (this.zhaozilong == null) {
			String name = "zhaozilong";
			Cell cell = getCellCreator().createCell( 0,
					2 * getGameConfig().getBaseImageLength(), name);
			this.zhaozilong = new Person(name, cell);
			PersonUtils.countIndex(this.zhaozilong, getGameConfig().getBaseImageLength());
			GameModel.addPerson(this.zhaozilong);
		}
		return this.zhaozilong;
	}

	@Override
	public Person machao() {
		if (this.machao == null) {
			String name = "machao";
			Cell cell = getCellCreator().createCell(
					3* getGameConfig().getBaseImageLength(),
					0, name);

			this.machao = new Person(name, cell);
			PersonUtils.countIndex(this.machao, getGameConfig().getBaseImageLength());
			GameModel.addPerson(this.machao);
		}
		return this.machao;
	}

	@Override
	public Person huangzhong() {
		if (this.huangzhong == null) {
			String name = "huangzhong";
			Cell cell = getCellCreator().createCell(
					getGameConfig().getBaseImageLength(),
					3*getGameConfig().getBaseImageLength(), name);
			this.huangzhong = new Person(name, cell);
			PersonUtils.countIndex(this.huangzhong, getGameConfig().getBaseImageLength());
			GameModel.addPerson(this.huangzhong);
		}
		return this.huangzhong;
	}

	@Override
	public Person caocao() {
		if (this.caocao == null) {
			String name = "caocao";
			Cell cell = getCellCreator().createCell(
					2 * getGameConfig().getBaseImageLength(),
					3*getGameConfig().getBaseImageLength(), name);

			this.caocao = new Person(name, cell);
			PersonUtils.countIndex(this.caocao, getGameConfig().getBaseImageLength());

			GameModel.addPerson(this.caocao);
		}
		return this.caocao;
	}

	@Override
	public Person[] samurais() {
		if (this.samurais == null) {
			this.samurais = new Person[4];
			String name = "samurai0";
			samurais[0] = new Person(name, getCellCreator().createCell(getGameConfig().getBaseImageLength(),  getGameConfig().getBaseImageLength(),
					name));
			name = "samurai1";
			samurais[1] = new Person(name, getCellCreator().createCell(
					0, 4 * getGameConfig().getBaseImageLength(), name));
			name = "samurai2";
			samurais[2] = new Person(name, getCellCreator().createCell(
					 getGameConfig().getBaseImageLength(),
					2 * getGameConfig().getBaseImageLength(), name));
			name = "samurai3";
			samurais[3] = new Person(name, getCellCreator().createCell(
					3 * getGameConfig().getBaseImageLength(),
					2 * getGameConfig().getBaseImageLength(), name));
			GameModel.addPerson(samurais[0]);
			GameModel.addPerson(samurais[1]);
			GameModel.addPerson(samurais[2]);
			GameModel.addPerson(samurais[3]);
			PersonUtils.countIndex(this.samurais[0], getGameConfig().getBaseImageLength());
			PersonUtils.countIndex(this.samurais[1], getGameConfig().getBaseImageLength());
			PersonUtils.countIndex(this.samurais[2], getGameConfig().getBaseImageLength());
			PersonUtils.countIndex(this.samurais[3], getGameConfig().getBaseImageLength());

		}

		return samurais;
	}
/*
	@Override
	public Person[] blanks() {
		if (this.blanks == null) {
			this.blanks = new Person[2];
			String name = "blank";
			blanks[0] = new Person(name, getCellCreator().createCell(0,
					getGameConfig().getBaseImageLength(),
					getGameConfig().getBaseImageLength(),
					getGameConfig().getBaseImageLength()));
			blanks[1] = new Person(name, getCellCreator().createCell(0,
					getGameConfig().getBaseImageLength(),
					2 * getGameConfig().getBaseImageLength(),
					getGameConfig().getBaseImageLength()));
			this.addPerson(blanks[0]);
			this.addPerson(blanks[1]);
		}
		return blanks;
	}*/
}
