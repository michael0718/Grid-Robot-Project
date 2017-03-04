package edu.toronto.csc301.warehouse;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import edu.toronto.csc301.grid.GridCell;
import edu.toronto.csc301.robot.IGridRobot;
import edu.toronto.csc301.robot.IGridRobot.Direction;

public class PathPlanner implements IPathPlanner{

	@Override
	public Entry<IGridRobot, Direction> nextStep(IWarehouse warehouse, Map<IGridRobot, GridCell> robot2dest) {
		// TODO Auto-generated method stub
		
		IGridRobot robot = warehouse.getRobots().next();
		Direction dir = null;
		GridCell destination = robot2dest.get(robot);
		GridCell NextStep = null;
		
		int distancex = destination.x - robot.getLocation().x; 
		int distancey = destination.y - robot.getLocation().y;
		
		if (distancex < 0) {
			NextStep = GridCell.at(robot.getLocation().x - 1, robot.getLocation().y);
		} else if(distancey < 0){
			NextStep = GridCell.at(robot.getLocation().x, robot.getLocation().y - 1);
		} else if(distancex > 0){
			NextStep = GridCell.at(robot.getLocation().x + 1, robot.getLocation().y);
		} else if(distancey < 0){
			NextStep = GridCell.at(robot.getLocation().x, robot.getLocation().y + 1);
		}
		
		
		if (distancex == 0 && distancey == 0){
			return null;
		}else if(distancex < 0){
			distancex = -1;
		}else if(distancey < 0){
			distancey = -1;
		}else if(distancex > 0){
			distancex = 1;
		}else if(distancey > 0){
			distancey = 1;
		}
		
		
		ArrayList<GridCell> position = new ArrayList<GridCell>();
		Iterator<IGridRobot> ListofRobots = warehouse.getRobots();
		while (ListofRobots.hasNext()) {
			position.add(ListofRobots.next().getLocation());
		}
		
		if ((position.contains(NextStep) == true) || !warehouse.getFloorPlan().hasCell(NextStep)){
			dir = Direction.NORTH;
		}
		else{
			if (distancex == 1) {
				dir = Direction.EAST;
			} else if (distancex == -1) {
				dir = Direction.WEST;
			} else if (distancey == 1){
				dir = Direction.NORTH;
			} else if (distancey == -1){
				dir = Direction.SOUTH;
			} else {
				return null;
			}
		} 
		
		Entry<IGridRobot, Direction> plan = new AbstractMap.SimpleEntry<IGridRobot, Direction>(robot, dir);
		
		return plan;
	}

}
