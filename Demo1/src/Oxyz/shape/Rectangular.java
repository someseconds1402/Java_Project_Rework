package Oxyz.shape;

import java.util.ArrayList;

import Oxyz.camera.Camera;
import Oxyz.plane_line_vector.Line;
import Oxyz.plane_line_vector.Plane;
import Oxyz.plane_line_vector.Point;
import Oxyz.plane_line_vector.Vector3D;

public class Rectangular {
	private Point A1;
	private Point B1;
	private Point C1;
	private double h; // height

	private Point D1;
	private Point A2;
	private Point B2;
	private Point C2;
	private Point D2;

	// Constructor

	public Rectangular(Point a1, Point b1, Point c1, double h) {
		A1 = a1;
		B1 = b1;
		C1 = c1;
		this.h = h;

		D1 = new Point();
		A2 = new Point();
		B2 = new Point();
		C2 = new Point();
		D2 = new Point();

		this.setD1();
		this.setA2();
		this.setB2();
		this.setC2();
		this.setD2();
	}

	// Setter and Getter

	public Point getA1() {
		return A1;
	}

	public void setA1(Point a1) {
		A1 = a1;
	}

	public Point getB1() {
		return B1;
	}

	public void setB1(Point b1) {
		B1 = b1;
	}

	public Point getC1() {
		return C1;
	}

	public void setC1(Point c1) {
		C1 = c1;
	}

	public double getH() {
		return h;
	}

	public void setH(double h) {
		this.h = h;
	}

	public Point getD1() {
		return D1;
	}

	public void setD1(Point d1) {
		D1 = d1;
	}

	public void setD1() {
		D1.setX(A1.getX() + C1.getX() - B1.getX());
		D1.setY(A1.getY() + C1.getY() - B1.getY());
		D1.setZ(A1.getZ() + C1.getZ() - B1.getZ());
	}

	public Point getA2() {
		return A2;
	}

	public void setA2(Point a2) {
		A2 = a2;
	}

	public void setA2() {
		A2.setX(A1.getX());
		A2.setY(A1.getY());
		A2.setZ(A1.getZ() + h);
	}

	public Point getB2() {
		return B2;
	}

	public void setB2(Point b2) {
		B2 = b2;
	}

	public void setB2() {
		B2.setX(B1.getX());
		B2.setY(B1.getY());
		B2.setZ(B1.getZ() + h);
	}

	public Point getC2() {
		return C2;
	}

	public void setC2(Point c2) {
		C2 = c2;
	}

	public void setC2() {
		C2.setX(C1.getX());
		C2.setY(C1.getY());
		C2.setZ(C1.getZ() + h);
	}

	public Point getD2() {
		return D2;
	}

	public void setD2(Point d2) {
		D2 = d2;
	}

	public void setD2() {
		D2.setX(D1.getX());
		D2.setY(D1.getY());
		D2.setZ(D1.getZ() + h);
	}

	/****************************************************************************/

	/** Kiem tra 1 diem co nam trong vat the hay khong */
	public boolean contain(Point p) {
		double n1, n2, n3, n4, n5, n6;
		Plane pl1, pl2, pl3, pl4, pl5, pl6;
		pl1 = new Plane(A1, B1, C1); // Mat phang A1.B1.C1.D1 (1)
		pl2 = new Plane(A1, B1, A2); // Mat phang A1.B1.B2.A2 (2)
		pl3 = new Plane(A1, D1, A2); // Mat phang A1.D1.D2.A2 (3)
		pl4 = new Plane(A2, pl1.getV()); // Mat phang A2.B2.C2.D2 (1)
		pl5 = new Plane(C1, pl2.getV()); // Mat phang C1.D1.D2.C2 (2)
		pl6 = new Plane(B1, pl3.getV()); // Mat phang B1.C1.C2.B2 (3)

		n1 = pl1.doSomething(p);
		n4 = pl4.doSomething(p);

		if (n1 * n4 > 0) {
			return false;
		}

		n2 = pl2.doSomething(p);
		n5 = pl5.doSomething(p);
		if (n2 * n5 > 0f) {
			return false;
		}

		n3 = pl3.doSomething(p);
		n6 = pl6.doSomething(p);
		if (n3 * n6 > 0f) {
			return false;
		}

		return true;
	}

	/** Xac dinh 3 diem dau vao co tao thanh 1 goc vuong hay ko */
	public boolean isPerpendicular() {
		Vector3D u1 = new Vector3D(A1, B1);
		Vector3D u2 = new Vector3D(B1, C1);
		return (u1.getX() * u2.getX() + u1.getY() * u2.getY() + u1.getZ() * u2.getZ()) == 0;
	}

	public boolean isInRect(Line l1, Line l2, Line l3, Line l4, Point p) { // Xac dinh 1 diem co nam trong day cua vat
																			// the hay khong
		if (Line.isBetween2Lines(l1, l2, p) && Line.isBetween2Lines(l3, l4, p)) {
			return true;
		}

		return false;
	}

	public boolean isInRect2(Line l1, Line l2, Line l3, Line l4, Point p) { // Xac dinh 1 diem co nam trong day cua vat
																			// the hay khong
		if (Line.isBetween2Lines2(l1, l2, p) && Line.isBetween2Lines2(l3, l4, p)) {
			return true;
		}

		return false;
	}

	public boolean isValidPosition(Rectangular r) { // dinh cua 1 vat the co nam trong vat the khac hay khong
		Line AB = new Line(A1, B1);
		Line CD = new Line(C1, AB.getU());
		Line AD = new Line(A1, D1);
		Line BC = new Line(B1, AD.getU());

		if (isInRect2(AB, CD, AD, BC, r.getA1()))
			return true;
		if (isInRect2(AB, CD, AD, BC, r.getB1()))
			return true;
		if (isInRect2(AB, CD, AD, BC, r.getC1()))
			return true;
		if (isInRect2(AB, CD, AD, BC, r.getD1()))
			return true;

		return false;
	}

	public boolean centroidIsInRect(Rectangular r) { // Xac dinh xem trong tam cua vat the r co nam trong day cua vat
														// the this hay ko
		double Gx = (r.getA1().getX() + r.getC1().getX()) / 2;
		double Gy = (r.getA1().getY() + r.getC1().getY()) / 2;
		double Gz = (r.getA1().getZ() + r.getC1().getZ()) / 2;

		Point G = new Point(Gx, Gy, Gz);

		return isInRect(G);
	}

	public boolean isInRect(Point p) { // Xac dinh 1 diem co nam trong day cua vat the hay khong
		Line AB = new Line(A1, B1);
		Line CD = new Line(C1, AB.getU());
		Line AD = new Line(A1, D1);
		Line BC = new Line(B1, AD.getU());

		if (Line.isBetween2Lines(AB, CD, p) && Line.isBetween2Lines(AD, BC, p)) {
			return true;
		}

		return false;
	}

	public boolean isInRect2(Point p) { // Xac dinh 1 diem co nam trong day cua vat the hay khong
		Line AB = new Line(A1, B1);
		Line CD = new Line(C1, AB.getU());
		Line AD = new Line(A1, D1);
		Line BC = new Line(B1, AD.getU());

		// if(Line.isBetween2Lines2(AB, CD, p))
		// System.out.println(1);
		// if(Line.isBetween2Lines2(AD, BC, p))
		// System.out.println(2);

		if (Line.isBetween2Lines2(AB, CD, p) && Line.isBetween2Lines2(AD, BC, p)) {
			return true;
		}

		return false;
	}

	// Xac dinh giao diem giua duong thang va vat the
	public Point intersection(Line l) {
		Point p;
		p = l.intersection(new Plane(A2, B2, C2)); // A2.B2.C2.D2
		if (p == null || !contain(p)) {
			p = l.intersection(new Plane(A1, A2, B2)); // A1.A2.B2.B1
			if (p == null || !contain(p)) {
				p = l.intersection(new Plane(A1, A2, D2)); // A1.A2.D2.D1
				if (p == null || !contain(p)) {
					p = l.intersection(new Plane(B1, B2, C2)); // B1.B2.C2.C1
					if (p == null || !contain(p)) {
						p = l.intersection(new Plane(C1, D1, D2)); // C1.D1.D2.C2
						if (p == null || !contain(p)) {
							p = l.intersection(new Plane(A1, B1, C1)); // A1.B1.C1.D1
							if (p == null || !contain(p)) {
								return null;
							}
						}
					}
				}
			}
		}

		return p;
	}

	/**
	 * Tim giao diem cua day tren (A2B2C2D2) cua vat the voi camera, tra ve
	 * arrayList sap xep theo khoang cach toi A tang dan. Moi doan thang chi cat
	 * vision[] toi da 2 diem (ko tinh truong hop doan thang trung voi 1 vision)
	 */

	public ArrayList<Point> intersection(Camera camera) {
		ArrayList<Point> arrayPoint = new ArrayList<>();

		arrayPoint.add(A2);
		arrayPoint.addAll(camera.intersection(A2, B2));

		arrayPoint.add(B2);
		arrayPoint.addAll(camera.intersection(B2, C2));

		arrayPoint.add(C2);
		arrayPoint.addAll(camera.intersection(C2, D2));

		arrayPoint.add(D2);
		arrayPoint.addAll(camera.intersection(D2, A2));

		arrayPoint.add(A2);

		return arrayPoint;
	}

	public ArrayList<Point> inVision(Camera camera) { // Xac dinh cac diem cua vat the nam trong tam nhin cua camera
		ArrayList<Point> arrayPoint = new ArrayList<>();
		if (camera.couldSee(A2))
			arrayPoint.add(A2);
		if (camera.couldSee(B2))
			arrayPoint.add(B2);
		if (camera.couldSee(C2))
			arrayPoint.add(C2);
		if (camera.couldSee(D2))
			arrayPoint.add(D2);

		return arrayPoint;
	}

	public ArrayList<Point> projection(Camera camera) { // Xac dinh cac diem cua da giac hinh chieu trong mat phang day
		ArrayList<Point> arr4 = new ArrayList<>();

		ArrayList<Point> arr1 = new ArrayList<>();
		arr1.add(A2);
		arr1.add(B2);
		arr1.add(C2);
		arr1.add(D2);

		ArrayList<Point> arr2 = intersection(camera); // array dung de duyet
		ArrayList<Point> arr3 = inVision(camera);

		Plane oxy = new Plane(new Point(), new Point(0, 1, 0), new Point(1, 0, 0));
		Point camPos = camera.getPosition();

		Point first = arr2.remove(0);
		if (arr3.contains(first)) {
			Line l = new Line(first, camPos);
			arr4.add(l.intersection(oxy));
		} else {
			arr4.add(first);
		}

		if (arr1.size() == arr3.size()) { // Ca 4 dinh cua vat the nam trong tam nhin cua camera
			for (Point element : arr1) {
				arr4.add(oxy.intersection(new Line(element, camPos)));
			}
		}
		// else if(arr3.size() == 0) {
		// arr4 = arr1;
		// }
		else {
			// duyet arr2
			for (Point element : arr2) {
				if (arr1.contains(element)) {
					if (arr3.contains(element)) {
						Point lastOfArr4 = arr4.get(arr4.size() - 1);
						Point interPoint = oxy.intersection(new Line(element, camPos)); // Giao diem cua mp Oxy voi
																						// duong thang noi giua camera
																						// va element

						if (arr2.contains(lastOfArr4)) {
							arr4.add(element);
							arr4.add(interPoint);
						} else {
							int nextElementIndex = arr2.indexOf(element) + 1;
							if (nextElementIndex < arr2.size()) {
								Point nextELement = arr2.get(nextElementIndex);
								if (arr1.contains(nextELement) && !arr3.contains(nextELement)) {
									arr4.add(interPoint);
									arr4.add(element);
								} else {
									arr4.add(interPoint);
								}
							}
						}

					} else {
						arr4.add(element);
					}

				} else {
					Point lastOfArr4 = arr4.get(arr4.size() - 1);
					Point interPoint = oxy.intersection(new Line(element, camPos)); // Giao diem cua mp Oxy voi duong
																					// thang noi giua camera va element

					if (arr2.contains(lastOfArr4)) {
						arr4.add(element);
						arr4.add(interPoint);
					} else {
						int nextElementIndex = arr2.indexOf(element) + 1;
						if (nextElementIndex < arr2.size()) {
							Point nextELement = arr2.get(nextElementIndex);
							if (arr1.contains(nextELement) && !arr3.contains(nextELement)) {
								arr4.add(interPoint);
								arr4.add(element);
							} else {
								arr4.add(interPoint);
							}
						}
					}
				}
			}
		}

		for (Point element : arr4) {
			element.setZ(0);
		}

		if (arr4.get(0).compare(arr4.get(arr4.size() - 1))) {
			arr4.remove(arr4.size() - 1);
		}

		return arr4;
	}

}
