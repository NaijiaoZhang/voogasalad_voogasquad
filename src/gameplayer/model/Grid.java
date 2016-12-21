package gameplayer.model;

import java.util.HashMap;
import java.util.List;

import gameplayer.model.tower.Tower;

public class Grid {

	private Cell[][] grid;
	private Cell start, end;
	private int row;
	private int col;
	private boolean noPath;
	private HashMap<Integer, Path> allPath;

	//boolean value noPath,or path encoded 


	public Grid(int x, int y) {
		this.row = x;
		this.col = y;
		grid = new Cell[x][y];
		for(int i = 0; i < grid.length;i++){
			for(int j = 0; j < grid[0].length; j++){
				grid[i][j] = new Cell(i, j);
			}
		}	
	}

	//testing
	public void printGrid(){
		//System.out.println("Grid");
		Cell current = this.start;
		while (current != null) {
			//System.out.println(current.getX() + ", " + current.getY());
			current = current.getNext();
		}
	}

	public Cell getNext(int pathID, Cell c){
		if(this.noPath){
			return getNoPathNext(c);		
		}
		else{
			return this.getPath(pathID).getNext(c);
		}

	}


	private Cell getNoPathNext(Cell c){

		int xdirection = (int)Math.signum( this.end.getX() - c.getX());

		if (xdirection != 0){
			if(availableToMoveTo(c.getX()+xdirection,c.getY())){
				return this.grid[c.getX()+xdirection][c.getY()];
			}
		}

		int ydirection = (int)Math.signum(this.end.getY() - c.getY() );
		if (ydirection != 0){
			if(availableToMoveTo(c.getX(), c.getY() + ydirection)){
				return this.grid[c.getX()][c.getY() + ydirection];
			}
			else{
				if(availableToMoveTo(c.getX(),c.getY()-ydirection)){
					return this.grid[c.getX()][c.getY()-ydirection];
				}
			}
		}

		if (xdirection != 0){
			if(availableToMoveTo(c.getX()-xdirection,c.getY())){
				return this.grid[c.getX()-xdirection][c.getY()];
			}
		}
		
		return c;
	}
	
	private Boolean availableToMoveTo(int x, int y){
		if(x >= this.row || x < 0 || y >= this.col || y < 0)
			return false;
		
		
		if (this.grid[x][y].getTower() != null)
			return false;
		
		return true;
		
	}
	
	
	

	public void setAllPath(HashMap<Integer, Path> allPath){
		this.allPath = allPath;
	}


	public void setNoPath(boolean isPathEmpty) {
		this.noPath = isPathEmpty;
	}
	
	public Boolean isNoPathType(){
		return this.noPath;
	}

	public HashMap<Integer,Path> getAllPaths(){
		return this.allPath;
	}

	public int getRows(){
		return this.row;
	}

	public int getCol(){
		return this.col;
	}


	/**
	 * factory set start and end for no path case
	 * @param x
	 * @param y
	 * @return
	 */
	public Cell getCell(int x, int y){
		return grid[x][y];
	}

	public void setStart(Cell cell){
		this.start = cell;
	}

	public void setEnd(Cell cell){
		this.end = cell;
	}


	public Cell getStart(){
		return this.start;
	}
	
	public Cell getEnd(int pathID) {
		if (this.noPath){
			return this.end;
		}
		else
			return this.getPath(pathID).getPathEndPoint();
	}
	/*
	public Cell getStartPoint(){
		System.out.println("start point:");
		System.out.println(this.start);
		return this.start;
	}
	 */

	public Path getPath(int id){
		//System.out.println("Does the allPath exist?");
		//System.out.println(allPath);
		return this.allPath.get(id);

	}

	/*
	public Cell getPathEndPoint(){
		if(this.noPath){
			Cell current = this.start;
			while(current.getNext() != null){
				current = current.getNext();
			}

			this.end = current;
			return current;

		}
		else{
			return this.end;
		}
	}
	 */

	public Cell[][] getGrid(){
		return this.grid;
	}

	public void placeTower(Tower t,  int coordx, int coordy){
		grid[coordx][coordy].addTower(t);
	}

	public void removeTower(int xcoord, int ycoord){
		grid[xcoord][ycoord].removeTower();
	}

	/*
	public void updateGrid(){
		for(int i = 0; i < grid.length;i++){
			for(int j = 0; j < grid[0].length; j++){
				//grid[i][j].manageCollision();
			}
		}


	}

	 */
}
