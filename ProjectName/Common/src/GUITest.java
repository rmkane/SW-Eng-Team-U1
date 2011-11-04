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
import javax.swing.*;
import com.jogamp.opengl.util.Animator;
import java.awt.event.*;

public class GUITest extends JFrame {
	// Comment
	public static void main(String[] args) {
		JFrame frame = new JFrame("3D GUI");
		frame.setVisible(true);
		frame.setSize(500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menubar = new JMenuBar();
		frame.setJMenuBar(menubar);

		JMenu file = new JMenu("File");// Items under File
		menubar.add(file);
		JMenuItem save = new JMenuItem("Save");
		file.add(save);
		JMenuItem load = new JMenuItem("Load");
		file.add(load);
		JMenuItem exit = new JMenuItem("Exit");
		file.add(exit);

		JMenu edit = new JMenu("Edit");// Items under Edit
		menubar.add(edit);
		JMenuItem blank = new JMenuItem("Blank Button");
		edit.add(blank);

		JMenu help = new JMenu("Help");
		menubar.add(help);
		JMenuItem about = new JMenuItem("About");
		help.add(about);

		class saveaction implements ActionListener {// Action For Save goes here
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
		class loadaction implements ActionListener {// Action For Save goes here
													// NOT COMPLETE
			public void actionPerformed(ActionEvent e) {
				/*JFrame loadFrame = new JFrame("Load");
				loadFrame.setVisible(true);
				loadFrame.setSize(200, 200);
				JLabel label = new JLabel("YOU CLICKED LOADE");
				JPanel panel = new JPanel();
				panel.add(label);*/
				
				JFileChooser chooser = new JFileChooser();
				CustomFileFilter filter = new CustomFileFilter();
				filter.addExtension("log"); // Only choose text files
				filter.setDescription("Log Files");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(null);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
				   System.out.println("You chose to open this file: " +
				        chooser.getSelectedFile().getName());
				}
			}
		}

		class exitaction implements ActionListener {// Action For Exit
			public void actionPerformed(ActionEvent e) {
				System.exit(0); // does what the user wants here.
			}
		}

		class aboutaction implements ActionListener {// Action For About
			public void actionPerformed(ActionEvent e) {
				JFrame aboutFrame = new JFrame("About");
				aboutFrame.setVisible(true);
				aboutFrame.setSize(200, 200);
				JLabel label = new JLabel(
						"This application was created by: Jennifer Hill, Ryan Kane, Dorothy Kirlew, Donald Shaner,  and Sean Weber.");
				JPanel panel = new JPanel();
				aboutFrame.add(panel);
				panel.add(label);
			}
		}

		class blankaction implements ActionListener {// Blank action
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

		// Adding the function of the action to the button
		exit.addActionListener(new exitaction());
		about.addActionListener(new aboutaction());
		blank.addActionListener(new blankaction());
		save.addActionListener(new saveaction());
		load.addActionListener(new loadaction());
	}
}
