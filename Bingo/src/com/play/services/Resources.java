package com.play.services;

import java.util.ArrayList;
import java.util.List;

public class Resources {

	public int[][] generateNo(int element) {

		List<Integer> numbers = new ArrayList<Integer>(element);

		for (int i = 1; i <= element * element; i++)
			numbers.add(i);

		// Collections.shuffle(numbers);

		int[][] numArr = new int[element][element];

		for (int i = 0, counter = 0; i < element; i++)
			for (int j = 0; j < element; j++, counter++)
				numArr[i][j] = numbers.get(counter);

		return numArr;
	}

	public String[] crossElement(int[][] box, String[] bingo, int element) {

		boolean leftDiago = false, rightDiago = false;

		for (int i = 0; i < box.length; i++) {
			boolean out = false;

			for (int j = 0; j < box[i].length; j++)
				if (box[i][j] == element) {
					box[i][j] = 0;

					if (i == j)
						leftDiago = true;
					if (i == box.length - i - 1)
						rightDiago = true;
					
					out = true;
					break;
				}
			if(out)
				break;
		}
		int strickCounter = 0;

		// exact middle element
		if (leftDiago && rightDiago) {
			strickCounter += (checkingBingoTopLeftToButtomRight(box)) ? 1 : 0;
			strickCounter += (checkingBingoTopRightToButtomLeft(box)) ? 1 : 0;
		}

		// check the element does exist : top left corner
		else if (leftDiago)
			strickCounter += (checkingBingoTopLeftToButtomRight(box)) ? 1 : 0;

		// check the element does exist : top right corner
		else if (rightDiago)
			strickCounter += (checkingBingoTopRightToButtomLeft(box)) ? 1 : 0;

		// check for horizontal & vertical axis
		strickCounter += (checkingBingoHorizontalAxis(box)) ? 1 : 0;
		strickCounter += (checkingBingoVerticalAxis(box)) ? 1 : 0;

		return validateBingo(strickCounter, bingo);
	}

	private String[] validateBingo(int strickCounter, String[] bingo) {

		int bingoCounter = 0;
		for (int i = 0; i < bingo.length; i++) {
			if (bingo[i].equals("?") && strickCounter > 0) {
				bingoCounter++;
				strickCounter--;
				bingo[i] = "-";
			} else if (bingo[i].equals("-")) {
				bingoCounter++;
			}
		}
		if (bingoCounter == 5) {
			System.out.println("!!BINGO!!");
		}
		return bingo;
	}

	private boolean checkingBingoVerticalAxis(int[][] box) {

		// checking the vertical axis
		for (int i = 0; i < box.length; i++)
			for (int j = 0; j < box[i].length; j++)
				if (box[j][i] != 0)
					return false;
		return true;
	}

	private boolean checkingBingoHorizontalAxis(int[][] box) {

		// checking the horizontal axis
		for (int i = 0; i < box.length; i++)
			for (int j = 0; j < box[i].length; j++)
				if (box[i][j] != 0)
					return false;
		return true;
	}

	private boolean checkingBingoTopRightToButtomLeft(int[][] box) {

		// checking the diagonal - top right to bottom left
		for (int i = 0, j = box.length - 1; i < box.length; i++, j--)
			if (box[i][j] != 0)
				return false;
		return true;
	}

	private boolean checkingBingoTopLeftToButtomRight(int[][] box) {

		// checking the diagonal - top left to bottom right
		for (int i = 0; i < box.length; i++)
			if (box[i][i] != 0)
				return false;
		return true;
	}

	public void printBox(int[][] box, String[] bingo) {

		for (int i = 0; i < box.length; i++) {
			for (int j = 0; j < box[i].length; j++) {
				System.out.printf("%-5d", box[i][j]);
			}
			System.out.println();
		}

		for (int i = 0; i < bingo.length; i++) {
			System.out.printf("%-5s", bingo[i]);
		}
	}

}
