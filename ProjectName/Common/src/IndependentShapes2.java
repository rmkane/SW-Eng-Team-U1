import java.awt.*;
import java.awt.event.*;
import java.nio.ByteBuffer;

import javax.swing.*;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;


public class IndependentShapes2 extends JPanel implements GLEventListener, KeyListener, MouseListener, MouseMotionListener, ActionListener{

	// mouse control variables
	private final GLCanvas canvas;
	private int winW = 512, winH = 512;
	private int mouseX, mouseY;
	private int mouseButton;
	private boolean mouseClick = false;
	private boolean clickedOnShape = false;
	
	private static final int BUFSIZE = 512;
	//GLuint selectBuf[BUFSIZE];

	// gl shading/transformation variables
	private float tx = 0.0f, ty = 0.0f;
	private float scale = 1.0f;
	private float angle = 0.0f;


	// a set of shapes
	private static final int Triangle = 0, Torus = 1, Sphere = 2, Icosahedron = 3, Teapot = 4;
	private static final int NumShapes = 5;
	// initial shape is a triangle
	private int shape = Triangle;

	// gl context/variables
	private GL2 gl;
	private final GLU glu = new GLU();
	private final GLUT glut = new GLUT();
	
	
	private static final int REFRESH_FPS = 60;
	private float rotateT = 0.0f;
	final FPSAnimator animator;
	
	
	private boolean drawWireframe = false;
	
	
	
	
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
	
	
	public void drawTriangle(GL2 gl, Point p1, Point p2, Point p3, Color color) {
		gl.glBegin(GL.GL_TRIANGLES);
		gl.glColor3d((color.getRed() / 255f),(color.getGreen() / 255f),(color.getBlue() / 255f));
		gl.glVertex3f((float) p1.getX(), (float) p1.getY(), (float) p1.getZ());
		gl.glVertex3f((float) p2.getX(), (float) p2.getY(), (float) p2.getZ());
		gl.glVertex3f((float) p3.getX(), (float) p3.getY(), (float) p3.getZ());
		
		gl.glEnd();
	}
	
	
	public void drawSquare(GL2 gl2, Point p1, Point p2, Point p3, Point p4, Color color) {
		gl.glBegin(GL2.GL_QUADS);
		gl.glColor3d((color.getRed() / 255f),(color.getGreen() / 255f),(color.getBlue() / 255f));
		gl.glVertex3f((float) p1.getX(), (float) p1.getY(), (float) p1.getZ());
		gl.glVertex3f((float) p2.getX(), (float) p2.getY(), (float) p2.getZ());
		gl.glVertex3f((float) p3.getX(), (float) p3.getY(), (float) p3.getZ());
		gl.glVertex3f((float) p4.getX(), (float) p4.getY(), (float) p4.getZ());
		
		gl.glEnd();
	}


	public void drawHexagon(GL2 gl2, Point p1, Point p2, Point p3, Point p4, 
   		Point p5, Point p6, Point p7, Point p8, Color color){
		gl.glBegin(GL2.GL_TRIANGLE_FAN);
		gl.glColor3f((color.getRed() / 255f),(color.getGreen() / 255f),(color.getBlue() / 255f));
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
	


	
	// constructor
	public IndependentShapes2() {
		canvas = new GLCanvas();
		this.setLayout(new BorderLayout());
		this.add(canvas, BorderLayout.CENTER);
		canvas.addGLEventListener(this);
		canvas.addMouseListener(this);
		canvas.addMouseMotionListener(this);
		
		this.setVisible(true);
      
		animator = new FPSAnimator(canvas, REFRESH_FPS, true);
	}
	
	
	public static void main(String args[]) 
	{
		final int WINDOW_WIDTH = 900;
		final int WINDOW_HEIGHT = 700;
		final String WINDOW_TITLE = "This is the JFrame";

		JFrame frame = new JFrame();
		final IndependentShapes2 joglMain = new IndependentShapes2();
		

		frame.setContentPane(joglMain);
		
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				new Thread() {
					public void run() 
					{
						joglMain.animator.stop();
						System.exit(0);
					}
				}.start();
			}
		});
		
		frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		frame.setTitle(WINDOW_TITLE);
		frame.setVisible(true);
		joglMain.animator.start(); // start the animation loop
	}
	

	public void display(GLAutoDrawable drawable) {
		
	    gl = drawable.getGL().getGL2();
		   
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		gl.glClear(GL.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		gl.glTranslatef(0.0f, 0.0f, -15.0f);

		
		//cube(drawable);
		//hexprism(drawable);
		//triprism(drawable);
		//sphere(drawable);
		//cylinder(drawable);
		//rotateT += 2.0f;
		
		
		// if mouse is clicked, we need to detect whether it's clicked on the shape
		if (mouseClick) {
			ByteBuffer pixel = ByteBuffer.allocateDirect(1);

			gl.glClear( GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT );
			gl.glColor3f(1.0f, 1.0f, 1.0f);
			gl.glDisable( GL2.GL_LIGHTING );
			drawShape(drawable);
			gl.glReadPixels(mouseX, (winH-1-mouseY), 1, 1, GL2.GL_RED, GL2.GL_UNSIGNED_BYTE, pixel);
			
			if (pixel.get(0) == (byte)1024) {
				clickedOnShape = true;
			}
			// set mouseClick to false to avoid detecting again
			mouseClick = false;
		}

		//gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		//gl.glEnable(GL2.GL_LIGHTING);
		//gl.glPolygonMode(GL.GL_FRONT_AND_BACK, drawWireframe ? GL2.GL_LINE : GL2.GL_FILL);
		//gl.glColor3f(1.0f, 0.3f, 0.1f);
		
		drawShape(drawable);
		
		//gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL2.GL_FILL);
		
	}

	public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {}

	
	// draw the current shape
	public void drawShape(GLAutoDrawable drawable) {
		gl.glLoadIdentity();
		//gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, lightPos, 0);
		gl.glTranslatef(tx, ty, -10.0f);
		gl.glScalef(scale, scale, scale);
		//gl.glRotatef(angle, 1.0f, 0.0f, 0.0f);
		
		
   		pyramid(drawable);
		rotateT += 2.0f;
			

	}
	
	
	   /***********************************************************************/
	   /**************************** SQUARE PYRAMID ***************************/
	   /***********************************************************************/		
	   public void pyramid (GLAutoDrawable drawable) 
	   {
	   		
		   gl = drawable.getGL().getGL2();
		   	
		   gl.glPushMatrix();
	   	
	   			gl.glRotatef(rotateT, 0.0f, 1.0f, 0.0f);
	   		
		   		//vertices
		   		Point top = new Point(0.0, 1.0, 0.0);
		   		Point q1 = new Point(-1.0,-1.0, 1.0);
		   		Point q2 = new Point( 1.0,-1.0, 1.0);
		   		Point q3 = new Point( 1.0,-1.0, -1.0);
		   		Point q4 = new Point(-1.0,-1.0, -1.0);
		   		
		   		//create shape
		   		drawSquare(gl, q1, q2, q3, q4, MAROON); //base
		   		drawTriangle(gl, top, q1, q2, NAVY);  //front
		   		drawTriangle(gl, top, q2, q3, CYAN); //left
		   		drawTriangle(gl, top, q3, q4, TEAL); // back
		   		drawTriangle(gl, top, q4, q1, BLUE); //right
	   		
	   		

	   		gl.glPopMatrix();
	   }
	

	public void init(GLAutoDrawable drawable) {
		gl = drawable.getGL().getGL2();
    	gl.setSwapInterval(1);

		gl.glColorMaterial(GL.GL_FRONT, GL2.GL_DIFFUSE);
		gl.glEnable( GL2.GL_COLOR_MATERIAL ) ;
		gl.glEnable(GL2.GL_LIGHT0);
		gl.glEnable(GL2.GL_NORMALIZE);
		gl.glShadeModel(GL2.GL_SMOOTH);
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glDepthFunc(GL2.GL_LESS);
		gl.glCullFace(GL2.GL_BACK);
		gl.glEnable(GL2.GL_CULL_FACE);

		//background color = white
		gl.glClearColor(1.0f, 1.0f, 1.0f, 1f);
		gl.glClearDepth(1.0f);
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		winW = width;
		winH = height;

		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		glu.gluPerspective(30.0f, (float) width / (float) height, 0.01f, 100.0f);
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL2.GL_MODELVIEW);		
	}


	public void mousePressed(MouseEvent mouse) {
		mouseClick = true;
		mouseX = mouse.getX();
		mouseY = mouse.getY();
		mouseButton = mouse.getButton();
		canvas.display();
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
		canvas.display();
		
	}
	
	public void keyPressed(KeyEvent key) { }
	public void keyTyped(KeyEvent key) { }
	public void keyReleased(KeyEvent key) { }
	public void mouseClicked(MouseEvent mouse) { }
	public void mouseEntered(MouseEvent mouse) { }
	public void mouseExited(MouseEvent mouse) {	}
	public void mouseMoved(MouseEvent mouse) { }
	public void actionPerformed(ActionEvent action) { }

	public void dispose(GLAutoDrawable drawable) { }

}
