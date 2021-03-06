package org.alex859.battleship.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.alex859.battleship.actions.Action;
import org.alex859.battleship.exceptions.InvalidShipFormatException;

/**
 * Model a ship
 * @author alex859
 *
 */
public class Ship implements Comparable<Ship>{
	private Position position;
	private Orientation orientation;
	private Boolean sunk=false;
	/**
	 * An auto increasing ID for the ship. Useful to give an ordered output list of ships
	 */
	private int id;
	
	/**
	 * Field used to generate the id of the ship.
	 */
	private static int idGen=0;
	
	private static Pattern pattern=Pattern.compile("\\s?\\((\\d+),\\s(\\d+)\\,\\s(\\D)\\)");
	
	/**
	 * Creates a ship with the given properties
	 * @param str The string with format (X, Y, O) where X and Y are the coordinates of the ship
	 * 			  and O is the orientation
	 */
	public Ship(String str){
		Matcher m=pattern.matcher(str);
		if(m.matches()){
			int x=Integer.parseInt(m.group(1));
			int y=Integer.parseInt(m.group(2));
			String o=m.group(3);
			this.position=new Position(x, y);
			this.orientation=Orientation.decode(o);
			this.id=idGen++;
		}else{
			throw new InvalidShipFormatException(str);
		}
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Orientation getOrientation() {
		return orientation;
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	public Boolean isSunk() {
		return sunk;
	}

	public void setSunk(Boolean sunk) {
		this.sunk = sunk;
	}

	/**
	 * Run the action passed as parameter on this ship
	 * @param action The action to be performed
	 */
	public void execute(Action action){
		action.exec(this);
	}
	
	@Override
	public String toString() {
		return "(" + position.getX()+", "+position.getY() + ", " + orientation
				+ ")"+(sunk ? " SUNK" : "");
	}

	public int compareTo(Ship o) {
		return this.id>o.id ? 1 :-1;
	}
	
	

}
