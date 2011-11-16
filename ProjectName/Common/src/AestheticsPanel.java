import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.awt.GLCanvas;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;




public class AestheticsPanel  {
	
	public AestheticsPanel(JPanel panel)
	{
		JButton test = new JButton("Button 1");
		test.setPreferredSize(new Dimension(0, 10));
		
		panel.add(test);
		panel.add(new JButton("2"));
		panel.add(new JLabel(""));  // for empty cell
		panel.add(new JButton("This is button three"));

		
	}
	

	public static void main(String[] args)
	{
		JFrame frame = new JFrame("3D GUI");

		frame.setSize(150, 230);

		
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				new Thread() {
					public void run() 
					{
						System.exit(0);
					}
				}.start();
			}
		});
		
		
		JPanel panel = new JPanel(new GridLayout(2,2,20,40));
	    frame.add(panel);
	    
		new AestheticsPanel(panel);
		
		

		frame.setVisible(true);
	}
	
}
