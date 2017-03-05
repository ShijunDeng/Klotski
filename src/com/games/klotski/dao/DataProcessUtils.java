package com.games.klotski.dao;

/**
 * 
 * @author shijun 数据交换抽象类：给出当前人物的坐标,得到走的路径以及是否可以走
 * 
 */
public class DataProcessUtils {
	private static final int CL = 2;

	/**
	 * 
	 * @param presentPersonsPosition
	 *            前人物的坐标
	 * @return 一条通路
	 */
	public static native Object[] getPath(int[][] presentPersonsPosition);

	public static int[][] autoFindPath(int[][] presentPersonsPosition) {
		 Object []pathObj=getPath(presentPersonsPosition);	
		// 用枚举应该更好
				// 1上,2下,3左,4右
		int length = pathObj.length / 2;
		int[][] pathInt = new int[length][CL];
		// 将c中返回的一维数组转化为二维整型数组
		for (int columnIndex = 0; columnIndex < CL; columnIndex++) {
			for (int rowIndex = 0; rowIndex < length; rowIndex++) {
				pathInt[rowIndex][columnIndex] = Integer.parseInt((pathObj[rowIndex * CL+ columnIndex].toString()));
			}
		}
		for(int i=0;i<pathInt.length/2;i++){//因为传过来的要为倒序
			int[] temp=pathInt[i];
			pathInt[i]=pathInt[length-i-1];
			pathInt[length-i-1]=temp;			
		}
		return pathInt;

	}

	// 加载动态链接库
	static {
		System.loadLibrary("msvcp110d");
		System.loadLibrary("msvcr110d");
		System.loadLibrary("DataProcessUtils");
	}
}
