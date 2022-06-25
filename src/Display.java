import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Display extends Application {
	
	private static final int WINDOW_WIDTH = 800;
	private static final int WINDOW_HEIGHT = 500;
	private static final int DATA_ELEMENTS_LIMIT = 80;
	private static final int DATA_VALUE_LIMIT = WINDOW_HEIGHT/2;
	private static final int SPACE_BETWEEN_RECT = 2;
	private static final int RECT_WIDTH = (WINDOW_WIDTH - (DATA_ELEMENTS_LIMIT-1)*(SPACE_BETWEEN_RECT))/DATA_ELEMENTS_LIMIT;
	private static final int MENU_HEIGHT = 25;
	private static final int MENU_WIDTH = 100;
	private ArrayList<Integer> masterCopyArray = new ArrayList<Integer>(); //won't be changed
	private ArrayList<Rectangle> rectArray = new ArrayList<Rectangle>();
	private Sorts sorter = new Sorts();
	private ComboBox comboBox;
	private long timeInNs;
	
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
			        "Insertion Sort",
			        "Bubble Sort"
			    );
		
		comboBox = new ComboBox(options);
		comboBox.setPrefWidth(3*MENU_WIDTH/2); 
		comboBox.setPrefHeight(MENU_HEIGHT);
		comboBox.setPromptText("Type of sort:");
		comboBox.setTranslateX(WINDOW_WIDTH/2 - MENU_WIDTH/2);
		comboBox.setTranslateY(MENU_HEIGHT*2);
		root.getChildren().add(comboBox);
		
		Button button = new Button("Sort!");
		button.setPrefWidth(MENU_WIDTH); 
		button.setPrefHeight(MENU_HEIGHT);
		button.setTranslateX(WINDOW_WIDTH/2 - MENU_WIDTH/2);
		button.setTranslateY(MENU_HEIGHT*4);
		root.getChildren().add(button);
		
		/*
		 * if the "sort" button is pressed: 
		 * - access whatever option has been chosen for the combobox
		 * - based on that chosen option call the right method on the array
		 * - update what's shown on the screen by making a new Rectangle array and just showing that
		 * - do the thing with the time (display nanoseconds taken at the top left)
		 */

		stage.setScene(scene);
		stage.show();
		
	}

}
