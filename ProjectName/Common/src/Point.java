public class Point {
	private double x, y, z;

	public Point() {
		setX(0);
		setY(0);
		setZ(0);
	}

	public Point(double x, double y, double z) {
		setX(x);
		setY(y);
		setZ(z);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public String toString() {
		return String.format("(%.2f, %.2f, %.2f)", x, y, z);
	}
}
