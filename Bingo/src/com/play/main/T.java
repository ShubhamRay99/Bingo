package com.play.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class T {
	public static void main(String[] args) {
		
		int element = 10;
		List<Integer> numbers = new ArrayList<Integer>(element);

		for (int i = 1; i <= element * element; i++)
			numbers.add(i);

		 Collections.shuffle(numbers);

		int[][] numArr = new int[element][element];

		for (int i = 0, counter = 0; i < element; i++)
			for (int j = 0; j < element; j++, counter++)
				numArr[i][j] = numbers.get(counter);
		
		for (int i = 0; i < numArr.length; i++) {
			for (int j = 0; j < numArr[i].length; j++) {
				System.out.printf("%-5d", numArr[i][j]);
			}
			System.out.println();
		}
		String[] bingo = new String[] {"B","I","N","G","O"};
		for (int i = 0; i < bingo.length; i++) {
			System.out.printf("%-5s", bingo[i]);
		}
		for (int i = 0; i < bingo.length; i++) {
			System.out.printf("%-5s", bingo[i]);
		}
	}
}
