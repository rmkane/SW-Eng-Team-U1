import java.awt.Color;
import java.awt.event.*;
import java.nio.ByteBuffer;

import javax.swing.*;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.*;

import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;


public class Scene extends JFrame implements GLEventListener, KeyListener, MouseListener, MouseMotionListener, ActionListener
{
	//private GLU glu;
	//private GLUT glut;
	//private GLCapabilities caps;
	private GLCanvas canvas;
  
	private int winW = 512, winH = 512;
	private int mouseX, mouseY;
	private int mouseButton;
	private boolean mouseClick = false;
	private boolean clickedOnShape = false;
	
	private float tx = 0.0f, ty = 0.0f;
	private float scale = 1.0f;
  
	private static final int REFRESH_FPS = 60;
	final FPSAnimator animator;
  	private float rotateT = 0.0f;
  	
  	private String curPos;
  
  
	private final Color MAROON = new Color(128, 0, 0);
	private final Color RED = new Color(255, 0, 0);
	private final Color ORANGE = new Color(255, 165, 0);
	private final Color YELLOW = new Color(255, 255, 0);
	private final Color OLIVE = new Color(128, 128, 0);
	private final Color GREEN = new Color(0, 128, 0);
	private final Color LIME = new Color(0, 255, 0);
	private final Color TEAL = new Color(0, 128, 128);
	private final Color CYAN = new Color(0, 255, 255);
	private final Color BLUE = new Color(0, 0, 255);
	private final Color NAVY = new Color(0, 0, 128);
	private final Color PURPLE = new Color(128, 0, 128);
	private final Color FUCHSIA = new Color(255, 0, 255);
	private final Color WHITE = new Color(255, 255, 255);
	private final Color SILVER = new Color(192, 192, 192);
	private final Color BLACK = new Color(0, 0, 0);
	
	
	public void drawTriangle(GL2 gl, Point p1, Point p2, Point p3, Color faceColor, Color edgeColor, int edgeWeight) {
		gl.glPolygonOffset(0.0f, 1.0f);
		gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);
		gl.glEnable( GL.GL_POLYGON_OFFSET_FILL );      
	    gl.glPolygonOffset( 1.0f, 1.0f );
	    
		gl.glBegin(GL2.GL_TRIANGLES);
			gl.glColor3d((faceColor.getRed() / 255f),(faceColor.getGreen() / 255f),
					(faceColor.getBlue() / 255f));
			gl.glVertex3f((float) p1.getX(), (float) p1.getY(), (float) p1.getZ());
			gl.glVertex3f((float) p2.getX(), (float) p2.getY(), (float) p2.getZ());
			gl.glVertex3f((float) p3.getX(), (float) p3.getY(), (float) p3.getZ());
		gl.glEnd();
		
		gl.glDisable( GL.GL_POLYGON_OFFSET_FILL );
		
		drawTriangleEdges(gl, p1, p2, p3, edgeColor, edgeWeight);
	}
	
	
	public void drawTriangleEdges(GL2 gl, Point p1, Point p2, Point p3, Color edgeColor, int edgeWeight) {
		gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_LINE);
		gl.glLineWidth((float) 5);
		gl.glPolygonOffset(0.0f, -1.0f);
		gl.glBegin(GL2.GL_TRIANGLES);
			gl.glColor3d((edgeColor.getRed() / 255f),(edgeColor.getGreen() / 255f),
					(edgeColor.getBlue() / 255f));
			gl.glVertex3f((float) p1.getX(), (float) p1.getY(), (float) p1.getZ());
			gl.glVertex3f((float) p2.getX(), (float) p2.getY(), (float) p2.getZ());
			gl.glVertex3f((float) p3.getX(), (float) p3.getY(), (float) p3.getZ());
		gl.glEnd();
	}
	
	
	public void drawSquare(GL2 gl, Point p1, Point p2, Point p3, Point p4, Color faceColor, Color edgeColor, int edgeWeight) {
		gl.glPolygonOffset(0.0f, 1.0f);
		gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);
		gl.glEnable( GL.GL_POLYGON_OFFSET_FILL );      
	    gl.glPolygonOffset( 1.0f, 1.0f );
		gl.glBegin(GL2.GL_QUADS);
			gl.glColor3d((faceColor.getRed() / 255f),(faceColor.getGreen() / 255f),(faceColor.getBlue() / 255f));
			gl.glVertex3f((float) p1.getX(), (float) p1.getY(), (float) p1.getZ());
			gl.glVertex3f((float) p2.getX(), (float) p2.getY(), (float) p2.getZ());
			gl.glVertex3f((float) p3.getX(), (float) p3.getY(), (float) p3.getZ());
			gl.glVertex3f((float) p4.getX(), (float) p4.getY(), (float) p4.getZ());
		gl.glEnd();
		
		drawSquareEdges(gl, p1, p2, p3, p4, edgeColor, edgeWeight);
	}
	
	
	public void drawSquareEdges(GL2 gl, Point p1, Point p2, Point p3, Point p4, Color edgeColor, int edgeWeight) {
		gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_LINE);
		gl.glLineWidth((float) 5);
		gl.glPolygonOffset(1.0f, -1.0f);
		gl.glBegin(GL2.GL_QUADS);
			gl.glColor3d((edgeColor.getRed() / 255f),(edgeColor.getGreen() / 255f),
					(edgeColor.getBlue() / 255f));
			gl.glVertex3f((float) p1.getX(), (float) p1.getY(), (float) p1.getZ());
			gl.glVertex3f((float) p2.getX(), (float) p2.getY(), (float) p2.getZ());
			gl.glVertex3f((float) p3.getX(), (float) p3.getY(), (float) p3.getZ());
			gl.glVertex3f((float) p4.getX(), (float) p4.getY(), (float) p4.getZ());
		gl.glEnd();
	}


	public void drawHexagon(GL2 gl, Point p1, Point p2, Point p3, Point p4, 
 		Point p5, Point p6, Point p7, Point p8, Color faceColor, Color edgeColor, int edgeWeight){
		gl.glPolygonOffset(0.0f, 1.0f);
		gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);
		gl.glEnable( GL.GL_POLYGON_OFFSET_FILL );      
	    gl.glPolygonOffset( 1.0f, 1.0f );
	    
		gl.glBegin(GL2.GL_TRIANGLE_FAN);
		gl.glColor3f((faceColor.getRed() / 255f),(faceColor.getGreen() / 255f),(faceColor.getBlue() / 255f));
		gl.glVertex3f((float) p1.getX(), (float) p1.getY(), (float) p1.getZ());
		gl.glVertex3f((float) p2.getX(), (float) p2.getY(), (float) p2.getZ());
		gl.glVertex3f((float) p3.getX(), (float) p3.getY(), (float) p3.getZ());
		gl.glVertex3f((float) p4.getX(), (float) p4.getY(), (float) p4.getZ());
		gl.glVertex3f((float) p5.getX(), (float) p5.getY(), (float) p5.getZ());
		gl.glVertex3f((float) p6.getX(), (float) p6.getY(), (float) p6.getZ());
		gl.glVertex3f((float) p7.getX(), (float) p7.getY(), (float) p7.getZ());
		gl.glVertex3f((float) p8.getX(), (float) p8.getY(), (float) p8.getZ());
		
		gl.glEnd(); 	
		
		drawHexagonEdges(gl, p1, p2, p3, p4, p5, p6, p7, p8, edgeColor, edgeWeight);
	}
	
	
	public void drawHexagonEdges(GL2 gl, Point p1, Point p2, Point p3, Point p4, 
	 		Point p5, Point p6, Point p7, Point p8, Color edgeColor, int edgeWeight) {
		gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_LINE);
		gl.glLineWidth((float) 5);
		gl.glPolygonOffset(0.0f, -1.0f);
		gl.glBegin(GL2.GL_QUADS);
			gl.glColor3d((edgeColor.getRed() / 255f),(edgeColor.getGreen() / 255f),
					(edgeColor.getBlue() / 255f));
			gl.glVertex3f((float) p1.getX(), (float) p1.getY(), (float) p1.getZ());
			gl.glVertex3f((float) p2.getX(), (float) p2.getY(), (float) p2.getZ());
			gl.glVertex3f((float) p3.getX(), (float) p3.getY(), (float) p3.getZ());
			gl.glVertex3f((float) p4.getX(), (float) p4.getY(), (float) p4.getZ());
			gl.glVertex3f((float) p5.getX(), (float) p5.getY(), (float) p5.getZ());
			gl.glVertex3f((float) p6.getX(), (float) p6.getY(), (float) p6.getZ());
			gl.glVertex3f((float) p7.getX(), (float) p7.getY(), (float) p7.getZ());
			gl.glVertex3f((float) p8.getX(), (float) p8.getY(), (float) p8.getZ());
		gl.glEnd();
	}
  

 
  public Scene()
  {
	  GLCanvas canvas = new GLCanvas();
	  setCanvas(canvas);	
	  //super("Testing Drag-and-Drop");

    	//canvas = new GLCanvas();
    	this.canvas.addGLEventListener(this);
    	this.canvas.addKeyListener(this);
    	this.canvas.addMouseListener(this);
    	this.canvas.addMouseMotionListener(this);
    
	    //JFrame frame = new JFrame("THIS IS A JFRAME");
	    //frame.setSize(900, 700);
		
		//frame.getContentPane().add(canvas);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		animator = new FPSAnimator(canvas, REFRESH_FPS, true);
    	animator.start();
    	
    	//frame.setVisible(true);
  }

  public void run()
  {
	  
	  
	  //setSize(800, 600);
	  //setLocationRelativeTo(null);
	  canvas.requestFocusInWindow();
	  animator.start();
  }

  public static void main(String[] args)
  {
	  Scene joglmain = new Scene();
	  joglmain.run();
	  //joglmain.animator.start();
  }


 public void init(GLAutoDrawable drawable)
  {
	 GL2 gl = drawable.getGL().getGL2();
	 //gl.glEnable(GL2.GL_LIGHTING);
	 gl.glEnable(GL2.GL_LIGHT0);
	 gl.glDepthFunc(GL.GL_LESS);
	 gl.glEnable(GL.GL_DEPTH_TEST);
    
    
	 gl = drawable.getGL().getGL2();
	 gl.setSwapInterval(1);

	 gl.glColorMaterial(GL.GL_FRONT, GL2.GL_DIFFUSE);
	 gl.glEnable( GL2.GL_COLOR_MATERIAL ) ;
	 gl.glEnable(GL2.GL_NORMALIZE);
	 gl.glShadeModel(GL2.GL_SMOOTH);
	 gl.glCullFace(GL2.GL_BACK);
	 gl.glEnable(GL2.GL_CULL_FACE);

	 //background color = white
	 gl.glClearColor(1.0f, 1.0f, 1.0f, 1f);
	 gl.glClearDepth(1.0f);

  }

	public void display(GLAutoDrawable drawable)
	{
		GL2 gl = drawable.getGL().getGL2();
  
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
    
		if (mouseClick) {
			ByteBuffer pixel = ByteBuffer.allocateDirect(1);

			gl.glClear( GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT );
			gl.glColor3f(1.0f, 1.0f, 1.0f);
			gl.glDisable( GL2.GL_LIGHTING );
			drawShape(drawable);
			gl.glReadPixels(mouseX, (winH-1-mouseY), 1, 1, GL2.GL_RED, GL2.GL_UNSIGNED_BYTE, pixel);
		
			if (pixel.get(0) == (byte)1024)
				clickedOnShape = true;
			mouseClick = false;
		}
    
		drawShape(drawable);
	}
  
  
	public void drawShape(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		gl.glLoadIdentity();
		gl.glTranslatef(tx, ty, -10.0f);
		
 		pyramid(drawable);
 		rotateT += 2.0f;
	}
  
  
	public void pyramid (GLAutoDrawable drawable) 
	{	
		GL2 gl = drawable.getGL().getGL2();
	   	
		gl.glPushMatrix();
	   
	  		gl.glRotatef(rotateT, 1.0f, 0.0f, 0.0f);
  		
	   		//vertices
	   		Point top = new Point(0.0, 1.0, 0.0);
	   		Point q1 = new Point(-1.0,-1.0, 1.0);
	   		Point q2 = new Point( 1.0,-1.0, 1.0);
	   		Point q3 = new Point( 1.0,-1.0, -1.0);
	   		Point q4 = new Point(-1.0,-1.0, -1.0);
	   		
	   		//create shape
	   		drawSquare(gl, q2, q1, q4, q3, MAROON, RED, 5); //base
	   		drawTriangle(gl, top, q1, q2, NAVY, RED, 5);  //front
	   		drawTriangle(gl, top, q2, q3, CYAN, RED, 5); //left
	   		drawTriangle(gl, top, q3, q4, TEAL, RED, 5); // back
	   		drawTriangle(gl, top, q4, q1, BLUE, RED, 5); //right

  		gl.glPopMatrix();
  	}
  

  	public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h)
  	{
  		GL2 gl = drawable.getGL().getGL2();
  
  		gl.glViewport(0, 0, w, h);
  		gl.glMatrixMode(GL2.GL_PROJECTION);
  		gl.glLoadIdentity();
  		if (w <= h) 
  			gl.glOrtho(-2.5, 2.5, 
  					-2.5 * (float) h / (float) w, 2.5 * (float) h / (float) w, -20.0, 20.0);
  		else 
  			gl.glOrtho(-2.5 * (float) w / (float) h, 
  					2.5 * (float) w / (float) h, -2.5, 2.5, -20.0, 20.0);
  		
  		gl.glMatrixMode(GL2.GL_MODELVIEW);
  	}
  

	public void mousePressed(MouseEvent mouse) {
		mouseClick = true;
		mouseX = mouse.getX();
		mouseY = mouse.getY();
		
		System.out.println("Mouse pressed at x=" + mouseX + ", y=" + mouseY);
		mouseButton = mouse.getButton();
		getCanvas().display();
	}

	public void mouseReleased(MouseEvent mouse) {
		clickedOnShape = false;
		canvas.display();
	}

	public void mouseDragged(MouseEvent mouse) {
		if (!clickedOnShape)	return;

		int x = mouse.getX();
		int y = mouse.getY();

		if (mouseButton == MouseEvent.BUTTON1) {
			tx += (x - mouseX) * 0.01f;
			ty -= (y - mouseY) * 0.01f;
		}
		
		mouseX = x;
		mouseY = y;
		
		setMouseX(x);
		setMouseY(y);
		
		canvas.display();
		
		System.out.println("Dragged:" + getMouseX() + ", " + getMouseY());
		
		
		
	}
	
	/*************** Get mouse cursor position ****************/
	public String getCurPos() { return curPos; }
	public void setCurPos(String curPos) { this.curPos = curPos; }
	public int getMouseX() { return mouseX; }
	public void setMouseX(int mouseX) {	this.mouseX = mouseX; }
	public int getMouseY() { return mouseY; }
	public void setMouseY(int mouseY) {	this.mouseY = mouseY; }
	/**********************************************************/

	public void keyPressed(KeyEvent key) { }
	public void keyTyped(KeyEvent key) { }
	public void keyReleased(KeyEvent key) { }
	public void mouseClicked(MouseEvent mouse) { }
	public void mouseEntered(MouseEvent mouse) { }
	public void mouseExited(MouseEvent mouse) {	}
	
	public void mouseMoved(MouseEvent mouse) {
		
		setCurPos(" Current Position: (" + getMouseX() + ", " + getMouseY() + ")  |  Selected: x  |  Total Shapes: x");
		/**
		int x = mouse.getX();
		int y = mouse.getY();

		mouseX = x;
		mouseY = y;
		
		System.out.println("(" + mouseX + "," + mouseY + ")");
		*/
	}
	
	public void actionPerformed(ActionEvent action) { }

	public void dispose(GLAutoDrawable drawable) { }


	public GLCanvas getCanvas() {
		return canvas;
	}


	public void setCanvas(GLCanvas canvas) {
		this.canvas = canvas;
	}}