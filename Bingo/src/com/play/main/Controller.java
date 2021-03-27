package com.play.main;

import com.play.services.Resources;

public class Controller {

	public static void main(String[] args) {
		Resources resource = new Resources();
		
		int[][] box = resource.generateNo(5);
		String[] bingo =  new String[] { "?", "?", "?", "?", "?" };
		
		resource.crossElement(box, bingo, 10);
		resource.printBox(box, bingo);
	}

}
