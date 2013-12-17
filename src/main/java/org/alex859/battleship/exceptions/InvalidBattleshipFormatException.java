package org.alex859.battleship.exceptions;


/**
 * Exception thrown when the given string for the battleship is not valid
 * @author alex859
 *
 */
public class InvalidBattleshipFormatException extends RuntimeException{

	private static final long serialVersionUID = 2494293511901232369L;

	public InvalidBattleshipFormatException(){
		super("Invalid battleship format");
	}

}
