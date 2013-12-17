package org.alex859.battleship.model;

import org.alex859.battleship.exceptions.UnrecognizedOrientationException;

/**
 * Model the possible ship orientation (North, South, West and East)
 * @author alex859
 *
 */
public enum Orientation {
	NORTH("N"),
	SOUTH("S"),
	WEST("W"),
	EAST("E");
	
	private String letter;
	
	private Orientation(String letter){
		this.letter=letter;
	}
	
	@Override
	public String toString(){
		return this.letter;
	}
	
	/**
	 * 
	 * @param str
	 * @return The orientation associated with the string passed as parameter
	 */
	public static Orientation decode(String str){
		for(Orientation c:Orientation.values()){
			if(c.letter.equals(str)){
				return c;
			}
		}
		throw new UnrecognizedOrientationException(str);
	}
}
