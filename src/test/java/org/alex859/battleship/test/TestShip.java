package org.alex859.battleship.test;

import static org.junit.Assert.*;

import org.alex859.battleship.actions.Action;
import org.alex859.battleship.actions.MoveForward;
import org.alex859.battleship.actions.Shoot;
import org.alex859.battleship.actions.SpinLeft;
import org.alex859.battleship.actions.SpinRight;
import org.alex859.battleship.exceptions.InvalidShipFormatException;
import org.alex859.battleship.exceptions.UnrecognizedOrientationException;
import org.alex859.battleship.model.Orientation;
import org.alex859.battleship.model.Position;
import org.alex859.battleship.model.Ship;
import org.junit.Before;
import org.junit.Test;

public class TestShip {

	private Ship ship; 
	
	@Before
	public void init(){
		ship=new Ship("(1, 1, N)");
	}
	
	@Test
	public void moveNorth() {
		Action move=new MoveForward();
		ship.execute(move);
		assertEquals(new Position(1, 2),ship.getPosition());
		assertEquals(Orientation.NORTH, ship.getOrientation());
		assertEquals(false, ship.isSunk());
		assertEquals("(1, 2, N)", ship.toString());
	}
	
	@Test
	public void spinRightAndMove() {
		Action move=new MoveForward();
		Action spinRight=new SpinRight();
		ship.execute(spinRight);
		ship.execute(move);
		assertEquals(new Position(2, 1),ship.getPosition());
		assertEquals(Orientation.EAST, ship.getOrientation());
		assertEquals(false, ship.isSunk());
		assertEquals("(2, 1, E)", ship.toString());
	}
	
	@Test
	public void spinLeftAndMove() {
		Action move=new MoveForward();
		Action spinLeft=new SpinLeft();
		ship.execute(spinLeft);
		ship.execute(move);
		assertEquals(new Position(0, 1),ship.getPosition());
		assertEquals(Orientation.WEST, ship.getOrientation());
		assertEquals(false, ship.isSunk());
		assertEquals("(0, 1, W)", ship.toString());
	}
	
	@Test
	public void shoot() {
		Action shoot=new Shoot();
		ship.execute(shoot);
		assertEquals(new Position(1, 1),ship.getPosition());
		assertEquals(Orientation.NORTH, ship.getOrientation());
		assertEquals(true, ship.isSunk());
		assertEquals("(1, 1, N) SUNK", ship.toString());
	}
	
	@Test(expected=InvalidShipFormatException.class)
	public void testWrongFormat(){
		ship=new Ship("(1,A, f)");
	}
	
	@Test(expected=UnrecognizedOrientationException.class)
	public void testUnrecognizedOrientation(){
		ship=new Ship("(1, 2, n)");
	}
	


}
