import java.awt.Color;
//import JOGL

public class Shape3D {

	private double posX, posY, posZ;
	private double length, width, height;
	private Color faceColor, edgeColor;
	private int edgeWeight;

	public Shape3D() {
		setPosX(0);
		setPosY(0);
		setPosZ(0);
		setLength(0);
		setWidth(0);
		setHeight(0);
		setFaceColor(new Color(0xFFFFFF));
		setEdgeColor(new Color(0x000000));
		setEdgeWeight(0);
	}

	public Shape3D(double x, double y, double z, double length, double width,
			double height, Color face, Color edge, int weight) {
		setPosX(x);
		setPosY(y);
		setPosZ(z);
		setLength(length);
		setWidth(width);
		setHeight(height);
		setFaceColor(face);
		setEdgeColor(edge);
		setEdgeWeight(weight);
	}

	public double getPosX() {
		return posX;
	}

	protected void setPosX(double posX) {
		this.posX = posX;
	}

	public double getPosY() {
		return posY;
	}

	protected void setPosY(double posY) {
		this.posY = posY;
	}

	public double getPosZ() {
		return posZ;
	}

	protected void setPosZ(double posZ) {
		this.posZ = posZ;
	}

	public double getLength() {
		return length;
	}

	protected void setLength(double length) {
		this.length = length;
	}

	public double getWidth() {
		return width;
	}

	protected void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	protected void setHeight(double height) {
		this.height = height;
	}

	public Color getFaceColor() {
		return faceColor;
	}

	protected void setFaceColor(Color faceColor) {
		this.faceColor = faceColor;
	}

	public Color getEdgeColor() {
		return edgeColor;
	}

	protected void setEdgeColor(Color edgeColor) {
		this.edgeColor = edgeColor;
	}

	public int getEdgeWeight() {
		return edgeWeight;
	}

	protected void setEdgeWeight(int edgeWeight) {
		this.edgeWeight = edgeWeight;
	}
}
