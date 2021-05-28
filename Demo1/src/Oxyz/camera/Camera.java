package Oxyz.camera;

import java.util.ArrayList;

import Oxyz.plane_line_vector.Line;
import Oxyz.plane_line_vector.Plane;
import Oxyz.plane_line_vector.Point;
import Oxyz.plane_line_vector.Vector3D;
import Oxyz.room.Room;

public class Camera {
	private Point position; // vi tri dat camera
	private Vector3D axis; // truc cua camera

	private Plane[] vision = new Plane[4]; // tam nhin cua camera (bao quanh boi 4 hinh tam giac)
	private Plane top; // mat phang vuong goc voi truc cua camera tai dinh.
						// Dung de kiem tra 1 vat co nam ngoai tam xa cua camera hay ko
	private double angle1, angle2;

	// Getter & Setter

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public Vector3D getAxis() {
		return axis;
	}

	public boolean setAxis(Room room) {
		if (position.getZ() == room.getHeightRoom()) {
			axis = new Vector3D(0, 0, 1);
			return true;
		} else if (position.getY() == room.getLengthRoom() || position.getY() == 0) {
			axis = new Vector3D(0, 1, 0);
			return true;
		} else if (position.getX() == room.getWidthRoom() || position.getX() == 0) {
			axis = new Vector3D(1, 0, 0);
			return true;
		} else {
			return false;
		}
	}

	public Plane[] getVision() {
		return vision;
	}

	public void setVision(Plane[] vision) {
		this.vision = vision;
	}

	public Plane getTop() {
		return top;
	}

	public void setTop() {
		top = new Plane(position, axis);
	}

	public double getAngle1() {
		return angle1;
	}

	public void setAngle1(double angle1) {
		this.angle1 = angle1;
	}

	public double getAngle2() {
		return angle2;
	}

	public void setAngle2(double angle2) {
		this.angle2 = angle2;
	}

	// Constructor

	public Camera(Room room, Point position, double angle1, double angle2) {
		this.position = position;
		this.angle1 = angle1;
		this.angle2 = angle2;
		double sinAngel1 = Math.sin(Math.toRadians(angle1 / 2));
		double cosAngel1 = Math.cos(Math.toRadians(angle1 / 2));
		double sinAngel2 = Math.sin(Math.toRadians(angle2 / 2));
		double cosAngel2 = Math.cos(Math.toRadians(angle2 / 2));

		if (setAxis(room)) {
			setTop();

			if (axis.getX() == 1) { // camera dat tren tuong (x = 0)
				vision[0] = new Plane(position, new Vector3D(-cosAngel1 * cosAngel1 + 1, sinAngel1 * cosAngel1, 0));
				vision[1] = new Plane(position, new Vector3D(cosAngel1 * cosAngel1 - 1, sinAngel1 * cosAngel1, 0));
				vision[2] = new Plane(position, new Vector3D(-cosAngel2 * cosAngel2 + 1, 0, sinAngel2 * cosAngel2));
				vision[3] = new Plane(position, new Vector3D(cosAngel2 * cosAngel2 - 1, 0, sinAngel2 * cosAngel2));

			} else if (axis.getY() == 1) { // camera dat tren tuong (y = 0)
				vision[0] = new Plane(position, new Vector3D(0, -cosAngel1 * cosAngel1 + 1, sinAngel1 * cosAngel1));
				vision[1] = new Plane(position, new Vector3D(0, cosAngel1 * cosAngel1 - 1, sinAngel1 * cosAngel1));
				vision[2] = new Plane(position, new Vector3D(sinAngel2 * cosAngel2, -cosAngel2 * cosAngel2 + 1, 0));
				vision[3] = new Plane(position, new Vector3D(sinAngel2 * cosAngel2, cosAngel2 * cosAngel2 - 1, 0));

			} else if (axis.getZ() == 1) { // camera dat tren tran (z = 0)
				vision[0] = new Plane(position, new Vector3D(sinAngel1 * cosAngel1, 0, -cosAngel1 * cosAngel1 + 1));
				vision[1] = new Plane(position, new Vector3D(sinAngel1 * cosAngel1, 0, cosAngel1 * cosAngel1 - 1));
				vision[2] = new Plane(position, new Vector3D(0, sinAngel2 * cosAngel2, -cosAngel2 * cosAngel2 + 1));
				vision[3] = new Plane(position, new Vector3D(0, sinAngel2 * cosAngel2, cosAngel2 * cosAngel2 - 1));
			}
		}
	}

	// Other Method

	public boolean couldSee(Point p) { // Kiem tra 1 vat co nam trong tam nhin cua camera hay ko
		if (top.distance(p) > 100)
			return false;
		if (!Plane.isBetween2Planes(vision[0], vision[1], p))
			return false;
		if (!Plane.isBetween2Planes(vision[2], vision[3], p))
			return false;
		return true;
	}

	/**
	 * Tim giao diem cua vision[] voi doan thang AB, tra ve arrayList sap xep theo
	 * khoang cach toi A tang dan. Moi doan thang chi cat vision[] toi da 2 diem (ko
	 * tinh truong hop doan thang trung voi 1 vision)
	 */

	public ArrayList<Point> intersection(Point A, Point B) {
		ArrayList<Point> arrayPoint = new ArrayList<>();

		Line AB = new Line(A, B);
		for (Plane pl : vision) {

			if (arrayPoint.size() < 2) {
				Point P = AB.intersection(pl);
				if (P != null && P.isBetween2Points(A, B)) {

					if (arrayPoint.size() == 0) {
						arrayPoint.add(P);
					} else {
						double lengthP = Math.abs(P.getX() - A.getX());
						double lengthElement = Math.abs(arrayPoint.get(0).getX() - A.getX());

						if (lengthP > lengthElement) {
							arrayPoint.add(P);
						} else if (lengthP < lengthElement) {
							arrayPoint.add(0, P);
						}
					}
				}
			} else
				break;
		}

		return arrayPoint;
	}

}
