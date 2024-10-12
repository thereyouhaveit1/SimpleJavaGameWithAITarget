package application;

import java.util.Random;

import application.Coordinate.Value;

public class PastureMap {
	
	
	 
	private Cows [] cows;
	private int row;
	private int col;
	private Coordinate[][] coordinates;
	private Coordinate player;
	private Random r;
	private boolean [][] isSoiled;
	private STATE state;
	
	
	enum STATE{
		WON,
		LOST,
		RUNNING
		
	}
	
	public PastureMap (int row, int col, int numCows) {
		
		
		this.row = row;
		this.col = col;
		this.r = new Random();
		this.cows = new Cows[numCows];
		
		this.coordinates = new Coordinate [row][col];
		 this.isSoiled = new boolean[row][col];
		 
		 
		
		this.setUpMap(row,col);
		 
		
		 
	}
	 

	public Coordinate getCoordinate(int row, int col) {
	return this.coordinates[row][col];
	 
	
	
	}
	public void  setUpMap (int row, int col) {
		
		
		this.state = STATE.RUNNING;
		this.isSoiled = new boolean[this.row][this.col];
		
		 for (int i = 0 ; i < row ; i++) {
			 for (int j = 0 ; j < col ; j++) {
	
				 if (i==0 && j ==1) {
					 Coordinate start = new Coordinate (i,j, Value.START);
					 this.coordinates[i][j] = start;
					 
					 
					 continue;
					 
				 }
				 if (i==1 && j ==1) {
					 Coordinate player = new Coordinate (i,j, Value.PLAYER);
					 this.coordinates[i][j] = player;
					 this.player = player;
					 continue;
					 
				 }
				 if (i==row-1 && j ==col-2) {
					 Coordinate exit = new Coordinate (i,j, Value.EXIT);
					 this.coordinates[i][j] = exit;
					 continue;
				 }
	
				 if (i==0 || i == row-1 || j ==0 || j == col-1) {
					 Coordinate coordinate = new Coordinate (i,j, Value.FENCE);
					 this.coordinates[i][j] = coordinate;
					 continue;
				 }
Random r = new Random();

   
if (r.nextDouble() <= 0.015) {
	 Coordinate coordinate = new Coordinate (i,j, Value.FENCE);
	 
	 this.coordinates[i][j] = coordinate;
	  
}
	 else {
		 Coordinate coordinate = new Coordinate (i,j, Value.EMPTY);
		 
		 this.coordinates[i][j] = coordinate;
		  
	 }}}
	 
	
	for (int i = 0 ; i <this.cows.length; i++) {
		 
		  
		 
		 int rCol = r.nextInt(this.col -6) + 5;
		 int rRow = r.nextInt(this.row -6) + 5;
 		 
	while (this.getCoordinate(rRow, rCol).getValue()== Value.COW) {
		  rCol = r.nextInt(this.col -6) + 5;
		   rRow = r.nextInt(this.row -6) + 5;
	}
	 Cows cow = new Cows (rRow, rCol , Value.COW);
		 this.cows[i] = cow;
		 this.coordinates[rRow][rCol] = cow;
		 
	}
	
	}
	
	
	
	
public void update (int row, int col) {
	if (this.getCoordinate(row, col).getValue()== Value.FENCE) {
		this.getCoordinate(row, col).setValue(Value.EMPTY);
		 
	}
	else {
		this.getCoordinate(row, col).setValue(Value.FENCE);
	}
}
public Coordinate getPlayer(){
	return this.player;
	
}


public Cows [] getCows() {
	return this.cows;
	
}
public boolean playerCanMove(int row, int col) {
	return this.getCoordinate(row, col).getValue()== Value.EMPTY;
}
public void playerUpdate (int oldRow, int oldCol, int newRow, int newCol) {
	
	
	//if (this.getCoordinate (newRow, newCol).getValue() != Value.FENCE){
		
	
	this.coordinates[oldRow][ oldCol].setValue(Value.EMPTY);
	this.coordinates[newRow][ newCol].setValue(Value.PLAYER);
	this.player.setRow(newRow);
	this.player.setCol(newCol);

}



public void cowUpdate(int index,  int newRow, int newCol) {
	
	Cows cow = this.cows [index];
	
	
	this.getCoordinate(cow.getRow(), cow.getCol()).setValue(Value.EMPTY);
	this.isSoiled[cow.getRow()][cow.getCol()] = true;
	
	cow.setRow(newRow);
	cow.setCol(newCol);
	
	}

public boolean soiled(int row, int col)
{
	return (this.isSoiled [row][col]);
	  
}
public void   setState(STATE state)
{
	this.state = state;
}
public STATE getState() {
	return this.state;
}

public boolean gameWon()
{

return this.getPlayer().getRow() == this.row -2 && this.getPlayer().getCol() == this.col -2;

}
}