package org.alex859.battleship.actions;

import org.alex859.battleship.model.Position;
import org.alex859.battleship.model.Ship;
import org.apache.log4j.Logger;

/**
 * Move the ship forward modifying its position
 * 
 * @author alex859
 * 
 */
public class MoveForward implements Action {
	private static final Logger logger=Logger.getLogger(MoveForward.class);
	
	public void exec(Ship ship) {
		// the direction depends on the orientation of the ship
		Position p = ship.getPosition();
		Position oldP=new Position(p);
		switch (ship.getOrientation()) {
		case EAST:
			p.setX(p.getX() + 1);
			break;
		case NORTH:
			p.setY(p.getY() + 1);
			break;
		case WEST:
			p.setX(p.getX() - 1);
			break;
		case SOUTH:
			p.setY(p.getY() - 1);
			break;
		}
		logger.info("Ship moved from "+oldP+" to "+p);

	}

}
