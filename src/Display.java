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
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Display extends Application {
	
	private static final int WINDOW_WIDTH = 800;
	private static final int WINDOW_HEIGHT = 500;
	private static final int DATA_ELEMENTS_LIMIT = 5;
	private static final int DATA_VALUE_LIMIT = WINDOW_HEIGHT/2;
	private static final int SPACE_BETWEEN_RECT = 2;
	private static final int RECT_WIDTH = (WINDOW_WIDTH - ((DATA_ELEMENTS_LIMIT-1)*SPACE_BETWEEN_RECT))/DATA_ELEMENTS_LIMIT;
	private static final int MENU_HEIGHT = 25;
	private static final int MENU_WIDTH = 100;
	private ArrayList<Integer> masterCopyArray = new ArrayList<Integer>(); //won't be changed
	private ArrayList<Rectangle> rectArray = new ArrayList<Rectangle>();
	private Sorts sorter = new Sorts();
	private ComboBox comboBox;
	private long startTime;
	private long endTime;
	//private Group root;
	
//	private class Timer extends AnimationTimer {
//		private static final int FRAMES_PER_SEC = 60;
//		private int frames = FRAMES_PER_SEC*5; //5 seconds?
//		public void handle(long now) {
//			frames--;
//			if (frames <= 0) {
//				sorter.
//			}
//		}
//	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("SortX");
		stage.setResizable(false);
		Group root = new Group();
		Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		stage.setScene(scene);
		stage.show();
		
		for (int i = 0; i < DATA_ELEMENTS_LIMIT; i++) {
			masterCopyArray.add((int)(Math.random()*(DATA_VALUE_LIMIT)+1)); //
		}
		
		for (int elem: masterCopyArray) {
			Rectangle rect = new Rectangle(RECT_WIDTH, elem);
			rect.setStyle("-fx-fill: #32b10a");
			rectArray.add(rect);
		}
		
		int x = SPACE_BETWEEN_RECT/2;
		int y = WINDOW_HEIGHT - 10;
		for (Rectangle elem: rectArray) {
			elem.setX(x);
			x += SPACE_BETWEEN_RECT + RECT_WIDTH;
			elem.setY(y - elem.getHeight());
			root.getChildren().addAll(elem);
		}

		
		ObservableList<String> options = 
				FXCollections.observableArrayList(
			        "Selection Sort",
			        "Bubble Sort",
			        "Gnome Sort"
			    );
		
		comboBox = new ComboBox(options);
		comboBox.setPrefWidth(3*MENU_WIDTH/2); 
		comboBox.setPrefHeight(MENU_HEIGHT);
		comboBox.setPromptText("Type of sort:");
		comboBox.setTranslateX(WINDOW_WIDTH/2 - MENU_WIDTH/2);
		comboBox.setTranslateY(MENU_HEIGHT*2);
		root.getChildren().add(comboBox);
		
		Button sort = new Button("Sort!");
		sort.setPrefWidth(MENU_WIDTH); 
		sort.setPrefHeight(MENU_HEIGHT);
		sort.setTranslateX(WINDOW_WIDTH/2 - MENU_WIDTH/2);
		sort.setTranslateY(MENU_HEIGHT*4);
		root.getChildren().add(sort);
		
		Button randomize = new Button("Randomize!");
		randomize.setPrefWidth(MENU_WIDTH); 
		randomize.setPrefHeight(MENU_HEIGHT);
		randomize.setTranslateX(WINDOW_WIDTH - 3*MENU_WIDTH/2);
		randomize.setTranslateY(MENU_HEIGHT*4);
		root.getChildren().add(randomize);
		
		
		randomize.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
		    @Override 
		    public void handle(ActionEvent e) {
		    	randomizeButtonClicked();
		    	for (Rectangle elem: rectArray) root.getChildren().add(elem);
		    }
		});

		
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
		    }
		});

		stage.setScene(scene);
		stage.show();
		
		
		/*
		 * -------- PENDING STEPS ---------
		 * - fix selection sort, add insertion sort (AVANI)
		 * 
		 * - maybe just get rid of gnome sort cuz it doesnt seem to be working
		 * 
		 * - add a third button to re-randomize a new set of data and re-display that (ADITI)
		 *   so the user doesnt need to keep hitting run again
		 *   
		 * - display time somehow - problem with access across the Display and Sorts class
		 * 
		 */
		
	}
	
	
	public void sortButtonClicked() {

    	String menuChoice = (String)comboBox.getValue();
    	ArrayList<Integer> newArr = masterCopyArray;
    	//if (menuChoice != null) {
    		System.out.println("1");
    		
    		if (menuChoice == null) {
    			System.out.println("choice is null");
    		}
    		if (menuChoice.equals("Selection Sort")) {
        		newArr = sorter.selectionSort(masterCopyArray);
        	} else if (menuChoice.equals("Gnome Sort")) {
        		newArr = sorter.gnomeSort(masterCopyArray);
        	} else if (menuChoice.equals("Bubble Sort")) {
        		newArr = sorter.bubbleSort(masterCopyArray);
        	}
        	for (Rectangle elem: rectArray) elem.setVisible(false);
        	for (int i = 0; i < newArr.size(); i++) {
    			Rectangle rect = new Rectangle(RECT_WIDTH, newArr.get(i));
    			rect.setStyle("-fx-fill: #32b10a");
    			rectArray.set(i, rect);
    		}
//    	} else {
//    		System.out.println("2");
//    	}
	}
	
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

}






