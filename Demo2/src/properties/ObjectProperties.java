package properties;

import Oxyz.shape.Rectangular;

public class ObjectProperties {
	private String a1;
	private String b1;
	private String c1;
	private double height;

	public ObjectProperties(Rectangular r) {
		a1 = r.getA1().getProperties();
		b1 = r.getB1().getProperties();
		c1 = r.getC1().getProperties();
		height = r.getH();
	}

	public String getA1() {
		return a1;
	}

	public void setA1(String a1) {
		this.a1 = a1;
	}

	public String getB1() {
		return b1;
	}

	public void setB1(String b1) {
		this.b1 = b1;
	}

	public String getC1() {
		return c1;
	}

	public void setC1(String c1) {
		this.c1 = c1;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

}
