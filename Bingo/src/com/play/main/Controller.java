package com.play.main;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.play.services.Resources;

public class Controller { 

	public static void main(String[] args) {
		Resources resource = new Resources();

		
		int bingo = 0;

		Scanner sc = new Scanner(System.in);
		
		//players
		
		System.out.println("Enter the number of players : ");
		int players;
		try {
			players = sc.nextInt();
		} catch (InputMismatchException e) {
			System.err.println("Enter players details in the form of 1, 2, 3, 4, etc.");
			players = sc.nextInt();
		}
		
		int player = 0;
		
		System.out.println("Enter the number of box on each row (recommended: 5) :");
		int limit;
		try {
			limit = sc.nextInt();
		} catch (InputMismatchException e) {
			System.err.println("Enter number row/column size in numerical format.");
			limit = sc.nextInt();
		}
		
		int[][] box = resource.generateNo(limit);
		
		System.out.println("Here is your bingo chart : !!All the Best!!");
		resource.printBox(box, bingo, limit);
		
		System.out.println("\n\n__Note__: \n\tYou can quit the game any time you want, by entering -1.\n\tRemember You are player 1.\n\n");
		
		int num = -2;
		System.out.println("\n\nEnter number on behalf of Player "+(++player)+" : ");
		try {
			num = sc.nextInt();
		} catch (InputMismatchException e) {
			System.err.println("Enter numbers in numerical format.");
			num = sc.nextInt();
		}
		while (num != -1) {
			
			if(player == players)
				player = 0;
			
			bingo = resource.crossElement(box, bingo, num, limit); 
			if(bingo == -1)
				break;	//game over
			
			resource.printBox(box, bingo, limit);

			System.out.println("\n\nEnter number on behalf of Player "+(++player)+" : ");
			try {
				num = sc.nextInt();
			} catch (InputMismatchException e) {
				System.err.println("Enter numbers in numerical format.");
				num = sc.nextInt();
			}
		}
		resource.printBox(box, limit, limit);	//final Print
		sc.close();
	}

}
