import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Display extends Application implements EventHandler<MouseEvent> {
	private static Data data = new Data();
	private int[] tempArray = data.temperatureData();
	private String[] cityArray = data.getCities();
	private static final int WINDOW_WIDTH = 800;
	private static final int WINDOW_HEIGHT = 500;
	private static final int DATA_ELEMENTS_LIMIT = data.temperatureData().length;
	private static final int DATA_VALUE_LIMIT = WINDOW_HEIGHT/2;
	private static final int SPACE_BETWEEN_RECT = 2;
	private static final int RECT_WIDTH = (WINDOW_WIDTH - ((DATA_ELEMENTS_LIMIT-1)*SPACE_BETWEEN_RECT))/DATA_ELEMENTS_LIMIT;
	private static final int MENU_HEIGHT = 25;
	private static final int MENU_WIDTH = 100;
	private ArrayList<Rectangle> rectArray = new ArrayList<Rectangle>();
	private Timer timer;
	//private Group root;
	
	private Rectangle rect1;
	private Label info = new Label();
	private Label lowest = new Label();
	//private Label highest = new Label();
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	
	private class Timer extends AnimationTimer {

		public void handle(long arg0) { //the timer's handle method
			
			//int i = 0;
			for (int i = 0; i < rectArray.size(); i++) { //for loop didnt work so for-each loop with a counter
				
				Rectangle elem = rectArray.get(i);
				rect1 = elem;
				System.out.println(tempArray[i]);
				
				info.setStyle("-fx-fill: #000000");
				info.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
				lowest.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 13));
				
				//info.setVisible(false);
				int j = i;
				rect1.setOnMouseEntered((EventHandler<MouseEvent>) new EventHandler<MouseEvent>() {
					public void handle(MouseEvent mouseEvent) {
						
						lowest.setText(cityArray[j] + " " + tempArray[j] + "F");
						lowest.setLayoutX(3);
						lowest.setLayoutY(3);
						info.setLayoutX(elem.getX());
						info.setLayoutY(elem.getY() - 13);
						elem.setStyle("-fx-fill: #bbbbbb");
						info.setVisible(true);
						lowest.setVisible(true);
					}
				});
				
				rect1.setOnMouseExited((EventHandler<MouseEvent>) new EventHandler<MouseEvent>() {
					public void handle(MouseEvent mouseEvent) {
						info.setVisible(false);
						lowest.setVisible(false);
						elem.setStyle("-fx-fill: #32b10a");
						
					}
				});
				//i++;
			}
			
		}
		
	}
	
	@Override
	public void start(Stage stage) throws Exception {
//		lowest.setVisible(false);
//		highest.setVisible(false);
		stage.setTitle("EnviroSort");
		stage.setResizable(false);
		
		
		Group root = new Group();
		Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		stage.setScene(scene);
		stage.show();
		root.getChildren().addAll(info, lowest);
	root.setStyle("-fx-background-color: #c8f0f7");
		
		for (int elem: tempArray) {
			Rectangle rect = new Rectangle(RECT_WIDTH, elem*3);
			rect.setStyle("-fx-fill: #32b10a");
			rectArray.add(rect);
		} 
		
		for (int elem: tempArray) System.out.print(elem + " ");
		
		int x = SPACE_BETWEEN_RECT/2;
		int y = WINDOW_HEIGHT - 10;
		for (Rectangle elem: rectArray) {
			elem.setX(x);
			x += SPACE_BETWEEN_RECT + RECT_WIDTH;
			elem.setY(y - elem.getHeight());
			root.getChildren().addAll(elem);
		}
		
		timer = new Timer();
		timer.start();
		
		Button sort = new Button("Sort!");
		sort.setPrefWidth(MENU_WIDTH); 
		sort.setPrefHeight(MENU_HEIGHT);
		sort.setTranslateX(WINDOW_WIDTH/2 - MENU_WIDTH/2);
		sort.setTranslateY(MENU_HEIGHT);
		sort.setStyle("-fx-fill: #aaaaaa");
		root.getChildren().add(sort);

		
		//what happens if the sort button is clicked
		sort.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
		    @Override 
		    public void handle(ActionEvent e) {
		    	sortButtonClicked();
		    	
		    	int x = SPACE_BETWEEN_RECT/2;
				int y = WINDOW_HEIGHT - 10;
				for (Rectangle elem: rectArray) {
					elem.setX(x);
					x += SPACE_BETWEEN_RECT + RECT_WIDTH;
					elem.setY(y - elem.getHeight());
					root.getChildren().addAll(elem);
				}
				//lowest.setText(cityArray[i] + tempArray[i] + "F");
		    }
		});
		

		stage.setScene(scene);
		stage.show();
	
	}
	
	public void sortButtonClicked() {
//    	ArrayList<Integer> ints = masterCopyArray; 
//		int n = tempArray.size();
//		for (int i = 0; i < n-1; i++) {
//	            // Find the minimum element in unsorted array
//			int min_idx = i;
//	        for (int j = i+1; j < n; j++)
//	        	if (ints.get(j) < ints.get(min_idx))
//	        		min_idx = j;
//	  
//	            // Swap the found minimum element with the first
//	            // element
//	        int temp = ints.get(min_idx);
//	        ints.set(min_idx, ints.get(i));
//	        ints.set(i, temp);
//	        
//	        String temp2 = cityArray[min_idx];
//	        cityArray[min_idx] = cityArray[i];
//	        cityArray[i] = temp2;
//	        //"sort" the city array accordingly
//	    }
		
		
		int n = tempArray.length;
		for (int i = 0; i < n-1; i++) {
	            // Find the minimum element in unsorted array
			int min_idx = i;
	        for (int j = i+1; j < n; j++)
	        	if (tempArray[j] < tempArray[min_idx])
	        		min_idx = j;
	  
	            // Swap the found minimum element with the first
	            // element
	        int temp = tempArray[min_idx];
	        tempArray[min_idx] = tempArray[i];
	        tempArray[i] = temp;
	        
	        String temp2 = cityArray[min_idx];
	        cityArray[min_idx] = cityArray[i];
	        cityArray[i] = temp2;
	        //"sort" the city array accordingly
	    }
		
        	for (Rectangle elem: rectArray) elem.setVisible(false);
        	for (int i = 0; i < tempArray.length; i++) {
    			Rectangle rect = new Rectangle(RECT_WIDTH, tempArray[i]*3);
    			rect.setStyle("-fx-fill: #32b10a");
    			rectArray.set(i, rect);
    		}
        	
        	//lowest.setStyle("-fx-fill: #000000");
			//lowest.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
//			highest.setStyle("-fx-fill: #000000");
//			highest.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
//			highest.setLayoutY(highest.getHeight()+3);
        	//lowest.setText("Lowest avg. temperature: " + cityArray[0] +  " " + tempArray[0] + "F");
        	//highest.setText("Highest avg. temperature: " + cityArray[cityArray.length-1] + " " + tempArray[tempArray.length-1] + "F");
	
        	System.out.println(cityArray[10] + tempArray[10]);
        	System.out.println(cityArray[0] + tempArray[0]);
        	
        	for (int elem: tempArray) System.out.print(elem + " ");
        	
        	//--------BIGMOMENT-----//
        	//masterCopyArray = ints;
	}

	@Override
	public void handle(MouseEvent mouseEvent) {
		
		
	}
	
	
	/*
	public void randomizeButtonClicked() {
		
		for (int i = 0; i < DATA_ELEMENTS_LIMIT; i++) {
			rectArray.get(i).setVisible(false);
			masterCopyArray.set(i, (int)(Math.random()*(DATA_VALUE_LIMIT)+1)); 
			rectArray.set(i, new Rectangle(RECT_WIDTH, masterCopyArray.get(i)));
			rectArray.get(i).setVisible(true);
			int x = SPACE_BETWEEN_RECT/2;
			int y = WINDOW_HEIGHT - 10;
			for (Rectangle elem: rectArray) {
				elem.setX(x);
				elem.setStyle("-fx-fill: #32b10a");
				x += SPACE_BETWEEN_RECT + RECT_WIDTH;
				elem.setY(y - elem.getHeight());
				//root.getChildren().addAll(elem);
			}
			//reassigning random numbers in master copy array
		}
		
	}
	*/

}






