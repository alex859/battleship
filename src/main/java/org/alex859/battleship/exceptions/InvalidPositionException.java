package org.alex859.battleship.exceptions;

import org.alex859.battleship.model.Position;

/**
 * Exception thrown when the position is not valid (i.e. it is outside the board)
 * @author alex859
 *
 */
public class InvalidPositionException extends Exception{

	private static final long serialVersionUID = -2795673229512728596L;

	public InvalidPositionException(Position p){
		super("Position "+p+" is not valid.");
	}

}
