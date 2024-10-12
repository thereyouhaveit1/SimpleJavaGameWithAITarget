package application;


import application.Coordinate.Value;
import application.PastureMap.STATE;
import gui.keyCode;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
	
	public class Main extends Application 
	{   
		 
int row = 30;
int col = 20;
		
		
		 

		@Override
		public void start(Stage primaryStage)  {
			try {
				
			PastureMap map = new PastureMap(row,col, 5);
 			PastureGUIPane gui = new PastureGUIPane(row,col,map);
		 
	        Scene scene = new Scene(gui,col*30,row*30+60);
	         
	        scene.getStylesheets().add(getClass().getResource("application2.css").toExternalForm());
	        primaryStage.setScene(scene);
	        primaryStage.show();
	        
	        
	        scene.setOnKeyPressed(e-> {
	        	
	        	
	        	if(map.getState() != STATE.RUNNING)
	        	return;
	        	
	        	
	        	if (e.getCode() != KeyCode.DOWN && e.getCode() != KeyCode.UP && e.getCode() != KeyCode.LEFT
	        			&& e.getCode() != KeyCode.RIGHT )
	        		return;
	        	
	        	Cows[] cows = map.getCows();
	        	
	        	
	        	for (int i = 0 ; i<cows.length; i++) {
	        		
	        		
	        		Coordinate move = cows [i].generateRandomMove();
	        		
	        		Value value = map.getCoordinate (move.getRow(), move.getCol()).getValue();
	        		
	        		
	        		if (value == Value.EMPTY  )  {
	        		
	        	     gui.cowUpdate(cows[i].getRow(),cows[i].getCol(), move.getRow(), move.getCol());
	        		 map.cowUpdate(i, move.getRow(), move.getCol());
	        	}
	        }
	        	int oldRow = map.getPlayer().getRow();
	        	int oldCol = map.getPlayer().getCol();
	        	int newCol = oldCol;
	        	int newRow = oldRow;
	        	
	        	switch(e.getCode()) {
	        	
	        	 case DOWN:
			         newRow++;

			            break;
			
			        case UP:
			        	newRow--;

			            break;
			
			        case LEFT:
			        	newCol--;

			            break;
			
			        case RIGHT:
			        	newCol++;

			            break;
				default:
					break;
			
	        	
	        	}
	        	
	        	
	        	if (map.playerCanMove(newRow, newCol)) {
	        			map.playerUpdate(oldRow, oldCol, newRow, newCol);
	        			gui.playerUpdate(oldRow, oldCol, newRow, newCol);
	        		}
	      
	        	
	        	if(map.soiled(newRow, newCol)){
	        		map.setState(STATE.LOST);
	        		gui.updateStateLabel(STATE.LOST);
	        	}
	        	if(map.gameWon()) {
	        		map.setState(STATE.WON);
	        		gui.updateStateLabel(STATE.WON);
	        	}
	        });
			
		}
		catch (Exception e) {
			  
            e.printStackTrace();
        }
}
		public static void main(String[] args) {
			launch(args);
		}}
	
