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

	protected void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	protected void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	protected void setZ(double z) {
		this.z = z;
	}

	public String toString() {
		return String.format("(%.2f, %.2f, %.2f)", x, y, z);
	}
}
