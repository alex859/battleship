package org.alex859.battleship.actions;

import org.alex859.battleship.model.Ship;

/**
 * Spin left the ship
 * @author alex859
 *
 */
public class Shoot implements Action{

	public void exec(Ship ship) {
		ship.setSunk(true);
	}

}
