package org.alex859.battleship.actions;

import static org.alex859.battleship.model.Orientation.*;

import org.alex859.battleship.model.Orientation;
import org.alex859.battleship.model.Ship;
import org.apache.log4j.Logger;

/**
 * Spin left the ship
 * @author alex859
 *
 */
public class SpinLeft implements Action{

	public static final Logger logger=Logger.getLogger(SpinLeft.class);
	
	public void exec(Ship ship) {
		Orientation oldO=ship.getOrientation();
		//the final orientation depends on the initial orientation of the ship
		switch (ship.getOrientation()) {
		case EAST:
			ship.setOrientation(NORTH);
			break;
		case NORTH:
			ship.setOrientation(WEST);
			break;
		case WEST:
			ship.setOrientation(SOUTH);
			break;
		case SOUTH:
			ship.setOrientation(EAST);
			break;
		}
		logger.info("Ship spinned left ("+oldO+"->"+ship.getOrientation()+")");
	}

}
