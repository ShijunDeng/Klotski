package com.games.klotski.testUnit;

import org.junit.Test;

import com.games.klotski.dao.DataProcessUtils;

public class TestDataProcessUtils {
	@Test
	public void testGetPath() {
		int[][] test = new int[][] { { 6, 6, 0, 0 }, { 3, 3, 2, 2 },
				{ 5, 5, 7, 9 }, { 4, 4, 1, 1, }, { 8, 10, 1, 1 } };
		Object[] ob = DataProcessUtils.getPath(test);

		System.out.println(ob == null);
		System.out.println(ob.length);
		for (int i = 0; i < ob.length / 2; i++) {
			System.out.print(ob[2 * i + 0]);
			System.out.println(ob[2 * i + 1]);
		}

	}

	@Test
	public void testCover() {
		System.out.println((int) 7.9);

	}

}
