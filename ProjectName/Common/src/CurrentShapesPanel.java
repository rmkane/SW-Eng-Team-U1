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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;




public class CurrentShapesPanel implements ListSelectionListener  {
	
	public CurrentShapesPanel(JPanel panel)
	{
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

	    JPanel currentShapesPanel = new JPanel();
	    panel.add(currentShapesPanel);
	    
	    
	    JLabel shapes_title = new JLabel("Current Shapes");
	    shapes_title.setPreferredSize(new Dimension(132, 17));
	    shapes_title.setOpaque(true);
	    shapes_title.setBackground(Color.lightGray);
	    currentShapesPanel.add(shapes_title);
	    
	    
	    String[] data = {"Shape 1", "Shape 2", "Shape 3", "Shape 4", "Shape 5"};
	    JList list = new JList(data);
	    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setFixedCellWidth(130);
        JScrollPane listScrollPane = new JScrollPane(list);
	    
	    currentShapesPanel.add(list);



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
		
		
	    JPanel panel = new JPanel();
	    frame.add(panel);
	    
		new CurrentShapesPanel(panel);
		
		

		frame.setVisible(true);
	}


	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
