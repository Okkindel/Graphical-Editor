import javax.swing.event.MenuListener;
import javax.swing.border.BevelBorder;
import javax.swing.WindowConstants;
import javax.swing.event.MenuEvent;
import javax.swing.SwingConstants;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.Box;

/**
 * Class makes menus and adds components to proper editor working.
 * @author Maciej Hajduk
 */
public class Interface extends JFrame {

    /** Creates JLabel showing coordinates. */
    static JLabel statusLabel = new JLabel("X: 0   Y: 0");
    /** Creates JLabel showing number of figures. */
    static JLabel numberOfFigures = new JLabel("Number of Figures: 0");

    /** Constructor creates window and adds Shapes class. */
    protected Interface() {
        setTitle("Graphical Editor");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createMenuBar();
        createLowerBar();
        add(new PaintingListener());
    }

    /** Creates bottom plank. */
    private void createLowerBar() {
        JPanel statusPanel = new JPanel();
        statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        add(statusPanel, BorderLayout.SOUTH);
        statusPanel.setPreferredSize(new Dimension(getWidth(), 16));
        statusPanel.setLayout(new BorderLayout());
        statusPanel.add(numberOfFigures, BorderLayout.WEST);
        statusPanel.add(statusLabel, BorderLayout.EAST);
    }

    /**
     * Creates menu bar and other menus.
     * @see PaintingListener Shapes class
     */
    private void createMenuBar() {

        JMenuBar menubar = new JMenuBar();

        ImageIcon iconFile = new ImageIcon("icons/file.png");
        ImageIcon iconDraw = new ImageIcon("icons/draw.png");
        ImageIcon iconEdit = new ImageIcon("icons/edit.png");
        ImageIcon iconNew  = new ImageIcon("icons/new.png");
        ImageIcon iconExport = new ImageIcon("icons/export.png");
        ImageIcon iconOpen = new ImageIcon("icons/open.png");
        ImageIcon iconSave = new ImageIcon("icons/save.png");
        ImageIcon iconExit = new ImageIcon("icons/exit.png");
        ImageIcon iconInfo = new ImageIcon("icons/info.png");
        ImageIcon iconCircle  = new ImageIcon("icons/circle.png");
        ImageIcon iconRect = new ImageIcon("icons/rect.png");
        ImageIcon iconPolygon = new ImageIcon("icons/polygon.png");
        ImageIcon iconMove = new ImageIcon("icons/move.png");
        ImageIcon iconSize = new ImageIcon("icons/size.png");
        ImageIcon iconUndo = new ImageIcon("icons/undo.png");
        ImageIcon iconDelete = new ImageIcon("icons/delete.png");

        JMenu fileMenu = new JMenu("  File ▾");
        JMenuItem newMi = new JMenuItem("New", iconNew);
        JMenuItem exportMi = new JMenuItem("Export", iconExport);
        JMenuItem openMi = new JMenuItem("Open", iconOpen);
        JMenuItem saveMi = new JMenuItem("Save", iconSave);
        JMenuItem exitMi = new JMenuItem("Exit", iconExit);
        JMenu drawMenu = new JMenu("Drawing ▾");
        JMenuItem circleMi = new JMenuItem("Oval", iconCircle);
        JMenuItem rectMi = new JMenuItem("Rectangle", iconRect);
        JMenuItem polygonMi = new JMenuItem("Polygon", iconPolygon);
        JMenu editMenu = new JMenu("Editing ▾");
        JMenuItem moveMi = new JMenuItem("Move", iconMove);
        JMenuItem sizeMi = new JMenuItem("Change", iconSize);
        JMenu undoMenu = new JMenu("Undo");
        JMenu deleteMenu = new JMenu("Delete");
        JMenu infoMenu = new JMenu("About");

        exitMi.addActionListener((ActionEvent event) -> {
            if (JOptionPane.showConfirmDialog(null, "Are you sure you want to leave?", "Exit", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                System.exit(0);
        });
        newMi.addActionListener((ActionEvent event) -> {
            PaintingListener.clearAll();
            repaint();
        });
        exportMi.addActionListener((ActionEvent event) ->
                PaintingListener.index = 7
        );
        saveMi.addActionListener((ActionEvent event) ->
            FileSaveLoad.save()
        );
        openMi.addActionListener((ActionEvent event) -> {
            FileSaveLoad.read();
            PaintingListener.var = FiguresDrawing.shapes.size();
            repaint();
        });
        moveMi.addActionListener((ActionEvent event) -> {
            PaintingListener.index = 4;
            repaint();
        });
        sizeMi.addActionListener((ActionEvent event) -> {
            PaintingListener.index = 5;
            PaintingListener.pointer = false;
            repaint();
        });
        rectMi.addActionListener((ActionEvent event) -> {
            PaintingListener.index = 1;
            repaint();
        });
        polygonMi.addActionListener((ActionEvent event) -> {
            PaintingListener.index = 2;
            repaint();
        });
        circleMi.addActionListener((ActionEvent event) -> {
            PaintingListener.index = 3;
            repaint();
        });

        fileMenu.setIcon(iconFile);
        fileMenu.setHorizontalTextPosition(SwingConstants.CENTER);
        fileMenu.setVerticalTextPosition(SwingConstants.BOTTOM);
        drawMenu.setIcon(iconDraw);
        drawMenu.setHorizontalTextPosition(SwingConstants.CENTER);
        drawMenu.setVerticalTextPosition(SwingConstants.BOTTOM);
        editMenu.setIcon(iconEdit);
        editMenu.setHorizontalTextPosition(SwingConstants.CENTER);
        editMenu.setVerticalTextPosition(SwingConstants.BOTTOM);
        deleteMenu.setIcon(iconDelete);
        deleteMenu.setHorizontalTextPosition(SwingConstants.CENTER);
        deleteMenu.setVerticalTextPosition(SwingConstants.BOTTOM);
        undoMenu.setIcon(iconUndo);
        undoMenu.setHorizontalTextPosition(SwingConstants.CENTER);
        undoMenu.setVerticalTextPosition(SwingConstants.BOTTOM);
        infoMenu.setIcon(iconInfo);
        infoMenu.setHorizontalTextPosition(SwingConstants.CENTER);
        infoMenu.setVerticalTextPosition(SwingConstants.BOTTOM);

        infoMenu.addMenuListener(new MenuListener() {
            public void menuSelected(MenuEvent e) {
                JOptionPane.showMessageDialog(null, "Simple graphical editor created in java.\n"
                        + "May contain some errors.\n"
                        + "Author: Maciej Hajduk.");}
            public void menuDeselected(MenuEvent e) {}
            public void menuCanceled(MenuEvent e) {}
        });
        undoMenu.addMenuListener(new MenuListener() {
            public void menuSelected(MenuEvent e) {
                PaintingListener.deleteLast();
                repaint();}
            public void menuDeselected(MenuEvent e) {}
            public void menuCanceled(MenuEvent e) {}
        });
        deleteMenu.addMenuListener(new MenuListener() {
            public void menuSelected(MenuEvent e) {
                PaintingListener.deleteShape();
                repaint();}
            public void menuDeselected(MenuEvent e) {}
            public void menuCanceled(MenuEvent e) {}
        });

        fileMenu.add(newMi);
        fileMenu.add(exportMi);
        fileMenu.add(openMi);
        fileMenu.add(saveMi);
        fileMenu.addSeparator();
        fileMenu.add(exitMi);
        drawMenu.add(circleMi);
        drawMenu.addSeparator();
        drawMenu.add(rectMi);
        drawMenu.addSeparator();
        drawMenu.add(polygonMi);
        editMenu.add(moveMi);
        editMenu.addSeparator();
        editMenu.add(sizeMi);
        menubar.add(fileMenu);
        menubar.add(drawMenu);
        menubar.add(editMenu);
        menubar.add(Box.createHorizontalGlue());
        menubar.add(Box.createHorizontalGlue());
        menubar.add(undoMenu);
        menubar.add(deleteMenu);
        menubar.add(Box.createHorizontalGlue());
        menubar.add(infoMenu);
        setJMenuBar(menubar);
    }
}
