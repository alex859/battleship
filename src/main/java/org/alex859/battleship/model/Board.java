package org.alex859.battleship.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.alex859.battleship.exceptions.InvalidBoardFormatException;
import org.alex859.battleship.exceptions.InvalidPositionException;

/**
 * The board contains the ships and is responsible of checking the correctness of their positions
 * @author alex859
 *
 */
public class Board {
	private int maxX;
	private int maxY;
	private Map<Position, Ship> shipsMap=new HashMap<Position, Ship>();
	private static Pattern pattern=Pattern.compile("\\((\\d+),\\s(\\d+)\\)");
	
	/**
	 * Creates a board with the dimensions given by the string.
	 * @param str The input string MUST have the format (X, Y) where X and Y are integer numbers
	 */
	public Board(String str){
		Matcher m=pattern.matcher(str);
		if(m.matches()){
			this.maxX=Integer.parseInt(m.group(1));
			this.maxY=Integer.parseInt(m.group(2));
		}else{
			throw new InvalidBoardFormatException(str);
		}
	}
	
	/**
	 * Checks if the given position is valid in the board:
	 * a position is valid if it is inside the board and if is free (that is no ship is already there)
	 * @param p The position to be tested
	 * @return true if the position is valid, false otherwise
	 */
	public boolean check(Position p){
		if(p.getX()<0 || p.getY()<0 || p.getX()>this.maxX || p.getY()>this.maxY || shipsMap.containsKey(p)){
			return false;
		}
		return true;
		
	}

	public Map<Position, Ship> getShipsMap() {
		return shipsMap;
	}
	
	/**
	 * Adds a ship to the board after checking its position validity
	 * @param ship The ship to be added
	 * @throws InvalidPositionException 
	 */
	public void addShip(Ship ship) throws InvalidPositionException{
		if(check(ship.getPosition())){
			this.shipsMap.put(new Position(ship.getPosition()), ship);
		}else{
			throw new InvalidPositionException(ship.getPosition());
		}
	}
	
	/**
	 * Removes a ship from the board
	 * @param ship The ship to be removes
	 */
	public void removeShipAt(Position p){
		this.shipsMap.remove(p);
	}
	

	public int getMaxX() {
		return maxX;
	}

	public int getMaxY() {
		return maxY;
	}

	@Override
	public String toString() {
		StringBuilder builder=new StringBuilder();
		List<Ship> l=new ArrayList<Ship>(shipsMap.values());
		Collections.sort(l);
		for(Ship s:l){
			builder.append(s.toString()).append("\n");
		}
		return builder.toString();
	}
	
	

}
