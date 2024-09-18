package model;

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
	
	public static int[] getIndicePorId(int[] array) {
		
		for(int i = 0; i < array.length; i++) {
			array[i]--;
		}
		
		int[] arrayNovo = {array[0], array[1], array[2], array[3]};
		
		for(int i = 0; i < array.length; i++) {
			array[i]++;
		}
		
		return arrayNovo;
	}
	
	public static void aumentarArray(int[] array) {
		
		for(int i = 0; i < array.length; i++) {
			array[i]++;
		}
		
		
	}
	
}
