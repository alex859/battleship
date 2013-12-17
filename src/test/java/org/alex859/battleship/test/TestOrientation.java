package org.alex859.battleship.test;

import org.alex859.battleship.exceptions.UnrecognizedOrientationException;
import org.alex859.battleship.model.Orientation;
import org.junit.Test;

public class TestOrientation {
	/**
	 * Tests the decode method in case of correct input string.
	 */
	@Test
	public void correctInput() {
		Orientation p=Orientation.decode("N");
		p=Orientation.decode("S");
		p=Orientation.decode("W");
		p=Orientation.decode("E");
	}
	
	/**
	 * Tests the decode method in case of incorrect input string.
	 */
	@Test(expected=UnrecognizedOrientationException.class)
	public void incorrectInput() {
		Orientation p=Orientation.decode("A");
	}
}
