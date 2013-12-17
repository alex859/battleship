package org.alex859.battleship.exceptions;


/**
 * Exception thrown when the given string for the board is not valid
 * @author alex859
 *
 */
public class InvalidBoardFormatException extends RuntimeException{

	private static final long serialVersionUID = 6001054315793846143L;

	public InvalidBoardFormatException(String input){
		super("Board "+input+" is not valid.");
	}

}
