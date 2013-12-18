package org.alex859.battleship.test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.alex859.battleship.model.Position;
import org.junit.Test;

public class TestPosition {
	
	/**
	 * Test the equals method of class Position
	 */
	@Test
	public void testEquals(){
		Position p1=new Position(2, 3);
		Position p2=new Position(2, 3);
		Position p3=new Position(2, 2);
		assertEquals(p1, p2);
		assertThat(p1, not(equalTo(p3)));
	}


}
