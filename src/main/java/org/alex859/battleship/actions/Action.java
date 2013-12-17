package org.alex859.battleship.actions;

import org.alex859.battleship.model.Ship;

/**
 * Interface for the action that can be taken on a ship. This action modifies the
 * internal state of the ship.
 * @author alex859
 *
 */
public interface Action {
	public void exec(Ship ship);
}
