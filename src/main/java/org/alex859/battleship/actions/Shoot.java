package org.alex859.battleship.actions;

import org.alex859.battleship.model.Ship;
import org.apache.log4j.Logger;

/**
 * Spin left the ship
 * @author alex859
 *
 */
public class Shoot implements Action{
	Logger logger=Logger.getLogger(Shoot.class);
	
	public void exec(Ship ship) {
		ship.setSunk(true);
		logger.info("Shoot at position: "+ship.getPosition());
	}

}
