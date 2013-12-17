package org.alex859.battleship.test;

import static org.junit.Assert.*;

import org.alex859.battleship.exceptions.InvalidBattleshipFormatException;
import org.alex859.battleship.model.Battleship;
import org.alex859.battleship.model.Orientation;
import org.alex859.battleship.model.Position;
import org.alex859.battleship.model.Ship;
import org.junit.Before;
import org.junit.Test;

public class TestBattleship {
	private Battleship b;
	
	@Before
	public void setup(){
		b=new Battleship("(4, 4)\n(0, 1, N) (32, 3, E) (2, 0, E)\n(0, 1) MRMMTRMMLM\n(2, 0) MMM\n(4, 0)");
	}
	
	@Test
	public void testGameCreation() {
		assertEquals(4, b.getBoard().getMaxX());
		assertEquals(4, b.getBoard().getMaxY());
		//one ship has invalid position so it will be skipped
		assertEquals(2, b.getBoard().getShipsMap().size());
		assertEquals(3, b.getActionStrings().size());
	}
	
	@Test(expected=InvalidBattleshipFormatException.class)
	public void testGameCreationBadInput() {
		
		b=new Battleship("(A, 4)\n(1, 2, N) (3, 3, E)\n");
	}
	
	@Test(expected=InvalidBattleshipFormatException.class)
	public void testInvalidShipPosition() {
		
		b=new Battleship("(A, 4)\n(1, 2, N) (3, 3, E)\n");
	}

	@Test
	public void testPlaySimpleGame(){
		b.play();
		Position expectedP1=new Position(3,1);
		Ship s1=b.getBoard().getShipsMap().get(expectedP1);
		assertNotNull(s1);
		assertEquals(expectedP1, s1.getPosition());
		assertEquals(Orientation.EAST, s1.getOrientation());
		assertFalse(s1.isSunk());
		//the other ship should have been sunk
		Position expectedP2=new Position(4,0);
		Ship s2=b.getBoard().getShipsMap().get(expectedP2);
		assertNotNull(s2);
		assertEquals(expectedP2, s2.getPosition());
		assertEquals(Orientation.EAST, s2.getOrientation());
		assertTrue(s2.isSunk());
	}

	@Test
	public void testGivenExample(){
		b=new Battleship("(5, 5)\n(1, 2, N) (3, 3, E)\n(1, 2) LMLMLMLMM\n(2, 3)\n(3, 3) MMRMMRMRRM\n(1, 3)");
		b.play();
		assertEquals("(1, 3, N) SUNK\n(5, 1, E)", b.getBoard().toString());
	}
}
