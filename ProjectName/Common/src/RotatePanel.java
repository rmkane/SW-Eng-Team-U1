import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;




public class RotatePanel  {
	
	public RotatePanel(JPanel panel)
	{
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

	    JPanel rotatePanel1 = new JPanel();
	    rotatePanel1.setLayout(new GridBagLayout());
	    panel.add(rotatePanel1);
	    
	    
	    GridBagConstraints c = new GridBagConstraints();
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.insets = new Insets(4, 4, 4, 4); // 5-pixel margins on all sides
	    c.anchor = GridBagConstraints.NORTHWEST;
	    

	    c.gridx = 1;
	    c.gridy = 0;
	    c.gridwidth = 3;
	    c.gridheight = 1;
	    c.weightx = c.weighty = 1.0;
	    
	    
	    JLabel rotate_title = new JLabel("Rotate Shape");
	    rotate_title.setOpaque(true);
	    rotate_title.setBackground(Color.lightGray);
	    rotatePanel1.add(rotate_title, c);

	    

	    c.gridx = 1;
	    c.gridy = 1;
	    c.gridwidth = 3;
	    c.gridheight = 1;
	    c.weightx = c.weighty = 1.0;
	    rotatePanel1.add(new JLabel("Axis of Rotation:"), c);
	    
	    
	    /**********************************
	     -----------CHECK BUTTONS----------
	     **********************************/
        JCheckBox x_cb = new JCheckBox("x");
	    c.gridx = 1;
	    c.gridy = 2;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.weightx = c.weighty = 1.0;
	    rotatePanel1.add(x_cb, c);

	    JCheckBox y_cb = new JCheckBox("y");
	    c.gridx = 2;
	    c.gridy = 2;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.weightx = c.weighty = 1.0;
	    rotatePanel1.add(y_cb, c);
	    
	    JCheckBox z_cb = new JCheckBox("z");
	    c.gridx = 3;
	    c.gridy = 2;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.weightx = c.weighty = 1.0;
	    rotatePanel1.add(z_cb, c);
	    
	    
	    /**********************************
	     ------------BLANK SPACE-----------
	     **********************************/
	    c.gridx = 1;
	    c.gridy = 3;
	    c.gridwidth = 3;
	    c.gridheight = 1;
	    c.weightx = c.weighty = 1.0;
	    rotatePanel1.add( Box.createHorizontalStrut( 10 ), c );
	    rotatePanel1.add( Box.createVerticalStrut( 10 ), c );
	    
	    
	    /**********************************
	     ----------TEXT INPUT BOXES--------
	     **********************************/
	    //number of rotations
	    c.gridx = 1;
	    c.gridy = 4;
	    c.gridwidth = 2;
	    c.gridheight = 1;
	    c.weightx = c.weighty = 1.0;
	    rotatePanel1.add(new JLabel("Num. Rotations:"), c);
	    

        JTextField numRotations_tf = new JTextField();
        JTextField speed = new JTextField();

	    c.gridx = 3;
	    c.gridy = 4;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.weightx = c.weighty = 0.0;
	    rotatePanel1.add(numRotations_tf, c);
	    
	    //speed
	    c.gridx = 1;
	    c.gridy = 5;
	    c.gridwidth = 2;
	    c.gridheight = 1;
	    c.weightx = c.weighty = 0.0;
	    rotatePanel1.add(new JLabel("Speed (1-100):"), c);
	    
	    c.gridx = 3;
	    c.gridy = 5;
	    c.gridwidth = 1;
	    c.gridheight = 1;
	    c.weightx = c.weighty = 0.0;
	    rotatePanel1.add(speed, c);
	    
	    
	    
	    /**********************************
	     ------------BLANK SPACE-----------
	     **********************************/
	    c.gridx = 1;
	    c.gridy = 6;
	    c.gridwidth = 3;
	    c.gridheight = 1;
	    c.weightx = c.weighty = 1.0;
	    rotatePanel1.add( Box.createHorizontalStrut( 8 ), c );
	    rotatePanel1.add( Box.createVerticalStrut( 8 ), c );
	    
	    
	    
	    /**********************************
	     -------START / PAUSE BUTTONS------
	     **********************************/
	    JPanel rotatePanel2 = new JPanel();
	    panel.add(rotatePanel2);
	    
	    JButton start_b = new JButton("start");
	    JButton pause_b = new JButton("pause");
	    start_b.setFont(new Font("sansserif",Font.PLAIN,11));
	    pause_b.setFont(new Font("sansserif",Font.PLAIN,11));
	    
	   
	    rotatePanel2.setLayout(new GridLayout(1,2,7,0));
	    rotatePanel2.setMaximumSize(new Dimension(135, 40));
	    rotatePanel2.add(start_b, c);
	    rotatePanel2.add(pause_b, c);


	}
	

	public static void main(String[] args)
	{
		JFrame frame = new JFrame("3D GUI");

		//frame.setSize(150, 230);

		
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
		
		
	    JPanel panel = new JPanel();
	    frame.add(panel);
	    
		new RotatePanel(panel);
		
		

		frame.setVisible(true);
	}
	
}
