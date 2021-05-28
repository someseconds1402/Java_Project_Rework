package Oxyz.room;

import java.awt.Dimension;
import java.util.ArrayList;
import java.io.File;  
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import Oxyz.plane_line_vector.Line;
import Oxyz.plane_line_vector.Point;
import Oxyz.shape.Rectangular;
import Oxyz.camera.Camera;

public class Room {
	private ArrayList<Rectangular> rectList = new ArrayList<Rectangular>();
	private ArrayList<Camera> cameraList = new ArrayList<Camera>();
	// private Rectangular room;

	private double widthRoom;
	private double lengthRoom;
	private double heightRoom;
	
	private final int N = 1000;

	// Constructor

	public Room(double widthRoom, double lengthRoom, double heightRoom) {
		this.widthRoom = widthRoom;
		this.lengthRoom = lengthRoom;
		this.heightRoom = heightRoom;
	}

	public Room(Point pWidth, Point pLength, Point pHeight) {
		widthRoom = pWidth.getX();
		lengthRoom = pLength.getY();
		heightRoom = pHeight.getZ();
	}
	
	
	// Read file input.txt
	public Room(String fileName) {

		try {
			File file = new File(fileName);
			Scanner scanner = new Scanner(file);

			// Create room
			String para[] = scanner.nextLine().replace("(", "").replace(")", "").replace(",", " ").replace("  ", " ").split(" ");
			int size = para.length;
			for(int i = 0; i < size; i += 3) {
				widthRoom = Double.parseDouble(para[i]);
				if(widthRoom > 0)
					break;
			}
			for(int i = 1; i < size; i += 3) {
				lengthRoom = Double.parseDouble(para[i]);
				if(lengthRoom > 0)
					break;
			}
			for(int i = 2; i < size; i += 3) {
				heightRoom = Double.parseDouble(para[i]);
				if(heightRoom > 0)
					break;
			}
			
			// Add object
			int totalObject = Integer.parseInt(scanner.nextLine());
			readObject(scanner, totalObject);
			
			// Add object
			int totalCamera = Integer.parseInt(scanner.nextLine());
			readCamera(scanner, totalCamera);
			
			scanner.close();
			
		} catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
	}
	
	//Read file
	
	private void readObject(Scanner scanner, int total){
		
		for(int i = 0; i < total; i++) {
			String para[] = scanner.nextLine().replace("(", "").replace(")", "").replace(",", " ").replace("  ", " ").split(" ");
			Point A1 = new Point(Double.parseDouble(para[0]), Double.parseDouble(para[1]), Double.parseDouble(para[2]));
			Point B1 = new Point(Double.parseDouble(para[3]), Double.parseDouble(para[4]), Double.parseDouble(para[5]));
			Point C1 = new Point(Double.parseDouble(para[6]), Double.parseDouble(para[7]), Double.parseDouble(para[8]));
			addObject(new Rectangular(A1, B1, C1, Double.parseDouble(para[14])));
		}
	}
	
	private void readCamera(Scanner scanner, int total){
		
		for(int i = 0; i < total; i++) {
			String para[] = scanner.nextLine().replace("(", "").replace(")", "").replace(",", " ").replace("  ", " ").split(" ");
			Point P = new Point(Double.parseDouble(para[0]), Double.parseDouble(para[1]), Double.parseDouble(para[2]));
			addCameara(P, Double.parseDouble(para[3]), Double.parseDouble(para[4]));
		}		
	}
	
	
	// Read file export.txt
	public Room(String fileName, int n) {
		try {
			File file = new File(fileName);
			Scanner scanner = new Scanner(file);

			// Create room
			String para[] = scanner.nextLine().split(" ");
			
			widthRoom = Double.parseDouble(para[0]);
			lengthRoom = Double.parseDouble(para[1]);
			heightRoom = Double.parseDouble(para[2]);
//			System.out.println(para[0]);
//			System.out.println(2324);
//			System.out.println(scanner.nextLine());
			// Add object
			int totalObject = Integer.parseInt(scanner.nextLine());
			for(int i = 0; i < totalObject; i++) {
				
				String para1[] = scanner.nextLine().split(" ");
				Point A1 = new Point(Double.parseDouble(para1[0]), Double.parseDouble(para1[1]), Double.parseDouble(para1[2]));
				Point B1 = new Point(Double.parseDouble(para1[3]), Double.parseDouble(para1[4]), Double.parseDouble(para1[5]));
				Point C1 = new Point(Double.parseDouble(para1[6]), Double.parseDouble(para1[7]), Double.parseDouble(para1[8]));
				rectList.add(new Rectangular(A1, B1, C1, Double.parseDouble(para1[9])));
			}
			
			// Add object
			int totalCamera = Integer.parseInt(scanner.nextLine());
			for(int i = 0; i < totalCamera; i++) {
				String para2[] = scanner.nextLine().split(" ");
				Point P = new Point(Double.parseDouble(para2[0]), Double.parseDouble(para2[1]), Double.parseDouble(para2[2]));
				cameraList.add(new Camera(this, P, Double.parseDouble(para2[3]), Double.parseDouble(para2[4])));
			}
			
			scanner.close();
			
		} catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
	}
	
	// write file export.txt
	
	public boolean exportFile(String filename) {
		try {
			FileWriter file = new FileWriter(filename);
			file.write(widthRoom + " " + lengthRoom + " " + heightRoom + "\n");
			
			file.write(rectList.size() + "\n");
			for(Rectangular rect : rectList) {
				Point A1 = rect.getA1();
				Point B1 = rect.getB1();
				Point C1 = rect.getC1();
				file.write(A1.getX() + " " + A1.getY() + " " + A1.getZ() + " ");
				file.write(B1.getX() + " " + B1.getY() + " " + B1.getZ() + " ");
				file.write(C1.getX() + " " + C1.getY() + " " + C1.getZ() + " ");
				file.write(rect.getH() + "\n");
			}
			
			file.write(cameraList.size() + "\n");
			for(Camera camera : cameraList) {
				Point P = camera.getPosition();
				file.write(P.getX() + " " + P.getY() + " " + P.getZ() + " ");
				file.write(camera.getAngle1() + " " + camera.getAngle2() + "\n");
			}
			
			file.close();
			return true;
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	

	// Getter & Setter

	public ArrayList<Rectangular> getRectList() {
		return rectList;
	}

	public void setRectList(ArrayList<Rectangular> rectList) {
		this.rectList = rectList;
	}

	public ArrayList<Camera> getCameraList() {
		return cameraList;
	}

	public void setCameraList(ArrayList<Camera> cameraList) {
		this.cameraList = cameraList;
	}

	public double getWidthRoom() {
		return widthRoom;
	}

	public void setWidthRoom(double widthRoom) {
		this.widthRoom = widthRoom;
	}

	public double getLengthRoom() {
		return lengthRoom;
	}

	public void setLengthRoom(double lengthRoom) {
		this.lengthRoom = lengthRoom;
	}

	public double getHeightRoom() {
		return heightRoom;
	}

	public void setHeightRoom(double heightRoom) {
		this.heightRoom = heightRoom;
	}
	
	public int getN() {
		return N;
	}

	// Other Method

	/**********************************************************************/

	/**
	 * Them vat vao trong phong, kiem tra vi tri dat vat co hop le hay ko. xet
	 * truong hop vat nam tren san va nam tren vat khac
	 */
	public boolean addObject(Point A1, Point B1, Point C1, double height) {
		// boolean check = Vector.isPerpendicular(A1, B1, C1);
		// if(!check) {
		// return false;
		// } else {
		Rectangular r = new Rectangular(A1, B1, C1, height);
		return addObject(r);
		// }
	}

	/**
	 * Them vat vao trong phong, kiem tra vi tri dat vat co hop le hay ko. xet
	 * truong hop vat nam tren san va nam tren vat khac
	 */
	public boolean addObject(Rectangular r) {
		if (inRoom(r)) { // vi tri dat vat hop le (nam trong phong)
			double bottom = r.getA1().getZ();
			if (bottom > 0) { // Neu vat khong nam tren san

				double Gx = (r.getA1().getX() + r.getC1().getX()) / 2;
				double Gy = (r.getA1().getY() + r.getC1().getY()) / 2;
				double Gz = (r.getA1().getZ() + r.getC1().getZ()) / 2;

				Point G = new Point(Gx, Gy, Gz); // G la trong tam mat duoi cua vat the r

				for (Rectangular rect : rectList) {
					if (bottom < rect.getH()) {
						if (rect.isValidPosition(r) || r.isValidPosition(rect))
							return false;
					} else if (bottom == rect.getH()) {
						if (rect.isInRect(G)) {
							rectList.add(r);
							return true;
						}
					}
				}

				return false;
			} else {
				for (Rectangular rect : rectList) {
					if (rect.centroidIsInRect(r)) // Trong tam vat the r nam trong vat the rect
						return false;
					if (r.centroidIsInRect(rect)) // Trong tam vat the rect nam trong vat the r
						return false;
					if (rect.isValidPosition(r)) // Dinh cua r nam trong vat the rect
						return false;
					if (r.isValidPosition(rect)) // Dinh cua rect nam trong vat the r
						return false;
				}
				rectList.add(r);
				return true;
			}
		} else { // Vi tri dat vat nam ngoai phong
			return false;
		}
	}

	/** Xac dinh 1 vat co nam trong phong hay ko */
	public boolean inRoom(Rectangular r) {
		if (!inRoom(r.getA1())) {
			return false;
		}
		if (!inRoom(r.getB1())) {
			return false;
		}
		if (!inRoom(r.getC1())) {
			return false;
		}
		if (!inRoom(r.getD1())) {
			return false;
		}
		if (r.getA2().getZ() > heightRoom) {
			return false;
		}
		return true;
	}

	/** Xac dinh 1 diem co nam trong phong hay ko */
	public boolean inRoom(Point p) {
		return !(p.getX() > widthRoom || p.getX() < 0 || p.getY() > lengthRoom || p.getY() < 0);
	}

	/**********************************************************************/

	/** Xac dinh diem dat camera co hop le hay khong */
	public boolean isValidPosition(Point p) {
		if (p.getZ() == heightRoom) {
			return true;
		} else if (p.getY() == lengthRoom || p.getY() == 0) {
			return true;
		} else if (p.getX() == widthRoom || p.getX() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/** Them camera vao phong, xac dinh vi tri dat co hop le hay ko */
	public boolean addCameara(Point position, double angle1, double angle2) {
		if (isValidPosition(position)) {
			cameraList.add(new Camera(this, position, angle1, angle2));
			return true;
		} else {
			return false;
		}
	}

	/**********************************************************************/

	/**
	 * kiem tra vi tri cua 1 diem, * tra ve 0 neu ko nam trong tam nhin cua camera
	 * va ko nam trong vat the. tra ve -1 neu nam ngoai phong. tra ve 1 neu nam
	 * trong vat. tra ve 2 neu nam trong tam nhin cua camera. tra ve 3 neu nam trong
	 * tam nhin cua camera nhung lai bi chan boi vat the
	 */

	public Dimension checkPoint(Point p) {
		Dimension result = new Dimension(0, 0);

		if (!inRoom(p))
			return new Dimension(-1, -1);

		if (p.getZ() > heightRoom)
			return new Dimension(-1, -1);

		for (Rectangular object : rectList) {
			if (object.contain(p))
				return new Dimension(1, rectList.indexOf(object));
		}

		// int countInVision = 0; // Dem so camera co the thay duoc diem p (chua xet
		// truong hop co vat the chan)

		for (Camera camera : cameraList) {
			if (camera.couldSee(p)) { // p nam trong tam nhin cua camera

				// countInVision++;
				Point pos = camera.getPosition();
				Line l = new Line(pos, p); // l la duong thang noi giua camera va diem p
				result = new Dimension(2, cameraList.indexOf(camera));

				for (Rectangular object : rectList) {

					Point interPoint = object.intersection(l); // giao diem giua object va l
					if (interPoint != null) { // Neu co giao diem thi xet truong hop giao diem nam giua camera va diem p
						if (interPoint.isBetween2Points(pos, p)) {
							/*
							 * Neu giao diem nam giua camera va p -> p ko trong tam nhin cua camera -> tra
							 * ve result, break
							 */
							result = new Dimension(3, 0);
							break;
						}
					}
				}
				if (result.getWidth() == 2) {
					return result;
				}

			}
		}

		// if(countInVision > 0)
		// return new Dimension(3, 0);

		return result;
	}

	public int checkPoint2(Point p) { // Ham kiem tra 1 diem thuoc mat phang san nha hoac tran nha thuoc khong gian nao
		int result = 0;
		// result = 1 => p nam trong tam nhin cua camera
		// result = 2 => p ko nam trong tam nhin cua camera

		for (Camera camera : cameraList) {
			if (camera.couldSee(p)) { // p nam trong tam nhin cua camera

				Point pos = camera.getPosition();
				Line l = new Line(pos, p); // l la duong thang noi giua camera va diem p
				result = 1;

				for (Rectangular object : rectList) {

					Point interPoint = object.intersection(l); // giao diem giua object va l
					if (interPoint != null) { // Neu co giao diem thi xet truong hop giao diem nam giua camera va diem p
//						 if(interPoint.isBetween2Points(pos, p)) {
						/*
						 * Neu giao diem nam giua camera va p -> p ko trong tam nhin cua camera -> tra
						 * ve result, break
						 */
						result = 2;
						break;
//						 }
					}
				}
				if (result == 1) {
					return result;
				}

			} else {
				result = 2;
			}
		}

		return result;
	}
	
	public int checkPoint3(Point p) { // Ham kiem tra 1 diem bat ki thuoc vung khong gian nao
		int result = 0;
		// result = 1 => p nam trong tam nhin cua camera
		// result = 2 => p ko nam trong tam nhin cua camera

		for (Camera camera : cameraList) {
			if (camera.couldSee(p)) { // p nam trong tam nhin cua camera

				Point pos = camera.getPosition();
				Line l = new Line(pos, p); // l la duong thang noi giua camera va diem p
				result = 1;

				for (Rectangular object : rectList) {

					Point interPoint = object.intersection(l); // giao diem giua object va l
					if (interPoint != null) { // Neu co giao diem thi xet truong hop giao diem nam giua camera va diem p
						 if(interPoint.isBetween2Points(pos, p)) {
						/*
						 * Neu giao diem nam giua camera va p -> p ko trong tam nhin cua camera -> tra
						 * ve result, break
						 */
						result = 2;
						break;
						 }
					}
				}
				if (result == 1) {
					return result;
				}

			} else {
				result = 2;
			}
		}

		return result;
	}

	public ArrayList<Double> couldnotSeePoint() {
		ArrayList<Double> arr = new ArrayList<>();

		double primaryX = widthRoom / N;
		double primaryY = lengthRoom / N;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				double x = primaryX * i;
				double y = primaryY * j;
				Point p = new Point(x, y, 0);
				if (checkPoint2(p) == 2) {
					arr.add(x);
					arr.add(y);
				}
			}
		}
		return arr;
	}
	
	
	/**
	 * Method couldnotSeePoint2 : ve hinh chieu tren cac mat tuong
	 * @param plane : co 2 gia tri 1 (x = 0 || x = widthRoom) va 2 (y = 0 || y = lengthRoom)
	 * @param value : co 2 gia tri 1 (toa do = 0) va 2 (toa do = roomSize)
	 */
	 //            |  value = 1  | value = 2
	 // ___________|_____________|________________
	 // plane = 1  |    x = 0    | x = widthRoom    
	 // plane = 2  |    y = 0    | y = lengthRoom    
	 
	public ArrayList<Double> couldnotSeePoint2(int plane, int value) {
		ArrayList<Double> arr = new ArrayList<>();
		
		if(plane == 1) {
			double primaryY = lengthRoom / N;
			double primaryZ = heightRoom / N;
			
			if(value == 1) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						double y = primaryY * i;
						double z = primaryZ * j;
						Point p = new Point(0, y, z);
						if (checkPoint2(p) == 2) {
							arr.add(y);
							arr.add(z);
						}
					}
				}
				
			} else if(value == 2) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						double y = primaryY * i;
						double z = primaryZ * j;
						Point p = new Point(widthRoom, y, z);
						if (checkPoint2(p) == 2) {
							arr.add(y);
							arr.add(z);
						}
					}
				}
				
			}
			
		} else if(plane == 2) {
			double primaryX = widthRoom / N;
			double primaryZ = heightRoom / N;
			

			if(value == 1) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						double x = primaryX * i;
						double z = primaryZ * j;
						Point p = new Point(x, 0, z);
						if (checkPoint2(p) == 2) {
							arr.add(x);
							arr.add(z);
						}
					}
				}
				
			} else if(value == 2) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						double x = primaryX * i;
						double z = primaryZ * j;
						Point p = new Point(x, lengthRoom, z);
						if (checkPoint2(p) == 2) {
							arr.add(x);
							arr.add(z);
						}
					}
				}
				
			}
		}
		
		return arr;
	}

	
	/**
	 * Ham tra ve so diem nhin thay trong can phong
	 */
	
	public double couldSeeVolume() {
		int count = 0;
		
		double primaryX = widthRoom / N;
		double primaryY = lengthRoom / N;
		double primaryZ = heightRoom / N;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				for(int k = 0; k < N; k++) {
					Point point = new Point(primaryX * i, primaryY * j, primaryZ * k);
					if(checkPoint3(point) == 1) {
						count++;
					}
				}
			}
		}
		return count * primaryX * primaryY * primaryZ;
	}
	
}
