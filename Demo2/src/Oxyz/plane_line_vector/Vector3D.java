package Oxyz.plane_line_vector;

public class Vector3D {
	private double x;
	private double y;
	private double z;
	
	public Vector3D(double x, double y, double z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	// Constructor
	
	public Vector3D(Point p1, Point p2) {
		x = p1.getX() - p2.getX();
		y = p1.getY() - p2.getY();
		z = p1.getZ() - p2.getZ();
	}
	
	public Vector3D(Vector3D v1, Vector3D v2) {
		/* Nhan co huong 2 vector [v1(a1, b1, c1) , v2(a2, b2, c2)] = v3(a3, b3, c3)
		 * 
		 * a3 = b1*c2 - b2*c1
		 * b3 = c1*a2 - c2*a1
		 * c3 = a1*b2 - a2*b1
		 */
		x = v1.getY()*v2.getZ() - v2.getY()*v1.getZ();
		y = v1.getZ()*v2.getX() - v2.getZ()*v1.getX();
		z = v1.getX()*v2.getY() - v2.getX()*v1.getY();
	}
	
	// Getter & Setter
	
	public double getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public double getZ() {
		return z;
	}
	public void setZ(int z) {
		this.z = z;
	}
	
	// Other Method

	public boolean isPerpendicular(Vector3D v) { // xac dinh 2 vector co vuong goc voi nhau hay ko
		return (x*v.getX() + y*v.getY() + z*v.getZ()) == 0;
	}
	
	/** Xac dinh 3 diem co tao thanh 2 vector vuong goc voi nhau tai p2 hay ko, tra ve true neu dung */
	public static boolean isPerpendicular(Point p1, Point p2, Point p3) { 
		if(p1.isDifference(p2)) {
			if(p2.isDifference(p3)) {
				Vector3D v1 = new Vector3D(p1, p2);
				return v1.isPerpendicular(new Vector3D(p2, p3));
			} else {
				return false;
			}
		} else {
			return false;
		}		
	}
}
