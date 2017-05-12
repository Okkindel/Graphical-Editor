import java.awt.event.MouseAdapter;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.JColorChooser;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.Action;
import java.awt.Dimension;
import java.awt.Color;

/**
 * Class with separate Listener, shows Pop-Up menu.
 * @author Maciej Hajduk
 */
public class PopUpMenu extends MouseAdapter {

    /** Creates JPopupMenu. */
    private JPopupMenu popup = new JPopupMenu();
    /** Is responsible for advanced colors. */
    private Color colour;
    /** Overwrites the main panel. */
    private JPanel frame;

    /** Creates a grid with colors and a label for advanced colors. */
    PopUpMenu() {

        JMenuItem item = new JMenuItem();
        item.setLayout(new GridLayout(5, 5, 1, 1));
        item.setPreferredSize(new Dimension(100, 100));

        JButton setGrey1 = new JButton();
        setGrey1.setBackground(new Color(255 ,255 ,255));
        setGrey1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (PaintingListener.pointer)
                    PaintingListener.colors[PaintingListener.clicked] = new Color(255, 255, 255);
                else
                    frame.setBackground(new Color(255, 255, 255));
            }
        });
        JButton setGrey2 = new JButton();
        setGrey2.setBackground(new Color(192, 192, 192));
        setGrey2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (PaintingListener.pointer)
                    PaintingListener.colors[PaintingListener.clicked] = new Color(192, 192, 192);
                else
                    frame.setBackground(new Color(192, 192, 192));
            }
        });
        JButton setGrey3 = new JButton();
        setGrey3.setBackground(new Color(128, 128, 128));
        setGrey3.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (PaintingListener.pointer)
                    PaintingListener.colors[PaintingListener.clicked] = new Color(128, 128, 128);
                else
                    frame.setBackground(new Color(128, 128, 128));
            }
        });
        JButton setGrey4 = new JButton();
        setGrey4.setBackground(new Color(63 ,63 ,63));
        setGrey4.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (PaintingListener.pointer)
                    PaintingListener.colors[PaintingListener.clicked] = new Color(63, 63, 63);
                else
                    frame.setBackground(new Color(63, 63, 63));
            }
        });
        JButton setGrey5 = new JButton();
        setGrey5.setBackground(new Color(0 ,0 ,0));
        setGrey5.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (PaintingListener.pointer)
                    PaintingListener.colors[PaintingListener.clicked] = new Color(0, 0, 0);
                else
                    frame.setBackground(new Color(0, 0, 0));
            }
        });
        item.add(setGrey1);
        item.add(setGrey2);
        item.add(setGrey3);
        item.add(setGrey4);
        item.add(setGrey5);

        JButton setYellow1 = new JButton();
        setYellow1.setBackground(new Color(255, 255, 204));
        setYellow1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (PaintingListener.pointer)
                    PaintingListener.colors[PaintingListener.clicked] = new Color(255, 255, 204);
                else
                    frame.setBackground(new Color(255, 255, 204));
            }
        });
        JButton setYellow2 = new JButton();
        setYellow2.setBackground(new Color(255, 255, 102));
        setYellow2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (PaintingListener.pointer)
                    PaintingListener.colors[PaintingListener.clicked] = new Color(255, 255, 102);
                else
                    frame.setBackground(new Color(255, 255, 102));
            }
        });
        JButton setYellow3 = new JButton();
        setYellow3.setBackground(new Color(204, 204, 0));
        setYellow3.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (PaintingListener.pointer)
                    PaintingListener.colors[PaintingListener.clicked] = new Color(204, 204, 0);
                else
                    frame.setBackground(new Color(204, 204, 0));
            }
        });
        JButton setYellow4 = new JButton();
        setYellow4.setBackground(new Color(102, 102, 0));
        setYellow4.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (PaintingListener.pointer)
                    PaintingListener.colors[PaintingListener.clicked] = new Color(102, 102, 0);
                else
                    frame.setBackground(new Color(102, 102, 0));
            }
        });
        JButton setYellow5 = new JButton();
        setYellow5.setBackground(new Color(51, 51, 0));
        setYellow5.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (PaintingListener.pointer)
                    PaintingListener.colors[PaintingListener.clicked] = new Color(51, 51, 0);
                else
                    frame.setBackground(new Color(51, 51, 0));
            }
        });
        item.add(setYellow1);
        item.add(setYellow2);
        item.add(setYellow3);
        item.add(setYellow4);
        item.add(setYellow5);

        JButton setGreen1 = new JButton();
        setGreen1.setBackground(new Color(153, 255, 153));
        setGreen1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (PaintingListener.pointer)
                    PaintingListener.colors[PaintingListener.clicked] = new Color(153, 255, 153);
                else
                    frame.setBackground(new Color(153, 255, 153));
            }
        });
        JButton setGreen2 = new JButton();
        setGreen2.setBackground(new Color(51, 255, 51));
        setGreen2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (PaintingListener.pointer)
                    PaintingListener.colors[PaintingListener.clicked] = new Color(51, 255, 51);
                else
                    frame.setBackground(new Color(51, 255, 51));
            }
        });
        JButton setGreen3 = new JButton();
        setGreen3.setBackground(new Color(0, 204, 0));
        setGreen3.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (PaintingListener.pointer)
                    PaintingListener.colors[PaintingListener.clicked] = new Color(0, 204, 0);
                else
                    frame.setBackground(new Color(0, 204, 0));
            }
        });
        JButton setGreen4 = new JButton();
        setGreen4.setBackground(new Color(0, 102, 0));
        setGreen4.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (PaintingListener.pointer)
                    PaintingListener.colors[PaintingListener.clicked] = new Color(0, 102, 0);
                else
                    frame.setBackground(new Color(0, 102, 0));
            }
        });
        JButton setGreen5 = new JButton();
        setGreen5.setBackground(new Color(0, 51, 0));
        setGreen5.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (PaintingListener.pointer)
                    PaintingListener.colors[PaintingListener.clicked] = new Color(0, 51, 0);
                else
                    frame.setBackground(new Color(0, 51, 0));
            }
        });
        item.add(setGreen1);
        item.add(setGreen2);
        item.add(setGreen3);
        item.add(setGreen4);
        item.add(setGreen5);

        JButton setBlue1 = new JButton();
        setBlue1.setBackground(new Color(153, 204, 255));
        setBlue1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (PaintingListener.pointer)
                    PaintingListener.colors[PaintingListener.clicked] = new Color(153, 204, 255);
                else
                    frame.setBackground(new Color(153, 204, 255));
            }
        });
        JButton setBlue2 = new JButton();
        setBlue2.setBackground(new Color(51, 153, 255));
        setBlue2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (PaintingListener.pointer)
                    PaintingListener.colors[PaintingListener.clicked] = new Color(51, 153, 255);
                else
                    frame.setBackground(new Color(51, 153, 255));
            }
        });
        JButton setBlue3 = new JButton();
        setBlue3.setBackground(new Color(0, 102, 204));
        setBlue3.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (PaintingListener.pointer)
                    PaintingListener.colors[PaintingListener.clicked] = new Color(0, 102, 204);
                else
                    frame.setBackground(new Color(0, 102, 204));
            }
        });
        JButton setBlue4 = new JButton();
        setBlue4.setBackground(new Color(0, 76, 153));
        setBlue4.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (PaintingListener.pointer)
                    PaintingListener.colors[PaintingListener.clicked] = new Color(0, 76, 153);
                else
                    frame.setBackground(new Color(0, 76, 153));
            }
        });
        JButton setBlue5 = new JButton();
        setBlue5.setBackground(new Color(0, 25, 51));
        setBlue5.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (PaintingListener.pointer)
                    PaintingListener.colors[PaintingListener.clicked] = new Color(0, 25, 51);
                else
                    frame.setBackground(new Color(0, 25, 51));
            }
        });
        item.add(setBlue1);
        item.add(setBlue2);
        item.add(setBlue3);
        item.add(setBlue4);
        item.add(setBlue5);

        JButton setRed1 = new JButton();
        setRed1.setBackground(new Color(255, 204, 204));
        setRed1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (PaintingListener.pointer)
                    PaintingListener.colors[PaintingListener.clicked] = new Color(255, 204, 204);
                else
                    frame.setBackground(new Color(255, 204, 204));
            }
        });
        JButton setRed2 = new JButton();
        setRed2.setBackground(new Color(255, 102, 102));
        setRed2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (PaintingListener.pointer)
                    PaintingListener.colors[PaintingListener.clicked] = new Color(255, 102, 102);
                else
                    frame.setBackground(new Color(255, 102, 102));
            }
        });
        JButton setRed3 = new JButton();
        setRed3.setBackground(new Color(255, 0, 0));
        setRed3.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (PaintingListener.pointer)
                    PaintingListener.colors[PaintingListener.clicked] = new Color(255, 0, 0);
                else
                    frame.setBackground(new Color(255, 0, 0));
            }
        });
        JButton setRed4 = new JButton();
        setRed4.setBackground(new Color(153, 0, 0));
        setRed4.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (PaintingListener.pointer)
                    PaintingListener.colors[PaintingListener.clicked] = new Color(153, 0, 0);
                else
                    frame.setBackground(new Color(153, 0, 0));
            }
        });
        JButton setRed5 = new JButton();
        setRed5.setBackground(new Color(51, 0, 0));
        setRed5.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (PaintingListener.pointer)
                    PaintingListener.colors[PaintingListener.clicked] = new Color(51, 0, 0);
                else
                    frame.setBackground(new Color(51, 0, 0));
            }
        });
        item.add(setRed1);
        item.add(setRed2);
        item.add(setRed3);
        item.add(setRed4);
        item.add(setRed5);
        popup.add(item);

        Action setColour = new AbstractAction("  Other Colours") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                colour = JColorChooser.showDialog(frame, "Select a colour", Color.WHITE);
                if (PaintingListener.pointer)
                    PaintingListener.colors[PaintingListener.clicked] = colour;
                else
                    frame.setBackground(colour);
            }
        };
        popup.add(setColour);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        frame = (JPanel) e.getSource();
        if (e.getModifiers() == InputEvent.BUTTON3_MASK && PaintingListener.index == 5)
            popup.show(e.getComponent(), e.getX(), e.getY());
        PaintingListener.pointer = false;
    }
}