import java.awt.Color;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLDrawableFactory;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;

public class JoglTest {
	public static void main(String[] args) {
		Frame frame = new Frame("Hello World");
		
		// http://www.cs.umd.edu/~meesh/kmconroy/JOGLTutorial/
		// Why doesn't this work!?
		//GLCanvas canvas = GLDrawableFactory.getFactory().createGLCanvas(new GLCapabilities());
	    //frame.add(canvas);
	    
	    frame.setSize(300, 300);
	    frame.setBackground(Color.WHITE);

	    frame.addWindowListener(new WindowAdapter()
	    {
	    	@Override
	        public void windowClosing(WindowEvent e)
	        {
	            System.exit(0);
	        }
	    });
	    
	    frame.setVisible(true);
	}
}