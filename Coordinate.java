package application;

public class Coordinate {
 
	 public  enum Value{
		EMPTY,
		FENCE,
		START,
		COW,
		PLAYER,
		DOWN,
		UP,
		RIGHT,
		LEFT,
		EXIT
	}
	private int col;
	private int row;
	private Value value;

 
	public Coordinate(int row, int col, Value value){
		this.row  = row;
		this.col = col;
		this.value = value;
	}


	public int getCol() {
		return col;
	}


	public void setCol(int col) {
		this.col = col;
	}


	public int getRow() {
		return row;
	}


	public void setRow(int row) {
		this.row = row;
	}


	public Value getValue() {
		return value;
	}


	public void setValue(Value value) {
		this.value = value;
	}


	public Coordinate getMove() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
	