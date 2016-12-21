package gameplayer.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Path {
	private HashMap <Cell, Cell> path;
	private ArrayList<Cell> allCoordinates;
	private Cell start, end;
	


	
	public Path(ArrayList<Cell> allCoordinates, Cell[][] grid) {
		this.allCoordinates = allCoordinates;
		this.start = allCoordinates.get(0);
		this.end = allCoordinates.get(this.allCoordinates.size()-1);
		this.path = new HashMap<Cell, Cell>();
		for(int i = 0; i < allCoordinates.size(); i++){
			if( i == allCoordinates.size() - 1){
				path.put(allCoordinates.get(i), null);
			}
			else{
				path.put(allCoordinates.get(i), allCoordinates.get(i+1));
			}		
		}
	}
	
	
	
	public Cell getNext(Cell c){

		return path.get(c);
	}
	
	
	/**
	 * for front end displaying multiple paths
	 */
	public Cell getPathStart(){
		for(int i = 0; i < allCoordinates.size() - 1; i++){
			Cell current =  allCoordinates.get(i);
			Cell next = allCoordinates.get(i+1);
			current.setNext(next);
		}		
		//System.out.println("Is there a getPathStart (Path.java line: 13): "+this.start);
		return this.start;
	}
	
	
	/**
	 * for enemy entering
	 * @return
	 */
	public Cell getStartPoint(){
		return this.start;
	}
	
	public Cell getPathEndPoint(){
		return this.end;
	}
	
	

	
	

}
