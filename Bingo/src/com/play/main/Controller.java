package com.play.main;

import java.util.Scanner;

import com.play.services.Resources;

public class Controller { 

	public static void main(String[] args) {
		Resources resource = new Resources();

		
		int bingo = 0;

		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the number of box on each row (recommended: 5) :");
		int element = sc.nextInt();
		int[][] box = resource.generateNo(element);
		System.out.println("Here is your bingo chart : !!All the Best!!");
		resource.printBox(box, bingo, element);
		
		int num = -1;
		while (true) {

			System.out.println("Enter Your number : ");
			num = sc.nextInt();

			bingo = resource.crossElement(box, bingo, num); 
			resource.printBox(box, bingo, element);

		}

	}

}
