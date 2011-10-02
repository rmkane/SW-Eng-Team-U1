
public class MidPoint {
	
	private Point p1, p2, p3;
	
	public MidPoint() {
		p1 = new Point(0,0,0);
		p2 = new Point(0,0,0);
		p3 = new Point(0,0,0);
	}
	
	public MidPoint(Point p1, Point p2) {
		p3 = new Point();
		p3.setX((p1.getX()+p2.getX())/2);
		p3.setY((p1.getY()+p2.getY())/2);
		p3.setZ((p1.getZ()+p2.getZ())/2);
	}
	
	public Point getPoint() {
		return p3;
	}
}
