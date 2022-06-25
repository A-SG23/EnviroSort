import java.util.ArrayList;

import javafx.application.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Display extends Application {
	
	private static final int WINDOW_WIDTH = 800;
	private static final int WINDOW_HEIGHT = 500;
	private static final int DATA_LIMIT = 80;
	private static final int SPACE_BETWEEN_RECT = 2;
	private static final int RECT_WIDTH = (WINDOW_WIDTH - (DATA_LIMIT-1)*(SPACE_BETWEEN_RECT))/DATA_LIMIT;
	ArrayList<Integer> array = new ArrayList<Integer>();
	ArrayList<Rectangle> rectArray = new ArrayList<Rectangle>();

	
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
		
		for (int i = 0; i < DATA_LIMIT; i++) {
			array.add((int)(Math.random()*(DATA_LIMIT/2)));
		}
		
		for (int elem: array) {
			Rectangle rect = new Rectangle(RECT_WIDTH, elem);
			rectArray.add(rect);
		}
		
		int x = 0;
		int y = DATA_LIMIT + 20;
		for (Rectangle elem: rectArray) {
			elem.setX(x);
			x += SPACE_BETWEEN_RECT + RECT_WIDTH;
			elem.setY(y - elem.getHeight());
			root.getChildren().addAll(elem);
		}

		stage.setScene(scene);
		stage.show();
		
		//make a sorts object and 
	}

}
