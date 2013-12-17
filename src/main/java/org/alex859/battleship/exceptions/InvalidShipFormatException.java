package org.alex859.battleship.exceptions;


/**
 * Exception thrown when the given string for the ship is not valid
 * @author alex859
 *
 */
public class InvalidShipFormatException extends RuntimeException{

	private static final long serialVersionUID = 285570707920157235L;

	public InvalidShipFormatException(String input){
		super("Ship "+input+" is not valid.");
	}

}
