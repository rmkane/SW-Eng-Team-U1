import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;




public class ResizePanel implements ListSelectionListener  {
	
	public ResizePanel(JPanel panel)
	{
	    panel.setLayout(new BorderLayout());
	    

	    JLabel resize_title = new JLabel("R E S I Z E");
	    resize_title.setOpaque(true);
	    resize_title.setBackground(Color.lightGray);
	    

	    panel.add(resize_title, BorderLayout.PAGE_START);
	    

	    JPanel leftPanel = new JPanel();
	    JPanel rightPanel = new JPanel();
	    JPanel bottomPanel = new JPanel();
	    leftPanel.setLayout(new GridLayout(3, 2, 5, 15));
	    rightPanel.setLayout(new GridLayout(2, 1));
	    
	    panel.add(leftPanel, BorderLayout.LINE_START);
	    panel.add(rightPanel, BorderLayout.LINE_END);
	    panel.add(bottomPanel, BorderLayout.PAGE_END);
	    leftPanel.setBorder(new EmptyBorder(10, 3, 10, 3) );
	    rightPanel.setBorder(new EmptyBorder(10, 10, 10, 10) );
	    bottomPanel.setBorder(new EmptyBorder(0, 10, 10, 10) );
	    
	    
	    JTextField height = new JTextField();
	    JTextField width = new JTextField();
	    JTextField depth = new JTextField();
	    

	    leftPanel.add(new JLabel("Height:"));
	    leftPanel.add(height);
	    leftPanel.add(new JLabel("Width:"));
	    leftPanel.add(width);
	    leftPanel.add(new JLabel("Depth:"));
	    leftPanel.add(depth);
	    
	    
	    

	    
	    JCheckBox maintainRatio = new JCheckBox("Maintain Aspect Ratio");
	    maintainRatio.setFont(new Font("sansserif",Font.PLAIN,11));
	    bottomPanel.add(maintainRatio);



	}
	

	public static void main(String[] args)
	{
		JFrame frame = new JFrame("3D GUI");

		frame.setSize(150, 200);

		
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
	    
		new ResizePanel(panel);
		
		

		frame.setVisible(true);
	}


	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
