package properties;

import Oxyz.camera.Camera;

public class CameraProperties {
	private String position;
	private double angle1;
	private double angle2;
	
	public CameraProperties(Camera camera) {
		position = camera.getPosition().getProperties();
		angle1 = camera.getAngle1();
		angle2 = camera.getAngle2();
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
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

}
