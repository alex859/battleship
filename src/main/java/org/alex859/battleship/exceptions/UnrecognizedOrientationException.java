package org.alex859.battleship.exceptions;

/**
 * Exception thrown when we cannot recognize a cardinal point from the input string
 * @author alex859
 *
 */
public class UnrecognizedOrientationException extends RuntimeException{

	private static final long serialVersionUID = 2320307146833023267L;

	public UnrecognizedOrientationException(String str){
		super("Letter "+str+" not recognized as a cardinal point.");
	}

}
