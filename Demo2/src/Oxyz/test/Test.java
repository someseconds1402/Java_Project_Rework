package Oxyz.test;

import java.util.ArrayList;

import Oxyz.camera.Camera;
import Oxyz.plane_line_vector.Point;
import Oxyz.room.Room;
import Oxyz.shape.Rectangular;

public class Test {

	public static void main(String[] args) {
		
		Room room = new Room(100, 100, 100);
		Camera camera = new Camera(room, new Point(50, 50, 100), 60, 60);
		
		Point B = new Point(0, 40, 0);
		Point A = new Point(100, 40, 0);
		Point C = new Point(0, 60, 0);
		
		Rectangular rectangular = new Rectangular(A, B, C, 50);
		
		ArrayList<Point> arrayList = new ArrayList<>();
		ArrayList<Point> something = rectangular.intersection(camera);
		ArrayList<Point> projection = rectangular.projection(camera);
		for(Point point : projection) {
			System.out.println(point.getProperties());;
		}
		
	}

}
