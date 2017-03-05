package com.games.klotski.util;

import java.util.Map;
import java.util.Map.Entry;

import com.games.klotski.commons.Point;
import com.games.klotski.person.Person;

/**
 * 
 * @author shijun 将已经产生的人物的坐标抽象转化为二维数组
 */
public class PersonUtils {
	public static int[][] to2DArray(Map<String, Person> persons,
			int baseLength, int rows, int columns) {
		int[][] positionArray = new int[rows][columns];
		for (Entry<String, Person> person : persons.entrySet()) {
			switch (person.getKey()) {

			case "zhangfei":
				setPerIndex(positionArray, person.getValue(), baseLength, 3);
				break;
			case "guanyu":
				setPerIndex(positionArray, person.getValue(), baseLength, 2);
				break;
			case "zhaozilong":
				setPerIndex(positionArray, person.getValue(), baseLength, 4);
				break;
			case "machao":
				setPerIndex(positionArray, person.getValue(), baseLength, 6);
				break;
			case "huangzhong":
				setPerIndex(positionArray, person.getValue(), baseLength, 5);
				break;
			case "caocao":
				setPerIndex(positionArray, person.getValue(), baseLength, 1);
				break;
			case "samurai0":
				setPerIndex(positionArray, person.getValue(), baseLength, 7);
				break;
			case "samurai1":
				setPerIndex(positionArray, person.getValue(), baseLength, 8);
				break;
			case "samurai2":
				setPerIndex(positionArray, person.getValue(), baseLength, 9);
				break;
			case "samurai3":
				setPerIndex(positionArray, person.getValue(), baseLength, 10);
				break;
			default:
				break;
			}
		}
		return positionArray;

	}

	/**
	 * 
	 * @param positionArray
	 * @param person
	 * @param baseLength
	 * @param value
	 */
	private static void setPerIndex(int[][] positionArray, Person person,
			int baseLength, int value) {
		for (int i = 0; i < (int) person.getCell().getWidth() / baseLength; i++) {
			for (int j = 0; j < (int) person.getCell().getHeight() / baseLength; j++) {
				positionArray[person.getYIndex() + j][person.getXIndex() + i] = value;
			}
		}
	}

	/**
	 * 
	 * @param cell
	 * @param baseLength
	 *            将实际位置转化为坐标位置
	 */
	public static void countIndex(Person person, int baseLength) {
		person.setIndexPoint(new Point((int) person.getCell().getBeginX()
				/ baseLength, (int) person.getCell().getBeginY() / baseLength));
	}

}
