/***********************************************************************
 * -----------------------
 * IndependentShapes2.java
 * -----------------------
 * Team U1
 * Example JOGL Canvas
 * 
 * Renders one of each of the following shapes:
 *  - rectangular prism
 *  - square pyramid
 *  - triangular prism, 
 *  - hexagonal prism
 *  - sphere
 *  - cylinder
 *  
 * Each shape rotate, scales, and moves independently
 * 
 ***********************************************************************/

import java.awt.*;
import java.awt.event.*;
import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;



public class IndependentShapes2 extends JPanel 
	implements GLEventListener, MouseListener, MouseMotionListener, ActionListener{

	private final GLCanvas canvas;
	private int mouseX, mouseY;
	private int mouseButton;
	private boolean clickedOnShape = false;
	
	private float tx = 0.0f, ty = 0.0f;

	private GL2 gl;
	private final GLU glu = new GLU();
	private final GLUT glut = new GLUT();
	
	private static final int REFRESH_FPS = 60;
	private float rotateT = 0.0f;
	final FPSAnimator animator;
	
	
	
/***********************************************************************/
/******************************** COLORS *******************************/
/***********************************************************************/	
	
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
	
	

/***********************************************************************/
/****************************** DRAW FACES *****************************/
/***********************************************************************/	
	/* ---TRIANGLE--- */
	public void drawTriangle
	(GL2 gl, Point p1, Point p2, Point p3, Color faceColor, Color edgeColor, int edgeWeight) {
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
	
	
	/* ---TRIANGLE EDGES--- */
	public void drawTriangleEdges
	(GL2 gl, Point p1, Point p2, Point p3, Color edgeColor, int edgeWeight) {
		gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_LINE);
		gl.glLineWidth((float) edgeWeight);
		gl.glPolygonOffset(0.0f, -1.0f);
		gl.glBegin(GL2.GL_TRIANGLES);
			gl.glColor3d((edgeColor.getRed() / 255f),(edgeColor.getGreen() / 255f),
					(edgeColor.getBlue() / 255f));
			gl.glVertex3f((float) p1.getX(), (float) p1.getY(), (float) p1.getZ());
			gl.glVertex3f((float) p2.getX(), (float) p2.getY(), (float) p2.getZ());
			gl.glVertex3f((float) p3.getX(), (float) p3.getY(), (float) p3.getZ());
		gl.glEnd();
	}
	
	
	/* ---SQUARE--- */
	public void drawSquare
	(GL2 gl, Point p1, Point p2, Point p3, Point p4, Color faceColor, Color edgeColor, int edgeWeight) {
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
	
	
	/* ---SQUARE EDGES--- */
	public void drawSquareEdges
	(GL2 gl, Point p1, Point p2, Point p3, Point p4, Color edgeColor, int edgeWeight) {
		gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_LINE);
		gl.glLineWidth((float) edgeWeight);
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


	/* ---HEXAGON--- */
	public void drawHexagon
	(GL2 gl, Point p1, Point p2, Point p3, Point p4, Point p5, Point p6, Point p7, 
			Point p8, Color faceColor, Color edgeColor, int edgeWeight){
		//gl.glPolygonOffset(0.0f, 1.0f);
		gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);
		gl.glEnable( GL.GL_POLYGON_OFFSET_FILL );      
	    gl.glPolygonOffset( 1.0f, 1.0f );
	    
		gl.glBegin(GL2.GL_TRIANGLE_FAN);
		gl.glColor3f((faceColor.getRed() / 255f),(faceColor.getGreen() / 255f),
				(faceColor.getBlue() / 255f));
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
	
	
	/* ---HEXAGON EDGES--- */
	public void drawHexagonEdges
	(GL2 gl, Point p1, Point p2, Point p3, Point p4, Point p5, Point p6, Point p7, 
			Point p8, Color edgeColor, int edgeWeight) {
		gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_LINE);
		gl.glLineWidth((float) edgeWeight);
		gl.glPolygonOffset(0.0f, -1.0f);
		gl.glBegin(GL2.GL_LINE_STRIP);
			gl.glColor3d((edgeColor.getRed() / 255f),(edgeColor.getGreen() / 255f),
					(edgeColor.getBlue() / 255f));
			//gl.glVertex2d((float) p1.getX(), (float) p1.getY());
			gl.glVertex3f((float) p2.getX(), (float) p2.getY(), (float) p2.getZ());
			gl.glVertex3f((float) p3.getX(), (float) p3.getY(), (float) p3.getZ());
			gl.glVertex3f((float) p4.getX(), (float) p4.getY(), (float) p4.getZ());
			gl.glVertex3f((float) p5.getX(), (float) p5.getY(), (float) p5.getZ());
			gl.glVertex3f((float) p6.getX(), (float) p6.getY(), (float) p6.getZ());
			gl.glVertex3f((float) p7.getX(), (float) p7.getY(), (float) p7.getZ());
			gl.glVertex3f((float) p8.getX(), (float) p8.getY(), (float) p8.getZ());
		gl.glEnd();
	}
	


/***********************************************************************/
/***************************** CONSTRUCTOR *****************************/
/***********************************************************************/
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
	
	
	
/***********************************************************************/
/******************************** DISPLAY ******************************/
/***********************************************************************/
	public void display(GLAutoDrawable drawable) {
		
	    gl = drawable.getGL().getGL2();
		   
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		gl.glClear(GL.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		//gl.glTranslatef(0.0f, 0.0f, 0.0f);

		
		drawShape(drawable);
	}

	
	
/***********************************************************************/
/******************************* DRAWSHAPE *****************************/
/***********************************************************************/	
	public void drawShape(GLAutoDrawable drawable) {
		gl.glLoadIdentity();
		gl.glTranslatef(tx, ty, 0.0f);
		//gl.glScalef(0.5f, 0.5f, 0.5f);
		

   		pyramid(drawable);
		cube(drawable);
		hexprism(drawable);
		triprism(drawable);
		sphere(drawable);
		cylinder(drawable);
		
		rotateT += 2.0f;
			
	}
	
	
/***********************************************************************/
/***************************** DRAW SHAPES *****************************/
/***********************************************************************/		
	
	/////////////////////////////////////////////
	public void pyramid (GLAutoDrawable drawable) 
	/////////////////////////////////////////////
	{
		gl = drawable.getGL().getGL2();
		   	
		gl.glPushMatrix();
			
			//scale
			gl.glScalef(0.5f, 0.5f, 0.5f);
		   
			//translation
			gl.glTranslatef(3.5f, 3.5f, 0.0f);
		   
			//rotation
	   		gl.glRotatef(rotateT, 0.0f, 1.0f, 0.0f);
	   		
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
	   
	   
	   
	/////////////////////////////////////////////	
	public void triprism (GLAutoDrawable gLDrawable) 
	/////////////////////////////////////////////
	{
		gl = gLDrawable.getGL().getGL2();
	   	
	   	gl.glPushMatrix();
	   	
			//scale
			gl.glScalef(0.5f, 0.5f, 0.5f);
   	
			//translation
			gl.glTranslatef(-3.5f, 3.5f, 0.0f);
   	
			//rotation
			gl.glRotatef(rotateT, 1.0f, 0.0f, 0.0f);
   		

	   		//vertices
	   		Point top1 = new Point(0.0, 0.5, 1.0);
	   		Point top2 = new Point(0.0, 0.5, -1.0);
	   		Point q1 = new Point(-1.0,-1.0, 1.0);
	   		Point q2 = new Point( 1.0,-1.0, 1.0);
	   		Point q3 = new Point( 1.0,-1.0, -1.0);
	   		Point q4 = new Point(-1.0,-1.0, -1.0);
	   		
	   		//create shape
	   		drawTriangle(gl, q1, q2, top1, SILVER, BLUE, 2);  //front
	   		drawTriangle(gl, q3, q4, top2, RED, BLUE, 2); // back
	   		drawSquare(gl, top2, q4, q1, top1, MAROON, BLUE, 2); //right
	   		drawSquare(gl, top1, q2, q3, top2, LIME, BLUE, 2); //left
	   		drawSquare(gl, q2, q1, q4, q3, BLUE, BLUE, 2); //bottom

	   	gl.glPopMatrix();
	}


	/////////////////////////////////////////////		
	public void cube (GLAutoDrawable gLDrawable)
	/////////////////////////////////////////////
	{
		final GL2 gl = gLDrawable.getGL().getGL2();
	   	
	   	gl.glPushMatrix();
	   	
   			//scale
   			gl.glScalef(0.5f, 0.5f, 0.5f);
	   	
   			//translation
   			gl.glTranslatef(-3.5f, 0.0f, 0.0f);
	   	
	   		//rotation
	   		gl.glRotatef(rotateT, 0.0f, 1.0f, 0.0f);
	   		

	   		//vertices
	   		Point b_q1 = new Point(-1.0,-1.0, 1.0);
	   		Point b_q2 = new Point( 1.0,-1.0, 1.0);
	   		Point b_q3 = new Point( 1.0,-1.0, -1.0);
	   		Point b_q4 = new Point(-1.0,-1.0, -1.0);
	   		
	   		Point t_q1 = new Point(-1.0, 1.0, 1.0);
	   		Point t_q2 = new Point( 1.0, 1.0, 1.0);
	   		Point t_q3 = new Point( 1.0, 1.0, -1.0);
	   		Point t_q4 = new Point(-1.0, 1.0, -1.0);
	   		
	   		//create shape
	   		drawSquare(gl, b_q2, b_q1, b_q4, b_q3, BLUE, BLACK, 3);
	   		drawSquare(gl, t_q2, t_q1, b_q1, b_q2, RED, BLACK, 3);
	   		drawSquare(gl, t_q3, t_q2, b_q2, b_q3, YELLOW, BLACK, 3);
	   		drawSquare(gl, t_q4, t_q3, b_q3, b_q4, GREEN, BLACK, 3);
	   		drawSquare(gl, t_q1, t_q4, b_q4, b_q1, MAROON, BLACK, 3);
	   		drawSquare(gl, t_q1, t_q2, t_q3, t_q4, FUCHSIA, BLACK, 3);
	   		
	   	gl.glPopMatrix(); 
	}


	/////////////////////////////////////////////	
	public void hexprism (GLAutoDrawable gLDrawable) 
	/////////////////////////////////////////////
	{
		final GL2 gl = gLDrawable.getGL().getGL2();
	   	
	   	gl.glPushMatrix();
	   	
	   		//scale
	   		gl.glScalef(0.5f, 0.5f, 0.5f);
	
	   		//translation
	   		gl.glTranslatef(3.5f, 0.0f, 0.0f);
	
	   		//rotation
	   		gl.glRotatef(rotateT, 1.0f, 1.0f, 0.0f);
	   	
	   		
	   		//vertices
	   		Point h_0 = new Point( 0.0, 0.0, 0.0);
	   		Point h_1 = new Point( 0.5, 0.9, 0.5);
	   		Point h_2 = new Point(-0.5, 0.9, 0.5);
	   		Point h_3 = new Point(-1.0, 0.0, 0.5);
	   		Point h_4 = new Point(-0.5, -0.9, 0.5);
	   		Point h_5 = new Point( 0.5, -0.9, 0.5);
	   		Point h_6 = new Point( 1.0, 0.0, 0.5);
	   		
	   		Point hb_0 = new Point( 0.0, 0.0, -0.5);
	   		Point hb_1 = new Point( 0.5, 0.9, -0.5);
	   		Point hb_2 = new Point(-0.5, 0.9, -0.5);
	   		Point hb_3 = new Point(-1.0, 0.0, -0.5);
	   		Point hb_4 = new Point(-0.5, -0.9, -0.5);
	   		Point hb_5 = new Point( 0.5, -0.9, -0.5);
	   		Point hb_6 = new Point( 1.0, 0.0, -0.5);
	   		
	   		//create shape
	   		drawHexagon(gl, h_0, h_1, h_2, h_3, h_4, h_5, h_6, h_1, BLUE, SILVER, 1); //front
	   		drawHexagon(gl, hb_0, hb_6, hb_5, hb_4, hb_3, hb_2, hb_1, hb_6, GREEN, SILVER, 1); //back
	   		drawSquare(gl, h_1, hb_1, hb_2, h_2, LIME, SILVER, 1); //top
	   		drawSquare(gl, hb_1, h_1, h_6, hb_6, PURPLE, SILVER, 1); //top right
	   		drawSquare(gl, h_5, hb_5, hb_6, h_6, FUCHSIA, SILVER, 1); //bottom right
	   		drawSquare(gl, h_4, hb_4, hb_5, h_5, RED, SILVER, 1); //base
	   		drawSquare(gl, h_3, hb_3, hb_4, h_4, ORANGE, SILVER, 1); //bottom left
	   		drawSquare(gl, h_2, hb_2, hb_3, h_3, YELLOW, SILVER, 1); //top left

	   	gl.glPopMatrix();
	}


	/////////////////////////////////////////////
	public void sphere (GLAutoDrawable gLDrawable) 
	/////////////////////////////////////////////
	{
		final GL2 gl = gLDrawable.getGL().getGL2();
	   	gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);
	   	
	   	gl.glPushMatrix();
	   	
	   		//scale
	   		gl.glScalef(0.5f, 0.5f, 0.5f);

	   		//translation
	   		gl.glTranslatef(3.5f, -3.5f, -0.5f);
	   		
	   		//rotation
	   		gl.glRotatef(rotateT, 1.0f, 0.0f, 0.0f);
	   		
	   		
	   		//lighting and shading
	   		gl.glEnable(GL2.GL_LIGHTING);
	   		gl.glEnable(GL2.GL_LIGHT0);
	   		gl.glEnable(GL2.GL_DEPTH_TEST);
	   		gl.glShadeModel(GL2.GL_FLAT); //easier to see the rotation, but kind of ugly :\
	   		
	   		//create shape
	   		gl.glEnable(GL2.GL_COLOR_MATERIAL);
	   		gl.glColor4f( 1f, 0f, 0f, 1f );
	   		glut.glutSolidSphere (1.0, 35, 20);
	   		
	   		//disable lighting (so that this lighting is only for the sphere)
	   		gl.glDisable(GL2.GL_LIGHTING);
	   		gl.glDisable(GL2.GL_LIGHT0);

	   	gl.glPopMatrix(); 
	}


	////////////////////////////////////////////////		
	public void cylinder (GLAutoDrawable gLDrawable) 
	////////////////////////////////////////////////
	{
		final GL2 gl = gLDrawable.getGL().getGL2();
	   	
	   	gl.glPushMatrix();
	   	
	   		//scale
	   		gl.glScalef(0.5f, 0.5f, 0.5f);

	   		//translation
	   		gl.glTranslatef(-3.5f, -4.0f, 0.0f);
	   		
	   		//rotation
	   		gl.glRotatef(rotateT, 0.0f, 1.0f, 1.0f);
	   		
	   		
	   		//lighting and shading
	   		gl.glEnable(GL2.GL_LIGHTING);
	   		gl.glEnable(GL2.GL_LIGHT0);
	   		gl.glEnable(GL2.GL_DEPTH_TEST);
	   		gl.glShadeModel(GL2.GL_SMOOTH);
	   		
	   		//create shape
	   		gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);
	   		
	   		gl.glEnable(GL2.GL_COLOR_MATERIAL);
	   		GLUquadric quad = glu.gluNewQuadric();
	   		gl.glColor4f( 0f, 1f, 1f, 1f );
	   		glu.gluCylinder(quad, 1.0, 1.0, 1.5, 32, 1); //hollow cylinder
	   		
	   		gl.glTranslatef(0.0f, 0.0f, 1.5f); //cap 1
	   		glu.gluDisk(quad, 0.0, 1.0, 32, 1);
	   		
	   		gl.glTranslatef(0.0f, 0.0f, -1.5f); //cap 2
	   		glu.gluDisk(quad, 0.0, 1.0, 32, 1);
	   		
	   		
	   		//disable lighting (so that this lighting is only for the cylinder)
	   		gl.glDisable(GL2.GL_LIGHTING);
	   		gl.glDisable(GL2.GL_LIGHT0);

	   	gl.glPopMatrix(); 
	}

	
/***********************************************************************/
/******************************** INIT *********************************/
/***********************************************************************/		
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


/***********************************************************************/
/******************************* RESHAPE *******************************/
/***********************************************************************/	
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
  		if (width <= height) 
  			gl.glOrtho(-2.5, 2.5, -2.5 * (float) height / (float) width, 
  					2.5 * (float) height / (float) width, -20.0, 20.0);
  		else 
  			gl.glOrtho(-2.5 * (float) width / (float) height, 
  					2.5 * (float) width / (float) height, -2.5, 2.5, -20.0, 20.0);
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL2.GL_MODELVIEW);		
	}


/***********************************************************************/
/***************************** MOUSE INPUT *****************************/
/***********************************************************************/		
	//mouse click
	public void mousePressed(MouseEvent mouse) {
		mouseX = mouse.getX();
		mouseY = mouse.getY();
		mouseButton = mouse.getButton();
		canvas.display();
	}

	//mouse release
	public void mouseReleased(MouseEvent mouse) {
		clickedOnShape = false;
		canvas.display();
	}

	//mouse drag
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
	
	//unused
	public void mouseClicked(MouseEvent mouse) { }
	public void mouseEntered(MouseEvent mouse) { }
	public void mouseExited(MouseEvent mouse) {	}
	public void mouseMoved(MouseEvent mouse) { }
	
	

/***********************************************************************/
/*************************** KEYBOARD INPUT ****************************/
/***********************************************************************/
	//unused
	public void keyPressed(KeyEvent key) { }
	public void keyTyped(KeyEvent key) { }
	public void keyReleased(KeyEvent key) { }
	public void actionPerformed(ActionEvent action) { }

	
	
	
/***********************************************************************/
/******************************** MAIN *********************************/
/***********************************************************************/	
	public static void main(String args[]) 
	{
		//create frame
		final int WINDOW_WIDTH = 900;
		final int WINDOW_HEIGHT = 700;
		final String WINDOW_TITLE = "Independently Rotating JOGL Shapes";
		
		JFrame frame = new JFrame();
		frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		frame.setTitle(WINDOW_TITLE);
		
		
		//add IndependentShapes2 object to frame
		final IndependentShapes2 joglMain = new IndependentShapes2();
		frame.setContentPane(joglMain);
		
		
		//stops animation on close--> force quits
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
		
		
		// start the animation loop
		joglMain.animator.start();
		
		frame.setVisible(true);
	}
	

	
/***********************************************************************/
/****************************** (unused) *******************************/
/***********************************************************************/	
	public void dispose(GLAutoDrawable drawable) { }
		
	public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, 
			boolean deviceChanged) { }

}
