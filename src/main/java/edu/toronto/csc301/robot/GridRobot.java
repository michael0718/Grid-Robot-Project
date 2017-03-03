package edu.toronto.csc301.robot;

import edu.toronto.csc301.grid.GridCell;
import edu.toronto.csc301.robot.IGridRobot;

public class GridRobot implements IGridRobot{
	
	private GridCell cell;
	private int x;
	private int y;
	
	public GridRobot(GridCell cell) throws NullPointerException{
		this.cell = cell;
		if (cell == null){
			throw new NullPointerException();
		}
		this.x = cell.x;
		this.y = cell.y;
	}
 
	@Override
	public GridCell getLocation() {
		// TODO Auto-generated method stub
		return cell.at(cell.x, cell.y);
	}

	@Override
	public void step(Direction direction) {
		// TODO Auto-generated method stub
		
		switch(direction){
			case NORTH:
				y ++;
				cell = cell.at(x, y);
				break;
			case EAST:
				x++;
				cell = cell.at(x, y);
				break;
			case SOUTH:
				y--;
				cell = cell.at(x, y);
				break;
			case WEST:
				x--;
				cell = cell.at(x, y);
				break;
			}
	}

	@Override
	public void startListening(StepListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopListening(StepListener listener) {
		// TODO Auto-generated method stub
		
	}

}
