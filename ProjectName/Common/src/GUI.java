import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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

public class GUI extends JFrame {
	private GLCanvas canvas = new GLCanvas();
	private Animator animator = new Animator(canvas);

	// Main panel
	private JPanel mainPanel = new JPanel();

	// Menubar
	private JMenuBar menu = new JMenuBar();
	private JMenu file = new JMenu("File");
	private JMenu edit = new JMenu("Edit");
	private JMenu help = new JMenu("Help");

	// Shapes
	private JPanel shapesPanel = new JPanel();
	private JButton circle = new JButton("Circle");
	private JButton square = new JButton("Square");
	private JButton triangle = new JButton("Triangle");
	private JButton hexagon = new JButton("Hexagon");

	// Drawing area
	private GLJPanel viewer = new GLJPanel();

	// Commands
	private JPanel commandsPanel = new JPanel();
	private JButton move = new JButton("Move");
	private JButton scale = new JButton("Scale");
	private JButton rotate = new JButton("Rotate");
	private JButton zoom = new JButton("Zoom");

	private final int CENTER = GridBagConstraints.CENTER;

	public GUI() {
		super("Java Open-JL Test");

		addComponents();

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setSize(800, 560);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		//animator.start();
		canvas.requestFocus();
	}

	private void addComponents() {

		// Set up menu
		this.setJMenuBar(menu);
		menu.add(file);
		menu.add(edit);
		menu.add(help);
		
		// Set up panel
		this.add(mainPanel);
		mainPanel.setLayout(new GridBagLayout());
		addComponent(mainPanel, shapesPanel, 0, 0, 1, 1, CENTER);
		addComponent(mainPanel, viewer, 1, 0, 1, 1, CENTER);
		addComponent(mainPanel, commandsPanel, 2, 0, 1, 1, CENTER);
		
		// Shapes Panel
		shapesPanel.setLayout(new GridLayout(4, 1));
		shapesPanel.add(circle);
		shapesPanel.add(square);
		shapesPanel.add(triangle);
		shapesPanel.add(hexagon);
		
		// Canvas
		viewer.add(canvas); // Shows up if I add the canvas to the root pane
							// "this.add(canvas)"
		
		// Commands Panel
		commandsPanel.setLayout(new GridLayout(4, 1));
		commandsPanel.add(move);
		commandsPanel.add(scale);
		commandsPanel.add(rotate);
		commandsPanel.add(zoom);

		
		// Since JOGLTest is a 
		canvas.addGLEventListener(new IndependentShapes());
		this.add(canvas);

	}

	/** Adds a component to a panel using the GridBagConstraints layout manager. */
	private void addComponent(JPanel panel, JComponent component, int xPos,
			int yPos, int width, int height, int align) {
		GridBagConstraints grid = new GridBagConstraints();
		grid.gridx = xPos; // Column.
		grid.gridy = yPos; // Row.
		grid.gridwidth = width; // Width.
		grid.gridheight = height; // Height.
		grid.anchor = align; // Position
		grid.insets = new Insets(3, 3, 3, 3); // Add space between components.
		grid.fill = GridBagConstraints.NONE; // Resize component.
		panel.add(component, grid); // Adds component to the grid to the panel.
	} // End addComponent.

	public static void main(String[] args) {
		new GUI();
	}
}