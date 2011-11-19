import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class test extends JFrame {
    JButton button_1;
    JButton button_2;
    JButton button_3;
    JButton button_4;
    JButton button_5;
    JButton button_6;
    Canvas canvas_1;
    TextArea textarea_1;
    Scrollbar scrollbar_v_1;

    public test() {
        testLayout customLayout = new testLayout();

        getContentPane().setFont(new Font("Helvetica", Font.PLAIN, 12));
        getContentPane().setLayout(customLayout);

        button_1 = new JButton("button_1");
        getContentPane().add(button_1);

        button_2 = new JButton("button_2");
        getContentPane().add(button_2);

        button_3 = new JButton("button_3");
        getContentPane().add(button_3);

        button_4 = new JButton("button_4");
        getContentPane().add(button_4);

        button_5 = new JButton("button_5");
        getContentPane().add(button_5);

        button_6 = new JButton("button_6");
        getContentPane().add(button_6);

        canvas_1 = new Canvas();
        getContentPane().add(canvas_1);

        textarea_1 = new TextArea("textarea_1");
        getContentPane().add(textarea_1);

        scrollbar_v_1 = new Scrollbar(Scrollbar.VERTICAL);
        getContentPane().add(scrollbar_v_1);

        setSize(getPreferredSize());

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String args[]) {
        test window = new test();

        window.setTitle("test");
        window.pack();
        window.show();
    }
}

class testLayout implements LayoutManager {

    public testLayout() {
    }

    public void addLayoutComponent(String name, Component comp) {
    }

    public void removeLayoutComponent(Component comp) {
    }

    public Dimension preferredLayoutSize(Container parent) {
        Dimension dim = new Dimension(0, 0);

        Insets insets = parent.getInsets();
        dim.width = 1295 + insets.left + insets.right;
        dim.height = 745 + insets.top + insets.bottom;

        return dim;
    }

    public Dimension minimumLayoutSize(Container parent) {
        Dimension dim = new Dimension(0, 0);
        return dim;
    }

    public void layoutContainer(Container parent) {
        Insets insets = parent.getInsets();

        Component c;
        c = parent.getComponent(0);
        if (c.isVisible()) {c.setBounds(insets.left+56,insets.top+144,72,24);}
        c = parent.getComponent(1);
        if (c.isVisible()) {c.setBounds(insets.left+56,insets.top+192,72,24);}
        c = parent.getComponent(2);
        if (c.isVisible()) {c.setBounds(insets.left+56,insets.top+232,72,24);}
        c = parent.getComponent(3);
        if (c.isVisible()) {c.setBounds(insets.left+56,insets.top+272,72,24);}
        c = parent.getComponent(4);
        if (c.isVisible()) {c.setBounds(insets.left+56,insets.top+312,72,24);}
        c = parent.getComponent(5);
        if (c.isVisible()) {c.setBounds(insets.left+56,insets.top+104,72,24);}
        c = parent.getComponent(6);
        if (c.isVisible()) {c.setBounds(insets.left+144,insets.top+104,456,240);}
        c = parent.getComponent(7);
        if (c.isVisible()) {c.setBounds(insets.left+144,insets.top+352,456,104);}
        c = parent.getComponent(8);
        if (c.isVisible()) {c.setBounds(insets.left+600,insets.top+352,24,104);}
    }
}
