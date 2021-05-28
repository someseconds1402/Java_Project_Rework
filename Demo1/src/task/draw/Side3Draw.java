package task.draw;

import java.util.ArrayList;

import Oxyz.room.Room;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Side3Draw extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Room room = new Room("D:\\Don't Click Here\\2020-2\\OOP\\Project\\Demo1\\src\\input\\export.txt", 1);
			final int zoom = 500;
			final int N = room.getN();
			
			Group group = new Group();
			
			double primaryY = room.getWidthRoom() / N;
			double primaryZ = room.getHeightRoom() / N;
			
			Rectangle rect = new Rectangle(0, 0, room.getWidthRoom() *zoom, room.getHeightRoom() *zoom);
			rect.setFill(Color.YELLOW);
			group.getChildren().add(rect);
			
			ArrayList<Double> arr = room.couldnotSeePoint2(2, 1);
			for(int i = 0; i < arr.size(); i += 2) {
				Rectangle rectangle = new Rectangle(arr.get(i) *zoom, arr.get(i+1) *zoom, primaryY *zoom, primaryZ *zoom);
				group.getChildren().add(rectangle);
			}

			AnchorPane flatView = new AnchorPane();
			flatView.getChildren().add(group);
						
			Scene scene = new Scene(flatView);
			primaryStage.setTitle("FLAT DRAW");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}