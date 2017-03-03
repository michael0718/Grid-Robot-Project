package edu.toronto.csc301.warehouse;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Consumer;

import edu.toronto.csc301.grid.GridCell;
import edu.toronto.csc301.grid.IGrid;
import edu.toronto.csc301.robot.GridRobot;
import edu.toronto.csc301.robot.IGridRobot;
import edu.toronto.csc301.robot.IGridRobot.Direction;

public class Warehouse implements IWarehouse{
	
	private HashMap<GridCell, IGridRobot> board;
	private HashMap<IGridRobot, Direction> map;
	private IGrid<Rack> grid;
	private ArrayList<IGridRobot> ig = new ArrayList<IGridRobot>();
	
	public Warehouse(IGrid<Rack> grid) throws NullPointerException{
		this.board = new HashMap<GridCell, IGridRobot>();
		this.map = new HashMap<IGridRobot, Direction>();
		
		this.grid = grid;
		if (grid == null){
			throw new NullPointerException();
		}
		GridCell put;
		Iterator<GridCell> it = grid.getGridCells();
		while(it.hasNext()){
			put = it.next();
			board.put(put, null);
		}
	}

	@Override
	public IGrid<Rack> getFloorPlan() {
		// TODO Auto-generated method stub
		return this.grid;
	}

	@Override
	public IGridRobot addRobot(GridCell initialLocation) {
		// TODO Auto-generated method stub
		int i = 0;
		if (board.get(initialLocation) != null || !board.containsKey(initialLocation)){
			throw new IllegalArgumentException();
		}

		IGridRobot gr = new GridRobot(initialLocation);
		board.put(initialLocation, gr);
		ig.add(gr);
		
		return gr;
	}

	@Override
	public Iterator<IGridRobot> getRobots() {
		// TODO Auto-generated method stub
		return this.ig.iterator();
		//return this.board.values().iterator();
	}

	@Override
	public Map<IGridRobot, Direction> getRobotsInMotion() {
		// TODO Auto-generated method stub
		//map.clear();
		return map;
	}

	@Override
	public void subscribe(Consumer<IWarehouse> observer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unsubscribe(Consumer<IWarehouse> observer) {
		// TODO Auto-generated method stub
		
	}

}
