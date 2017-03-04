package edu.toronto.csc301.robot;

import java.util.ArrayList;
import java.util.List;

import edu.toronto.csc301.grid.GridCell;
import edu.toronto.csc301.robot.IGridRobot;

public class GridRobot implements IGridRobot{
	
	private GridCell cell;
	private int x;
	private int y;
	private boolean changed;
	private Direction dir;
	List<StepListener> start = new ArrayList<StepListener>();
	//List<StepListener> start = new ArrayList<StepListener>();
	
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
		changed = false;
		switch(direction){
			case NORTH:
				for (int i = 0; i<start.size(); i++){
					start.get(i).onStepStart(this, direction);
				}
				y ++;
				cell = cell.at(x, y);
				changed = true;
				this.dir = direction;
				
				for (int j = 0; j<start.size(); j++){
					start.get(j).onStepEnd(this, direction);
				}
				break;
			case EAST:
				for (int i = 0; i<start.size(); i++){
					start.get(i).onStepStart(this, direction);
				}
				x++;
				cell = cell.at(x, y);
				changed = true;
				this.dir = direction;
				
				for (int j = 0; j<start.size(); j++){
					start.get(j).onStepEnd(this, direction);
				}
				break;
			case SOUTH:
				for (int i = 0; i<start.size(); i++){
					start.get(i).onStepStart(this, direction);
				}
				y--;
				cell = cell.at(x, y);
				changed = true;
				this.dir = direction;
				
				for (int j = 0; j<start.size(); j++){
					start.get(j).onStepEnd(this, direction);
				}
				break;
			case WEST:
				for (int i = 0; i<start.size(); i++){
					start.get(i).onStepStart(this, direction);
				}
				x--;
				cell = cell.at(x, y);
				changed = true;
				this.dir = direction;
				
				for (int j = 0; j<start.size(); j++){
					start.get(j).onStepEnd(this, direction);
				}
				break;
			}
	}
	public boolean hasChanged(){
		return changed;
	}

	@Override
	public void startListening(StepListener listener) {
		// TODO Auto-generated method stub
		start.add(listener);
		/*if (hasChanged()){
			listener.onStepStart(this, dir);
		}*/
	}

	@Override
	public void stopListening(StepListener listener) {
		// TODO Auto-generated method stub
		start.remove(listener);
	}

}
