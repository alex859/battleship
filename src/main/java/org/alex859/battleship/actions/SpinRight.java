package org.alex859.battleship.actions;

import static org.alex859.battleship.model.Orientation.*;

import org.alex859.battleship.model.Orientation;
import org.alex859.battleship.model.Ship;
import org.apache.log4j.Logger;

/**
 * Spin right the ship
 * @author alex859
 *
 */
public class SpinRight implements Action{

	public static final Logger logger=Logger.getLogger(SpinRight.class);

	public void exec(Ship ship) {
		Orientation oldO=ship.getOrientation();
		//the final orientation depends on the initial orientation of the ship
		switch (ship.getOrientation()) {
		case EAST:
			ship.setOrientation(SOUTH);
			break;
		case NORTH:
			ship.setOrientation(EAST);
			break;
		case WEST:
			ship.setOrientation(NORTH);
			break;
		case SOUTH:
			ship.setOrientation(WEST);
			break;
		}
		logger.info("Ship spinned right ("+oldO+"->"+ship.getOrientation()+")");
	}

}
