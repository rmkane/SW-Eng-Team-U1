/***********************************************************************
 * -----------------------
 * BorderLayoutDemo.java
 * -----------------------
 * Team U1
 * 3D Manipulator GUI window
 * 
 * (Work in progress)
 * 
 ***********************************************************************/



import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.media.opengl.awt.GLCanvas;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class BorderLayoutDemo extends JPanel implements MouseListener, MouseMotionListener  {

	private static final long serialVersionUID = 1L;

	private Scene s;
	private GLCanvas c;
	private JFrame frame;
	
	// Menu
	private JMenuBar menubar;
	private JMenu file, edit, help;
	private JMenuItem save, load, exit, blank, about;
	
	// Panels
	private JPanel mainPanel, rightToolbar, currentShapes, rotatePane,
			resizePane, aestheticsPane, centerPanel;
	
	// Shapes Toolbar
	private JToolBar shapesToolbar;
	private JButton cube_b, triprism_b, pyramid_b, cylinder_b, sphere_b,
			hexprism_b, line_b;
	private JTextArea logText;
	private JLabel statusbar;

	
	public BorderLayoutDemo() {
		s = new Scene();
		c = s.getCanvas();
		c.addGLEventListener(s);
		c.addMouseMotionListener(this);

		init();
	}

	public final void init() {

		frame = new JFrame("3D GUI");
		frame.setSize(900, 700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		menubar = new JMenuBar();
		frame.setJMenuBar(menubar);

		// File
		file = new JMenu("File");// Items under File
		edit = new JMenu("Edit");// Items under Edit
		help = new JMenu("Help");

		menubar.add(file);
		menubar.add(edit);
		menubar.add(Box.createHorizontalGlue()); // adheres Help menu to right side
		menubar.add(help);

		save = new JMenuItem("Save");
		load = new JMenuItem("Load");
		exit = new JMenuItem("Exit");

		file.add(save);
		file.add(load);
		file.add(exit);

		
		// Edit
		blank = new JMenuItem("Blank Button");
		edit.add(blank);
		

		// Help
		about = new JMenuItem("About");
		help.add(about);
		

		// Adding the function of the action to the button
		exit.addActionListener(new ExitAction());
		about.addActionListener(new AboutAction());
		blank.addActionListener(new BlankAction());
		save.addActionListener(new SaveAction());
		load.addActionListener(new LoadAction());

		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		frame.add(mainPanel);

		
		// creates left-hand toolbar
		shapesToolbar = new JToolBar(JToolBar.VERTICAL);
		shapesToolbar.setFloatable(false);
		mainPanel.add(shapesToolbar, BorderLayout.LINE_START);

		shapesToolbar.setLayout(new GridLayout(7, 1, 0, 10));
		shapesToolbar.setBorder(LineBorder.createGrayLineBorder());

		
		// creates buttons for each shape
		cube_b = new JButton("[cube]");
		triprism_b = new JButton("[tri prism]");
		pyramid_b = new JButton("[pyramid]");
		cylinder_b = new JButton("[cylinder]");
		sphere_b = new JButton("[sphere]");
		hexprism_b = new JButton("[hex prism]");
		line_b = new JButton("[line]");

		
		// adds buttons to left-hand toolbar
		shapesToolbar.add(cube_b);
		shapesToolbar.add(triprism_b);
		shapesToolbar.add(pyramid_b);
		shapesToolbar.add(cylinder_b);
		shapesToolbar.add(sphere_b);
		shapesToolbar.add(hexprism_b);
		shapesToolbar.add(line_b);

		
		// creates right-hand toolbar
		rightToolbar = new JPanel();
		rightToolbar.setPreferredSize(new Dimension(150, 0));
		// rightToolbar.setLayout(new GridLayout(4,1,0,15));
		rightToolbar.setLayout(new BoxLayout(rightToolbar, BoxLayout.Y_AXIS));
		rightToolbar.setBorder(LineBorder.createGrayLineBorder());

		mainPanel.add(rightToolbar, BorderLayout.LINE_END);

		
        //current shapes panel
        JPanel currentShapes = new JPanel();
        currentShapes.setPreferredSize(new Dimension(150, 100));
        rightToolbar.add(currentShapes);
        rightToolbar.add(Box.createVerticalGlue());
        currentShapes.setBorder(LineBorder.createGrayLineBorder());
        
        new CurrentShapesPanel(currentShapes);
        
        
        //rotation panel
        JPanel rotatePane = new JPanel();
        rotatePane.setMaximumSize(new Dimension(150, 190));
        rotatePane.setPreferredSize(new Dimension(150, 190));
        rightToolbar.add(rotatePane);
        rotatePane.setBorder(new EmptyBorder(0, 0, 0, 0) );
        rotatePane.setBorder(LineBorder.createGrayLineBorder());
        rightToolbar.add(Box.createVerticalGlue());
	    
		new RotatePanel(rotatePane);
        

        
        //resize panel
        JPanel resizePane = new JPanel();
        resizePane.setPreferredSize(new Dimension(100, 100));
        rightToolbar.add(resizePane);
        resizePane.setBorder(LineBorder.createGrayLineBorder());
        
        new ResizePanel(resizePane);
        
        
        
        //aesthetics panel
        JPanel aestheticsPane = new JPanel();
        aestheticsPane.setMaximumSize(new Dimension(150, 110));
        aestheticsPane.setPreferredSize(new Dimension(150, 110));
        rightToolbar.add(aestheticsPane);
        aestheticsPane.setBorder(LineBorder.createGrayLineBorder());
        
        new AestheticsPanel(aestheticsPane);
        
        

		// creates center panel
		centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());

		mainPanel.add(centerPanel, BorderLayout.CENTER);

		centerPanel.add(c, BorderLayout.CENTER);

		frame.setVisible(true);

		logText = new JTextArea(); // **LOGGER PANEL**
		logText.setBorder(LineBorder.createGrayLineBorder());
		logText.setPreferredSize(new Dimension(0, 150));


		
		centerPanel.add(logText, BorderLayout.PAGE_END);

		
		statusbar = new JLabel();
		statusbar.setText(" Cursor Position:  |  Selected: x  |  Total Shapes: x");

		statusbar.setPreferredSize(new Dimension(-1, 22));
		statusbar.setBorder(LineBorder.createGrayLineBorder());
		mainPanel.add(statusbar, BorderLayout.PAGE_END);

	}

	
	

	public void actionPerformed(ActionEvent e) { }

	public static void main(String[] args) {
		BorderLayoutDemo ex = new BorderLayoutDemo();
		ex.setVisible(true);
	}

	class SaveAction implements ActionListener {// Action For Save goes here
		// NOT COMPLETE
		public void actionPerformed(ActionEvent e) {
			JFrame saveFrame = new JFrame("Save");
			saveFrame.setVisible(true);
			saveFrame.setSize(200, 200);
			JLabel label = new JLabel("YOU CLICKED SAVE");
			JPanel panel = new JPanel();
			saveFrame.add(panel);
			panel.add(label);
		}
	}

	class LoadAction implements ActionListener {// Action For Save goes here
		// NOT COMPLETE
		public void actionPerformed(ActionEvent e) {

			JFileChooser chooser = new JFileChooser();
			CustomFileFilter filter = new CustomFileFilter();
			filter.addExtension("log"); // Only choose text files
			filter.setDescription("Log Files");
			chooser.setFileFilter(filter);
			
			int returnVal = chooser.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				System.out.println("You chose to open this file: "
						+ chooser.getSelectedFile().getName());
			}
		}
	}

	class ExitAction implements ActionListener {// Action For Exit
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	class AboutAction implements ActionListener {// Action For About
		public void actionPerformed(ActionEvent e) {
			JFrame aboutFrame = new JFrame("About");
			aboutFrame.setVisible(true);
			aboutFrame.setSize(400, 250);

			JTextArea aboutText = new JTextArea(
					"This application was created by:"
							+ "\n\nJennifer Hill\nRyan Kane\nDorothy Kirlew\n" +
							"Donald Shaner\nand Sean Weber");
			Font JTextFont = new Font("Verdana", Font.BOLD, 12);
			aboutText.setFont(JTextFont);

			aboutText.setPreferredSize(new Dimension(380, 250));
			aboutText.setLineWrap(true);
			aboutText.setWrapStyleWord(true);

			JPanel panel = new JPanel();
			aboutFrame.add(panel);
			panel.add(aboutText);
		}
	}

	class BlankAction implements ActionListener {// Blank action
		public void actionPerformed(ActionEvent e) {
			JFrame blankFrame = new JFrame("Blank");
			blankFrame.setVisible(true);
			blankFrame.setSize(200, 200);
			JLabel label = new JLabel("Blank");
			JPanel panel = new JPanel();
			blankFrame.add(panel);
			panel.add(label);
		}
	}

	public JLabel getStatusbar() {
		return statusbar;
	}

	public void setStatusbar(JLabel statusbar) {
		this.statusbar = statusbar;
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		statusbar.setText(" Cursor Position: " + s.getCurPos() + "  |  Selected: x  |  Total Shapes: x");
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}	
		
	
	public void mouseMoved(MouseEvent e)
	{
		//System.out.println("LOL HI");
		//System.out.println(s.getCurPos());
		statusbar.setText(" Cursor Position: " + s.getCurPos() + "  |  Selected: x  |  Total Shapes: x");
	}
	
}