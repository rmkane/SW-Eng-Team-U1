import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import javax.swing.border.LineBorder;

public class BorderLayoutDemo extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	public BorderLayoutDemo() {

		initUI();
	}

	public final void initUI() {

		JFrame frame = new JFrame("3D GUI");
		// frame.setVisible(true);
		frame.setSize(900, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menubar = new JMenuBar();
		frame.setJMenuBar(menubar);

		// File
		JMenu file = new JMenu("File");// Items under File
		JMenu edit = new JMenu("Edit");// Items under Edit
		JMenu help = new JMenu("Help");

		menubar.add(file);
		menubar.add(edit);
		menubar.add(Box.createHorizontalGlue()); // adheres Help menu to right
													// side
		menubar.add(help);

		JMenuItem save = new JMenuItem("Save");
		JMenuItem load = new JMenuItem("Load");
		JMenuItem exit = new JMenuItem("Exit");

		file.add(save);
		file.add(load);
		file.add(exit);

		// Edit

		JMenuItem blank = new JMenuItem("Blank Button");
		edit.add(blank);

		// Help

		JMenuItem about = new JMenuItem("About");
		help.add(about);

		// Adding the function of the action to the button
		exit.addActionListener(new ExitAction());
		about.addActionListener(new AboutAction());
		blank.addActionListener(new BlankAction());
		save.addActionListener(new SaveAction());
		load.addActionListener(new LoadAction());

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		frame.add(mainPanel);

		// creates left-hand toolbar
		JToolBar shapesToolbar = new JToolBar(JToolBar.VERTICAL);
		shapesToolbar.setFloatable(false);
		mainPanel.add(shapesToolbar, BorderLayout.LINE_START);

		shapesToolbar.setLayout(new GridLayout(7, 1, 0, 10));
		shapesToolbar.setBorder(LineBorder.createGrayLineBorder());

		// creates buttons for each shape
		JButton cube_b = new JButton("[cube]");
		JButton triprism_b = new JButton("[tri prism]");
		JButton pyramid_b = new JButton("[pyramid]");
		JButton cylinder_b = new JButton("[cylinder]");
		JButton sphere_b = new JButton("[sphere]");
		JButton hexprism_b = new JButton("[hex prism]");
		JButton line_b = new JButton("[line]");

		// adds buttons to left-hand toolbar
		shapesToolbar.add(cube_b);
		shapesToolbar.add(triprism_b);
		shapesToolbar.add(pyramid_b);
		shapesToolbar.add(cylinder_b);
		shapesToolbar.add(sphere_b);
		shapesToolbar.add(hexprism_b);
		shapesToolbar.add(line_b);

		// creates right-hand toolbar
		JPanel rightToolbar = new JPanel();
		rightToolbar.setPreferredSize(new Dimension(150, 0));
		// rightToolbar.setLayout(new GridLayout(4,1,0,15));
		rightToolbar.setLayout(new BoxLayout(rightToolbar, BoxLayout.Y_AXIS));
		rightToolbar.setBorder(LineBorder.createGrayLineBorder());

		mainPanel.add(rightToolbar, BorderLayout.LINE_END);

		// current shapes panel
		JPanel currentShapes = new JPanel();
		currentShapes.setPreferredSize(new Dimension(100, 130));
		rightToolbar.add(currentShapes);
		currentShapes.setBorder(LineBorder.createGrayLineBorder());

		new CurrentShapesPanel(currentShapes);

		// rotation panel
		JPanel rotatePane = new JPanel();
		rotatePane.setMaximumSize(new Dimension(150, 110));
		rightToolbar.add(rotatePane);
		rotatePane.setBorder(LineBorder.createGrayLineBorder());
		rightToolbar.add(rotatePane);

		new RotatePanel(rotatePane);

		// resize panel
		JPanel resizePane = new JPanel();
		resizePane.setPreferredSize(new Dimension(100, 100));
		rightToolbar.add(resizePane);
		resizePane.setBorder(LineBorder.createGrayLineBorder());

		// aesthetics panel
		JPanel aestheticsPane = new JPanel();
		aestheticsPane.setPreferredSize(new Dimension(100, 100));
		rightToolbar.add(aestheticsPane);
		aestheticsPane.setBorder(LineBorder.createGrayLineBorder());

		new AestheticsPanel(aestheticsPane);

		// creates center panel
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());

		mainPanel.add(centerPanel, BorderLayout.CENTER);

		// JTextArea canvas = new JTextArea(); // **CANVAS** - just placeholder
		// right now
		// canvas.setBorder(LineBorder.createGrayLineBorder());

		// scene canvas = new scene();

		scene s = new scene();
		GLCanvas c = s.getCanvas();
		c.addGLEventListener(s);

		centerPanel.add(c, BorderLayout.CENTER);

		frame.setVisible(true);

		JTextArea logText = new JTextArea(); // **LOGGER PANEL**
		logText.setBorder(LineBorder.createGrayLineBorder());
		logText.setPreferredSize(new Dimension(0, 150));

		// centerPanel.add(canvas, BorderLayout.CENTER);
		centerPanel.add(logText, BorderLayout.PAGE_END);

		JLabel statusbar = new JLabel(
				" Current Position: (xxx , yyy)  |  Selected: x  |  Total Shapes: x");
		statusbar.setPreferredSize(new Dimension(-1, 22));
		statusbar.setBorder(LineBorder.createGrayLineBorder());
		mainPanel.add(statusbar, BorderLayout.PAGE_END);

		// frame.add(new JTextArea(), BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent ae) {
	}

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
			/*
			 * JFrame loadFrame = new JFrame("Load");
			 * loadFrame.setVisible(true); loadFrame.setSize(200, 200); JLabel
			 * label = new JLabel("YOU CLICKED LOADE"); JPanel panel = new
			 * JPanel(); panel.add(label);
			 */

			JFileChooser chooser = new JFileChooser();
			// CustomFileFilter filter = new CustomFileFilter();
			// filter.addExtension("log"); // Only choose text files
			// filter.setDescription("Log Files");
			// chooser.setFileFilter(filter);
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
							+ "\n\nJennifer Hill\nRyan Kane\nDorothy Kirlew\nDonald Shaner\n"
							+ "and Sean Weber");
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

}