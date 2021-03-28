package com.play.customException;

import java.util.HashSet;
import java.util.Set;

public class BingoException extends Exception {

	private static final long serialVersionUID = 1L;

	Set<Integer> calledOutNumbers;

	public BingoException() {
		
		calledOutNumbers = new HashSet<Integer>();
	}
	
	public BingoException(String string) {
		super(string);
	}


	public void userInputNumber(int element, int limit) throws BingoException {
		if(element > (limit*limit) || element <= 0)
			throw new BingoException("The supplied number "+element+" doesn't matches with the desired limit, kindly choose a different number");

			
		if (!calledOutNumbers.add(element))
			throw new BingoException("Number "+element+" has already been called before, kindly choose a different number.");
	}

}
