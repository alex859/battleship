package org.alex859.battleship.exceptions;

/**
 * Exception thrown when we cannot recognize a cardinal point from the input string
 * @author alex859
 *
 */
public class UnrecognizedActionException extends RuntimeException{

	private static final long serialVersionUID = -6803963821282499240L;

	public UnrecognizedActionException(String str){
		super("Letter "+str+" not recognized as an action.");
	}

}
