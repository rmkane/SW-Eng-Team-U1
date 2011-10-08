import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2ES1;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.fixedfunc.GLLightingFunc;
import javax.media.opengl.fixedfunc.GLMatrixFunc;
import javax.media.opengl.glu.GLU;
import com.jogamp.opengl.util.Animator;

// Imported from local libraries

public class JOGLTest implements GLEventListener, KeyListener {
	float rotateT = 0.0f;
	static GLU glu = new GLU();
	static GLCanvas canvas = new GLCanvas();
	static Frame frame = new Frame("Jogl Quad drawing");
	static Animator animator = new Animator(canvas);

	// Color Palette
	private final Color BLACK = new Color(0, 0, 0);
	private final Color RED = new Color(255, 0, 0);
	private final Color GREEN = new Color(0, 255, 0);
	private final Color BLUE = new Color(0, 0, 255);
	private final Color YELLOW = new Color(255, 255, 0);
	private final Color CYAN = new Color(0, 255, 255);
	private final Color FUCHSIA = new Color(255, 0, 255);
	private final Color SILVER = new Color(192, 192, 192);
	private final Color WHITE = new Color(255, 255, 255);

	public JOGLTest() {

	}

	public void drawTriangle(GL2 gl2, Point p1, Point p2, Point p3, Color color) {
		final GL2 gl = gl2.getGL().getGL2();
		gl.glBegin(GL2.GL_TRIANGLES); // Begin Drawing
		gl.glColor3f((float) (color.getRed() / 255),
				(float) (color.getGreen() / 255),
				(float) (color.getBlue() / 255)); // Red
		gl.glVertex3f((float) p1.getX(), (float) p1.getY(), (float) p1.getZ());
		gl.glVertex3f((float) p2.getX(), (float) p2.getY(), (float) p2.getZ());
		gl.glVertex3f((float) p3.getX(), (float) p3.getY(), (float) p3.getZ());
		gl.glEnd(); // Done Drawing
	}

	public void drawSquare(GL2 gl2, Point p1, Point p2, Point p3, Point p4,
			Color color) {
		final GL2 gl = gl2.getGL().getGL2();
		gl.glBegin(GL2.GL_QUADS); // Begin Drawing
		gl.glColor3f((float) (color.getRed() / 255),
				(float) (color.getGreen() / 255),
				(float) (color.getBlue() / 255)); // Red
		gl.glVertex3f((float) p1.getX(), (float) p1.getY(), (float) p1.getZ());
		gl.glVertex3f((float) p2.getX(), (float) p2.getY(), (float) p2.getZ());
		gl.glVertex3f((float) p3.getX(), (float) p3.getY(), (float) p3.getZ());
		gl.glVertex3f((float) p4.getX(), (float) p4.getY(), (float) p4.getZ());
		gl.glEnd(); // Done Drawing
	}

	public void display(GLAutoDrawable gLDrawable) {
		final GL2 gl = gLDrawable.getGL().getGL2();
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		gl.glClear(GL.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		gl.glTranslatef(0.0f, 0.0f, -5.0f);

		// rotate on the three axis
		
		gl.glRotatef(rotateT, 0.0f, 1.0f, 0.0f); // y - pitch
		gl.glRotatef(rotateT, 0.0f, 0.0f, 1.0f); // z - yaw
		gl.glRotatef(rotateT, 1.0f, 0.0f, 0.0f); // x - roll

		// Awesome! [http://www.falloutsoftware.com/tutorials/gl/gl3.htm] -Ryan

		// Square 1
		Point s1_p1 = new Point(1.0, 1.0, 0.0);
		Point s1_p2 = new Point(-1.0, 1.0, 0.0);
		Point s1_p3 = new Point(-1.0, -1.0, 0.0);
		Point s1_p4 = new Point(1.0, -1.0, 0.0);
		Color s1_c = WHITE;
		drawSquare(gl, s1_p1, s1_p2, s1_p3, s1_p4, s1_c);
		
		// Triangle 1
		Point t1_p1 = new Point(1.0, 1.0, 0.0);
		Point t1_p2 = new Point(-1.0, 1.0, 0.0);
		Point t1_p3 = new Point(0.0, 0.0, 1.0);
		Color t1_c = RED;
		drawTriangle(gl, t1_p1, t1_p2, t1_p3, t1_c);

		// Triangle 2
		Point t2_p1 = new Point(-1.0, 1.0, 0.0);
		Point t2_p2 = new Point(-1.0, -1.0, 0.0);
		Point t2_p3 = new Point(0.0, 0.0, 1.0);
		Color t2_c = YELLOW;
		drawTriangle(gl, t2_p1, t2_p2, t2_p3, t2_c);
		
		// Triangle 3
		Point t3_p1 = new Point(-1.0, -1.0, 0.0);
		Point t3_p2 = new Point(1.0, -1.0, 0.0);
		Point t3_p3 = new Point(0.0, 0.0, 1.0);
		Color t3_c = GREEN;
		drawTriangle(gl, t3_p1, t3_p2, t3_p3, t3_c);
		
		// Triangle 4
		Point t4_p1 = new Point(1.0, -1.0, 0.0);
		Point t4_p2 = new Point(1.0, 1.0, 0.0);
		Point t4_p3 = new Point(0.0, 0.0, 1.0);
		Color t4_c = BLUE;
		drawTriangle(gl, t4_p1, t4_p2, t4_p3, t4_c);
		
		/**
		 * // Draw Triangles gl.glBegin(GL2.GL_TRIANGLES); // Begin Larger
		 * Triangle gl.glVertex3f(-1.0f, -0.5f, -4.0f); // Lower left vertex
		 * gl.glColor3f(1.0f, 0.0f, 0.0f); // Red gl.glVertex3f(1.0f, -0.5f,
		 * -4.0f); // Lower right vertex gl.glColor3f(0.0f, 1.0f, 0.0f); //
		 * Green gl.glVertex3f(0.0f, 0.5f, -4.0f); // Upper vertex
		 * gl.glColor3f(1.0f, 0.0f, 1.0f); // Magenta gl.glEnd(); // Done
		 * Drawing Larger Triangle
		 * 
		 * gl.glBegin(GL2.GL_TRIANGLES); // Begin Smaller Triangle
		 * gl.glVertex3f(-0.5f, -1.5f, -4.0f); // Lower left vertex
		 * gl.glColor3f(1.0f, 1.0f, 0.0f); // Yellow gl.glVertex3f(0.5f, -1.5f,
		 * -4.0f); // Lower right vertex gl.glColor3f(0.0f, 1.0f, 1.0f); // Cyan
		 * gl.glVertex3f(0.0f, -1.0f, -4.0f); // Upper vertex gl.glColor3f(0.0f,
		 * 0.0f, 1.0f); // Blue gl.glEnd(); // Done Drawing Smaller Triangle
		 * 
		 * // Draw A Quad gl.glBegin(GL2.GL_QUADS); gl.glVertex3f(-0.5f, 0.5f,
		 * 0.0f); // Top Left gl.glColor3f(1.0f, 0.0f, 0.0f); // Red
		 * gl.glVertex3f(0.5f, 0.5f, 0.0f); // Top Right gl.glColor3f(1.0f,
		 * 1.0f, 0.0f); // Yellow gl.glVertex3f(0.5f, -0.5f, 0.0f); // Bottom
		 * Right gl.glColor3f(0.0f, 1.0f, 0.0f); // Green gl.glVertex3f(-0.5f,
		 * -0.5f, 0.0f); // Bottom Left gl.glColor3f(0.0f, 0.0f, 1.0f); // Blue
		 * gl.glEnd(); // Done Drawing The Quad
		 */
		// increasing rotation for the next iteration
		rotateT += 0.2f;
	}

	public void displayChanged(GLAutoDrawable gLDrawable, boolean modeChanged,
			boolean deviceChanged) {
	}

	public void init(GLAutoDrawable gLDrawable) {
		GL2 gl = gLDrawable.getGL().getGL2();
		gl.glShadeModel(GLLightingFunc.GL_SMOOTH);
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		gl.glClearDepth(1.0f);
		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glDepthFunc(GL.GL_LEQUAL);
		gl.glHint(GL2ES1.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
		((Component) gLDrawable).addKeyListener(this);
	}

	public void reshape(GLAutoDrawable gLDrawable, int x, int y, int width,
			int height) {
		GL2 gl = gLDrawable.getGL().getGL2();
		if (height <= 0) {
			height = 1;
		}
		float h = (float) width / (float) height;
		gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
		gl.glLoadIdentity();
		glu.gluPerspective(50.0f, h, 1.0, 1000.0);
		gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
		gl.glLoadIdentity();
	}

	public void keyPressed(KeyEvent e) {
		// Exit Program
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			exit();
		}
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
		// Increase Rotation speed
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			rotateT += 1f;
		}
		// Decrease Rotation speed
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			rotateT -= 1f;
		}
	}

	public static void exit() {
		animator.stop();
		frame.dispose();
		System.exit(0);
	}

	public static void main(String[] args) {
		canvas.addGLEventListener(new JOGLTest());
		frame.add(canvas);
		frame.setSize(640, 480);
		frame.setUndecorated(true);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});
		frame.setVisible(true);
		animator.start();
		canvas.requestFocus();
	}

	public void dispose(GLAutoDrawable gLDrawable) {
		// do nothing
	}
}