package org.alex859.battleship.test;

import static org.junit.Assert.*;

import org.alex859.battleship.exceptions.InvalidBoardFormatException;
import org.alex859.battleship.exceptions.InvalidPositionException;
import org.alex859.battleship.model.Board;
import org.alex859.battleship.model.Position;
import org.alex859.battleship.model.Ship;
import org.junit.Before;
import org.junit.Test;

public class TestBoard {
	private Board board;
	
	@Before
	public void setup(){
		board=new Board("(5, 6)");
	}
	
	/**
	 * Test the correctness of a position inside the board
	 */
	@Test
	public void testPositionCorrectness(){
		assertEquals(5, board.getMaxX());
		assertEquals(6, board.getMaxY());
		Position p=new Position(1,1);
		assertTrue(board.check(p));
		p.setX(-1);
		assertFalse(board.check(p));
		p.setX(7);
		assertFalse(board.check(p));
		p.setX(2);
		p.setY(-1);
		assertFalse(board.check(p));
		p.setY(10);
		assertFalse(board.check(p));
	}
	
	@Test(expected=InvalidBoardFormatException.class)
	public void testInvalidBoardIfNoSpace(){
		board=new Board("(5,5)");
	}
	
	@Test(expected=InvalidBoardFormatException.class)
	public void testInvalidBoardWithNegativeNumbers(){
		board=new Board("(-5,-5)");
	}
	
	@Test
	public void testPositionWithAShipAlreadyThere(){
		Ship s=new Ship("(2, 2, E)");
		try {
			board.addShip(s);
		} catch (InvalidPositionException e) {
			
		};
		assertFalse(board.check(new Position(2, 2)));
	}
	
	@Test
	public void removeShip(){
		try {
			board.addShip(new Ship("(2, 2, E)"));
			board.removeShipAt(new Position(2,2));
			assertEquals(0, board.getShipsMap().size());
		} catch (InvalidPositionException e) {

		}
		
	}



}
