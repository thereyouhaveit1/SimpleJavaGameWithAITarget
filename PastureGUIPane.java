package application;
import application.Coordinate.Value;
import application.PastureMap.STATE;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;

public class PastureGUIPane extends BorderPane{
 private int row;
 private int col;
 
 private PastureMap map;
 
 private Label[][]labels;
 
 private Label stateLabel;
 
 
 public PastureGUIPane (int row , int col , PastureMap map) {
	 
	 this.row = row;
	 this.col = col;
	 
	 this.labels = new Label[row][col];
	 
	 this.map = map;
	 
	 this.setUpTop();
	 this.setUpBottom();
	 this.setUpCenter();
	 Player player = new Player();
	 
 }
 
 public void setUpTop() {
	 
	 HBox top = new HBox();
	 top.getStyleClass().add("center");
	 
	 Label topLabel =  new Label("PastureMap");
	 
	 top.getChildren().add(topLabel);
	 this.setTop(top);
 }
 public void setUpBottom() {
	 
	 HBox bottom = new HBox();
	 bottom.getStyleClass().add("center");
	 this.stateLabel = new Label();
	 
	 
	 Button reButton =  new Button("Reset");
	 reButton.setFocusTraversable(false);
	  
	 reButton.setOnMouseClicked(e -> {
		 this.map.setUpMap(row,col);
		 this.setUpCenter();
		 this.stateLabel.setText("");
		 
	 });
	 bottom.getChildren().addAll(reButton,stateLabel);
	 this.setBottom(bottom);
 }
 public void setUpCenter() {
	 
	 GridPane gp = new GridPane();
	 for (int i =0 ; i <this.row; i++) {
		 for (int j =0 ; j <this.col;j++) {
			 
			 Coordinate coordinate = this.map.getCoordinate(i,j);
			 
			 Label label = new Label();
			 
			 label.setPrefSize(30,30);
			 
			 switch (coordinate.getValue()) {
				case FENCE:
			label.getStyleClass().add("fence");
				break;
				case EMPTY:
					label.getStyleClass().add("empty");
				break;
				case START:
					label.getStyleClass().add("tile");
					label.setText("S");
					label.setAlignment( Pos.CENTER);
				break;
				case EXIT:
					label.getStyleClass().add("tile");
					label.setText("E");
					label.setAlignment( Pos.CENTER);

				break;
				case COW:
					label.getStyleClass().add("cow");
					

				break;
				case PLAYER:
					label.getStyleClass().add("player");
					

				break;
				}
			 if(i>0 && i<row-1 && j>0 && j<col-1) {
				 label.setOnMouseClicked(e -> {
					 
					 if (coordinate.getValue() != Value.FENCE && coordinate.getValue() != Value.EMPTY){
						 return;
					 }
					 if (coordinate.getValue() == Value.FENCE){
							label.getStyleClass().clear();
							label.getStyleClass().add("empty");
							 this.map.update(coordinate.getRow(), coordinate.getCol());



					 }else if(coordinate.getValue() == Value.EMPTY) {
                         label.getStyleClass().clear();
                         label.getStyleClass().add("fence");
					
					 
					 this.map.update(coordinate.getRow(), coordinate.getCol());
					 } 
				 });
			 }
			 this.labels[i][j] = label;
			 gp.add(label, j , i);
		 }
	 }
 
 this.setCenter(gp);
 
 
 }

 
	 
	public void playerUpdate (int oldRow, int oldCol, int newRow, int newCol) {
		
	Label oldLabel = this.labels[oldRow][oldCol];
	 oldLabel.getStyleClass().clear();
	 oldLabel.getStyleClass().add("empty");
	 
	 Label newLabel = this.labels[newRow][newCol];
	 newLabel.getStyleClass().clear();
	 newLabel.getStyleClass().add("player");
}
	
	public void cowUpdate (  int oldRow, int oldCol, int newRow, int newCol) {
		
		Label oldLabel = this.labels[oldRow][oldCol];
		 oldLabel.getStyleClass().clear();
		 oldLabel.getStyleClass().add("soiled");
		 
		 Label newLabel = this.labels[newRow][newCol];
		 newLabel.getStyleClass().clear();
		 newLabel.getStyleClass().add("cow");
	 
}
	public void updateStateLabel (STATE state) {
		
		if(state == STATE.LOST) {
			this.stateLabel.setText("Game Lost");
			
		}
		else if (state == STATE.WON) {
			this.stateLabel.setText("Game Won");
		}
		}
	}