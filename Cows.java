package application;

import java.util.Random;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import tictactoeFX.Game.Turn;

public class Cows extends Coordinate {
	
	
	private Random r;
	
	public Cows (int row, int col , Value value) {
		
		super(row,col,value);
		this.r= new Random();
		
	}
	
	public Coordinate generateRandomMove() {
		
		
		 Coordinate coordinate = null;
		int value = r.nextInt(4);
		
		switch(value) {
    	
   	 case 0:
	        coordinate = new Coordinate(this.getRow()-1, this.getCol(), Value.EMPTY);

	            break;
	
	        case 1:
	        	coordinate = new Coordinate(this.getRow()+1, this.getCol(), Value.EMPTY);

	            break;
	
	        case 2:
	        	coordinate = new Coordinate(this.getRow(), this.getCol()+1, Value.EMPTY);
	            break;
	
	        case 3:
	        	coordinate = new Coordinate(this.getRow(), this.getCol()-1, Value.EMPTY);

	            break;
		default:
			break;
		}
		
		return coordinate;
		
	}
	 
    public void cowUpdate(int row, int col) {
    	
    	
    	this.setRow(row);
    	this.setCol(col);
    }
}