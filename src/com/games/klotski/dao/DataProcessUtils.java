package com.games.klotski.dao;

/**
 * 
 * @author shijun ���ݽ��������ࣺ������ǰ���������,�õ��ߵ�·���Լ��Ƿ������
 * 
 */
public class DataProcessUtils {
	private static final int CL = 2;

	/**
	 * 
	 * @param presentPersonsPosition
	 *            ǰ���������
	 * @return һ��ͨ·
	 */
	public static native Object[] getPath(int[][] presentPersonsPosition);

	public static int[][] autoFindPath(int[][] presentPersonsPosition) {
		 Object []pathObj=getPath(presentPersonsPosition);	
		// ��ö��Ӧ�ø���
				// 1��,2��,3��,4��
		int length = pathObj.length / 2;
		int[][] pathInt = new int[length][CL];
		// ��c�з��ص�һά����ת��Ϊ��ά��������
		for (int columnIndex = 0; columnIndex < CL; columnIndex++) {
			for (int rowIndex = 0; rowIndex < length; rowIndex++) {
				pathInt[rowIndex][columnIndex] = Integer.parseInt((pathObj[rowIndex * CL+ columnIndex].toString()));
			}
		}
		for(int i=0;i<pathInt.length/2;i++){//��Ϊ��������ҪΪ����
			int[] temp=pathInt[i];
			pathInt[i]=pathInt[length-i-1];
			pathInt[length-i-1]=temp;			
		}
		return pathInt;

	}

	// ���ض�̬���ӿ�
	static {
		System.loadLibrary("msvcp110d");
		System.loadLibrary("msvcr110d");
		System.loadLibrary("DataProcessUtils");
	}
}
