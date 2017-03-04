package edu.toronto.csc301.warehouse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import edu.toronto.csc301.grid.GridCell;
import edu.toronto.csc301.grid.IGrid;
import edu.toronto.csc301.robot.GridRobot;
import edu.toronto.csc301.robot.IGridRobot;
import edu.toronto.csc301.robot.IGridRobot.Direction;

public class Warehouse implements IWarehouse, IGridRobot.StepListener{
	
	private HashMap<GridCell, IGridRobot> board;
	private final Map<IGridRobot, Direction> map;
	private IGrid<Rack> grid;
	private ArrayList<IGridRobot> ig = new ArrayList<IGridRobot>();
	List<Consumer<IWarehouse>> obs = new ArrayList<Consumer<IWarehouse>>();
	
	public Warehouse(IGrid<Rack> grid) throws NullPointerException{
		this.board = new HashMap<GridCell, IGridRobot>();
		this.map = new HashMap<IGridRobot, Direction>();
		//Map<IGridRobot, Direction> Cmap = map;
		
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
		if (board.get(initialLocation) != null || !board.containsKey(initialLocation)){
			throw new IllegalArgumentException();
		}

		IGridRobot gr = new GridRobot(initialLocation);
		board.put(initialLocation, gr);
		ig.add(gr);
		
		
		gr.startListening(this);
		
		
		for(int i = 0; i<obs.size(); i++){
			
			obs.get(i).accept(this);
			
		}
		
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
		Map<IGridRobot, Direction> copy = new HashMap<IGridRobot, Direction>();
		copy.clear();
		copy.putAll(map);
		
		return copy;
	}
	

	@Override
	public void subscribe(Consumer<IWarehouse> observer) {
		// TODO Auto-generated method stub
		obs.add(observer);
	}

	@Override
	public void unsubscribe(Consumer<IWarehouse> observer) {
		// TODO Auto-generated method stub
		obs.remove(observer);
	}

	@Override
	public void onStepStart(IGridRobot robot, Direction direction) {
		// TODO Auto-generated method stub
		map.put(robot, direction);
		
		for(int i = 0; i<obs.size(); i++){
			
			obs.get(i).accept(this);
			
		}
		
	}

	@Override
	public void onStepEnd(IGridRobot robot, Direction direction) {
		// TODO Auto-generated method stub
		
		map.remove(robot);
		for(int i = 0; i<obs.size(); i++){
			
			obs.get(i).accept(this);
			
		}
		
	}

}
