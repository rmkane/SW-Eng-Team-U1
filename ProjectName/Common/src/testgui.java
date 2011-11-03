	import java.awt.GridBagConstraints;
	import java.awt.GridBagLayout;
	import java.awt.GridLayout;
	import java.awt.BorderLayout;
	import java.awt.Insets;
	import javax.media.opengl.awt.GLCanvas;
	import javax.media.opengl.awt.GLJPanel;
	import javax.swing.JButton;
	import javax.swing.JComponent;
	import javax.swing.JFrame;
	import javax.swing.JMenu;
	import javax.swing.JMenuBar;
	import javax.swing.JPanel;
	import com.jogamp.opengl.util.Animator;
	public class testgui extends JFrame
	{
		public static void main( String args[] )
		{
			GUITest Guitest = new GUITest();
			Guitest.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
			Guitest.setSize( 500,250 );  //frame initial size
			Guitest.setVisible (true); //display frame
		}//end main
	}//end class guitest