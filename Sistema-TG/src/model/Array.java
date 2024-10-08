package model;

import controller.AtiradorDAO;

public class Array {

	public static int getMaiorValorArray(int[] array, int modificador) {
		int maior = Integer.MIN_VALUE;

		for (int i = modificador; i < array.length; i++) {

			if (array[i] > maior) {
				maior = array[i];

			}
		}

		return maior;
	}
	
	public static int[] getIndicePorId(int[] idGuarda) {
		
		int[] indiceGuarda = new int[idGuarda.length];

		int[] IDs = AtiradorDAO.getArrayIdAtiradores();
		
		for(int i = 0; i < 4; i++) {
			
			for(int j = 0; j < IDs.length; j++) {
				if(IDs[j] == idGuarda[i]) {
					indiceGuarda[i] = j;
				}
			}
			
		}
		
		
		return indiceGuarda;
	}
	
	public static void aumentarArray(int[] array) {
		
		for(int i = 0; i < array.length; i++) {
			array[i]++;
		}
		
		
	}
	
}
