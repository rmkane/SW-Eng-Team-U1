// http://www.dannyfowler.com/?q=installJOGL\
// Showed Donald this link ^^^

public class Test {
	public static void main(String[] args) {
		Point p1 = new Point(34, 29, 5);
		Point p2 = new Point(-35, -19, -37);
		MidPoint m = new MidPoint(p1, p2);
		Point p3 = m.getPoint();
		
		System.out.println(p3.toString());
	}
}
