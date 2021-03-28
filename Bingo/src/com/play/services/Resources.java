package com.play.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.play.customException.BingoException;

public class Resources {

	BingoException bingoException;

	public Resources() {
		bingoException = new BingoException();
	}

	public int[][] generateNo(int element) {

		List<Integer> numbers = new ArrayList<Integer>(element);

		for (int i = 1; i <= element * element; i++)
			numbers.add(i);

		Collections.shuffle(numbers);

		int[][] numArr = new int[element][element];

		for (int i = 0, counter = 0; i < element; i++)
			for (int j = 0; j < element; j++, counter++)
				numArr[i][j] = numbers.get(counter);

		return numArr;
	}

	public int crossElement(int[][] box, int bingo,final int element, int limit) {

		try {
			bingoException.userInputNumber(element, limit);
		} catch (BingoException e) {
			System.err.println(e.getMessage());
			return bingo;
		}

		boolean leftDiago = false, rightDiago = false;

		int I = -1, J = -1;
		boolean out = false;
		for (int i = 0; i < box.length; i++) {

			for (int j = 0; j < box[i].length; j++)
				if (box[i][j] == element) {
					box[i][j] = 0;

					if (i == j)
						leftDiago = true;
					if (j == box.length - i - 1)
						rightDiago = true;

					I = i;
					J = j;
					out = true;
					break;
				}
			if (out)
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
		strickCounter += (checkingBingoHorizontalAxis(box, I)) ? 1 : 0;
		strickCounter += (checkingBingoVerticalAxis(box, J)) ? 1 : 0;

		return validateBingo(strickCounter, bingo, limit);
	}

	private int validateBingo(int nowBingo, int prevBingo, int bingo) {

		prevBingo += nowBingo;

		if (prevBingo == bingo) {
			return -1;
		}
		return prevBingo;
	}

	private boolean checkingBingoVerticalAxis(int[][] box, int J) {

		// checking the vertical line only
		for (int i = 0, j = J; i < box[J].length; i++)
			if (box[i][j] != 0)
				return false;
		return true;
	}

	private boolean checkingBingoHorizontalAxis(int[][] box, int I) {

		// checking the horizontal line only
		for (int i = I, j = 0; j < box[I].length; j++)
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

	public void printBox(int[][] box, int bingoCounter, int limit) {

		for (int i = 0; i < box.length; i++) {
			for (int j = 0; j < box[i].length; j++) {
				System.out.printf("%-5d", box[i][j]);
			}
			System.out.println();
		}

		String[] bingo = { "B", "I", "N", "G", "O" };

		int i = 0;
		while (limit-- > 0) {
			if (i == bingo.length)
				i = 0;

			if (bingoCounter-- > 0)
				System.out.printf("%-5s", bingo[i++]);
			else
				System.out.printf("%-5s", "-");
		}
	}

}
