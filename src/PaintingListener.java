import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.awt.event.MouseAdapter;
import javax.swing.SwingUtilities;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.Arrays;
import java.awt.Polygon;
import java.awt.Color;
import java.awt.Point;

/**
 * Class adds MouseListeners, also contains component responsible for drawing, adding points to array and controlling shapes.
 * @author Maciej Hajduk
 */
public class PaintingListener extends JPanel {

    /** Overwrites the dragged figure. */
    private Polygon dragged;
    /** Saves last location. */
    private Point lastLocation;
    /** Creates point to change polygon size. */
    private static int point;
    /** Checks if figure was removed. */
    private static Boolean removed = false;
    /** Default color */
    private static Color color = new Color(50, 50, 50);
    /** Checks if any figure is marked. */
    static Boolean pointer = false;
    /** Index - state of program, Clicked - Overwrites the clicked figure. */
    static int index, var, clicked = 0;
    /** X and Y coordinates of mouse. */
    private static int x, y;
    /** Array of program states. */
    static int[] counter = new int[25];
    /** Array of colors. */
    static Color[] colors = new Color[25];

    /** Constructor just adds Mouse Listeners. */
    PaintingListener() {
        addMouseListener(new PopUpMenu());
        addMouseListener(new DrawingClicker());
        addMouseMotionListener(new DrawingDragger());
        addMouseWheelListener(new DrawingTurner());
    }

    /** Adding point to array.
     * @param x x points.
     * @param y y points.
     */
    private void addPoint(int x, int y) {
        FiguresDrawing.currentShape.addPoint(x, y);
        repaint();
    }

    /** Just standard graphics paint component. */
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.setColor(color);

        int record = 0;
        for (Polygon shape : FiguresDrawing.shapes) {
            try {
                color = colors[record];
            } catch (ArrayIndexOutOfBoundsException Ex) {
                record = 0;
            }
            g.setColor(color);
            if (counter[record] == 1)
                FiguresDrawing.drawRectangle(g, shape);
            if (counter[record] == 2)
                FiguresDrawing.drawPolygon(g, shape);
            if (counter[record] == 3)
                FiguresDrawing.drawCircle(g, shape);

            g.setColor(new Color(50, 50, 50));
            if (index == 5 && pointer)
                for (int i = 0; i < shape.npoints; i++)
                    if (FiguresDrawing.shapes.indexOf(shape) == clicked)
                        g.drawRect(shape.xpoints[i] - 5, shape.ypoints[i] - 5, 10, 10);
            record++;
        }
        g.setColor(new Color(160, 160, 160));

        if (index == 1)
            FiguresDrawing.drawRectangle(g, FiguresDrawing.currentShape);
        if (index == 2)
            FiguresDrawing.drawPolygon(g, FiguresDrawing.currentShape);
        if (index == 3)
            FiguresDrawing.drawCircle(g, FiguresDrawing.currentShape);
        if (index == 5 && pointer) {
            g.setColor(new Color(160, 160, 160));
            if (counter[clicked] == 1)
                FiguresDrawing.drawRectangle(g, FiguresDrawing.shapes.get(clicked));
            if (counter[clicked] == 2)
                FiguresDrawing.drawPolygon(g, FiguresDrawing.shapes.get(clicked));
            if (counter[clicked] == 3)
                FiguresDrawing.drawCircle(g, FiguresDrawing.shapes.get(clicked));
        }
        if (index == 7) {
            index = 0;
            BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
            g = image.createGraphics();
            printAll(g);
            g.dispose();
            FileSaveLoad.export(image);
        }
    }

    /** Resets all of variables and JPanel. */
    static void clearAll() {
        if (index == 6) {
            if (JOptionPane.showConfirmDialog(null, "Too much shapes, we suggest to clear board. Is it ok?",
                    "Clear", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                FiguresDrawing.shapes.clear();
                Arrays.fill(colors, new Color(50, 50, 50));
                var = 0;
                updateLowerBar();
            }
        }
        else if (JOptionPane.showConfirmDialog(null, "Are you sure you want to clear all?",
                "New Project", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
        {
            index = 0;
            FiguresDrawing.shapes.clear();
            Arrays.fill(colors, new Color(50, 50, 50));
            var = 0;
        }
    }

    /** Standard undo, removes last shape. */
    static void deleteLast() {
        try {
            FiguresDrawing.shapes.remove(FiguresDrawing.shapes.size() - 1);
            colors[var-1] = new Color(50, 50, 50);
            var--;
            updateLowerBar();
        } catch (IndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(null, "You cannot undo anymore.");
        }
    }

    /** Deleting marked shape. */
    static void deleteShape() {
        if (counter[clicked] != 0 && pointer) {
            FiguresDrawing.shapes.remove(clicked);
            System.arraycopy(counter, clicked + 1, counter, clicked, FiguresDrawing.shapes.size() - clicked);
            System.arraycopy(colors, clicked + 1, colors, clicked, colors.length - 1 - clicked);
            pointer = false;
            var--;
            updateLowerBar();
        }
        else
            JOptionPane.showMessageDialog(null, "Choose the element you want to delete.");
    }

    /** Updates coordinates on status bar. */
    private static void updateLowerBar() {
        Interface.statusLabel.setText(String.valueOf("X: " + x + "  Y: " + y));
        Interface.numberOfFigures.setText(String.valueOf("Number of Figures: " + var));
    }

    /** Mouse Listeners acting on index variable. */
    class DrawingClicker extends MouseAdapter {

        public void mousePressed(MouseEvent e) {

            if (index == 1 || index == 3) {
                removed = false;
                addPoint(e.getX(), e.getY());
                addPoint(e.getX(), e.getY());
                repaint();
                if (SwingUtilities.isMiddleMouseButton(e)) {
                    FiguresDrawing.clearCurrentShape();
                    removed = true;
                    repaint();
                }
            }

            outerloop:
            if (index == 5) {
                for (Polygon shape : FiguresDrawing.shapes) {
                    for (point = 0; point < shape.npoints; point++) {
                        if (e.getX() > shape.xpoints[point] - 10 && e.getX() < shape.xpoints[point] + 10 &&
                                e.getY() > shape.ypoints[point] - 10 && e.getY() < shape.ypoints[point] + 10) {
                            dragged = shape;
                            break outerloop;
                        }
                    }
                }
            }

            if (index == 4) {
                for (Polygon shape : FiguresDrawing.shapes) {
                    int x_zero = Math.min(shape.xpoints[0], shape.xpoints[1]);
                    int y_zero = Math.min(shape.ypoints[0], shape.ypoints[1]);
                    int x_one = Math.max(shape.xpoints[0], shape.xpoints[1]);
                    int y_one = Math.max(shape.ypoints[0], shape.ypoints[1]);
                    if (shape.contains(e.getPoint())) {
                        dragged = shape;
                        lastLocation = e.getPoint();
                        break;
                    } else if (e.getX() > x_zero && e.getX() < x_one && e.getY() > y_zero && e.getY() < y_one) {
                        dragged = shape;
                        lastLocation = e.getPoint();
                        break;
                    }
                }
            }
        }

        public void mouseReleased(MouseEvent e) {
            if (index == 4) {
                dragged = null;
                lastLocation = null;
            }
            if (index == 1 || index == 3) {
                FiguresDrawing.createShape();
                repaint();
            }
            repaint();
        }

        public void mouseClicked(MouseEvent e) {

            if (index == 2) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (e.getClickCount() == 1) {
                        addPoint(e.getX(), e.getY());
                        repaint();
                    } else if (e.getClickCount() == 2 && index != 0) {
                        FiguresDrawing.createShape();
                        repaint();
                    }
                } else if (SwingUtilities.isMiddleMouseButton(e)) {
                    FiguresDrawing.clearCurrentShape();
                    repaint();
                }
            }
            if (index == 5) {
                for (Polygon shape : FiguresDrawing.shapes) {
                    int x_zero = Math.min(shape.xpoints[0], shape.xpoints[1]);
                    int y_zero = Math.min(shape.ypoints[0], shape.ypoints[1]);
                    int x_one = Math.max(shape.xpoints[0], shape.xpoints[1]);
                    int y_one = Math.max(shape.ypoints[0], shape.ypoints[1]);
                    if (shape.contains(e.getPoint())) {
                        clicked = FiguresDrawing.shapes.indexOf(shape);
                        pointer = true;
                        break;
                    } else if (e.getX() > x_zero && e.getX() < x_one && e.getY() > y_zero && e.getY() < y_one) {
                        clicked = FiguresDrawing.shapes.indexOf(shape);
                        pointer = true;
                        break;
                    }
                    repaint();
                }
            }
        }
    }

    /** Mouse Listeners acting on index variable. */
    class DrawingDragger extends MouseAdapter {

        public void mouseDragged(MouseEvent e) {
            if (index == 1 && !removed  || index == 3 && !removed) {
                FiguresDrawing.currentShape.xpoints[1] = e.getX();
                FiguresDrawing.currentShape.ypoints[1] = e.getY();
                repaint();
            }
            if (index == 4) {
                try {
                    if (dragged != null) {
                        dragged.translate(e.getX() - lastLocation.x, e.getY() - lastLocation.y);
                        lastLocation = e.getPoint();
                        repaint();

                    }
                } catch (Exception ignored) {}
            }
            if (index == 5) {
                if (dragged != null  && FiguresDrawing.shapes.indexOf(dragged) == clicked) {
                    try {
                        dragged.xpoints[point] = e.getX();
                        dragged.ypoints[point] = e.getY();
                    } catch (Exception ignored) {}
                    repaint();
                }
            }
        }
        public void mouseMoved(MouseEvent e) {
            x = e.getX();
            y = e.getY();
            updateLowerBar();
        }
    }

    /** Mouse Listeners acting on index variable. */
    class DrawingTurner extends MouseAdapter {

        public void mouseWheelMoved(MouseWheelEvent e) {
            if (index == 5) {
                if (pointer && counter[clicked] != 2) {
                    int notches = e.getWheelRotation();
                    int x_zero = Math.min(FiguresDrawing.shapes.get(clicked).xpoints[0], FiguresDrawing.shapes.get(clicked).xpoints[1]);
                    int y_zero = Math.min(FiguresDrawing.shapes.get(clicked).ypoints[0], FiguresDrawing.shapes.get(clicked).ypoints[1]);
                    if (notches < 0) {
                        if (x_zero == FiguresDrawing.shapes.get(clicked).xpoints[0]) {
                            FiguresDrawing.shapes.get(clicked).xpoints[0]--;
                            FiguresDrawing.shapes.get(clicked).xpoints[1]++;
                        } else {
                            FiguresDrawing.shapes.get(clicked).xpoints[0]++;
                            FiguresDrawing.shapes.get(clicked).xpoints[1]--;
                        }
                        if (y_zero == FiguresDrawing.shapes.get(clicked).ypoints[0]) {
                            FiguresDrawing.shapes.get(clicked).ypoints[0]--;
                            FiguresDrawing.shapes.get(clicked).ypoints[1]++;
                        } else {
                            FiguresDrawing.shapes.get(clicked).ypoints[0]++;
                            FiguresDrawing.shapes.get(clicked).ypoints[1]--;
                        }
                        repaint();
                    } else if (notches > 0) {
                        if (x_zero == FiguresDrawing.shapes.get(clicked).xpoints[0]) {
                            FiguresDrawing.shapes.get(clicked).xpoints[0]++;
                            FiguresDrawing.shapes.get(clicked).xpoints[1]--;
                        } else {
                            FiguresDrawing.shapes.get(clicked).xpoints[0]--;
                            FiguresDrawing.shapes.get(clicked).xpoints[1]++;
                        }
                        if (y_zero == FiguresDrawing.shapes.get(clicked).ypoints[0]) {
                            FiguresDrawing.shapes.get(clicked).ypoints[0]++;
                            FiguresDrawing.shapes.get(clicked).ypoints[1]--;
                        } else {
                            FiguresDrawing.shapes.get(clicked).ypoints[0]--;
                            FiguresDrawing.shapes.get(clicked).ypoints[1]++;
                        }
                        repaint();
                    }
                }
                else if (pointer && counter[clicked] == 2) {
                    int notches = e.getWheelRotation();
                    int x_average = 0, y_average = 0;
                    for (int i = 0; i < FiguresDrawing.shapes.get(clicked).npoints; i++) {
                        x_average += FiguresDrawing.shapes.get(clicked).xpoints[i];
                        y_average += FiguresDrawing.shapes.get(clicked).ypoints[i];
                    }
                    x_average /= FiguresDrawing.shapes.get(clicked).npoints;
                    y_average /= FiguresDrawing.shapes.get(clicked).npoints;
                    if (notches < 0) {
                        for (int i = 0; i < FiguresDrawing.shapes.get(clicked).npoints; i++) {
                            if (FiguresDrawing.shapes.get(clicked).xpoints[i] > x_average) {
                                if (FiguresDrawing.shapes.get(clicked).ypoints[i] > y_average) {
                                    FiguresDrawing.shapes.get(clicked).xpoints[i] ++;
                                    FiguresDrawing.shapes.get(clicked).ypoints[i] ++;
                                }
                                if (FiguresDrawing.shapes.get(clicked).ypoints[i] < y_average) {
                                    FiguresDrawing.shapes.get(clicked).xpoints[i] ++;
                                    FiguresDrawing.shapes.get(clicked).ypoints[i] --;
                                }
                                if (FiguresDrawing.shapes.get(clicked).ypoints[i] == y_average)
                                    FiguresDrawing.shapes.get(clicked).xpoints[i] ++;
                            }
                            if (FiguresDrawing.shapes.get(clicked).xpoints[i] < x_average) {
                                if (FiguresDrawing.shapes.get(clicked).ypoints[i] > y_average) {
                                    FiguresDrawing.shapes.get(clicked).xpoints[i] --;
                                    FiguresDrawing.shapes.get(clicked).ypoints[i] ++;
                                }
                                if (FiguresDrawing.shapes.get(clicked).ypoints[i] < y_average) {
                                    FiguresDrawing.shapes.get(clicked).xpoints[i] --;
                                    FiguresDrawing.shapes.get(clicked).ypoints[i] --;
                                }
                                if (FiguresDrawing.shapes.get(clicked).ypoints[i] == y_average)
                                    FiguresDrawing.shapes.get(clicked).xpoints[i] --;
                            }
                            if (FiguresDrawing.shapes.get(clicked).xpoints[i] == x_average) {
                                if (FiguresDrawing.shapes.get(clicked).ypoints[i] > y_average)
                                    FiguresDrawing.shapes.get(clicked).ypoints[i] ++;
                                if (FiguresDrawing.shapes.get(clicked).ypoints[i] < y_average)
                                    FiguresDrawing.shapes.get(clicked).ypoints[i] --;
                            }
                        }
                        repaint();
                    } else if (notches > 0) {
                        for (int i = 0; i < FiguresDrawing.shapes.get(clicked).npoints; i++) {
                            if (FiguresDrawing.shapes.get(clicked).xpoints[i] > x_average) {
                                if (FiguresDrawing.shapes.get(clicked).ypoints[i] > y_average) {
                                    FiguresDrawing.shapes.get(clicked).xpoints[i] --;
                                    FiguresDrawing.shapes.get(clicked).ypoints[i] --;
                                }
                                if (FiguresDrawing.shapes.get(clicked).ypoints[i] < y_average) {
                                    FiguresDrawing.shapes.get(clicked).xpoints[i] --;
                                    FiguresDrawing.shapes.get(clicked).ypoints[i] ++;
                                }
                                if (FiguresDrawing.shapes.get(clicked).ypoints[i] == y_average)
                                    FiguresDrawing.shapes.get(clicked).xpoints[i] --;
                            }
                            if (FiguresDrawing.shapes.get(clicked).xpoints[i] < x_average) {
                                if (FiguresDrawing.shapes.get(clicked).ypoints[i] > y_average) {
                                    FiguresDrawing.shapes.get(clicked).xpoints[i] ++;
                                    FiguresDrawing.shapes.get(clicked).ypoints[i] --;
                                }
                                if (FiguresDrawing.shapes.get(clicked).ypoints[i] < y_average) {
                                    FiguresDrawing.shapes.get(clicked).xpoints[i] ++;
                                    FiguresDrawing.shapes.get(clicked).ypoints[i] ++;
                                }
                                if (FiguresDrawing.shapes.get(clicked).ypoints[i] == y_average)
                                    FiguresDrawing.shapes.get(clicked).xpoints[i] ++;
                            }
                            if (FiguresDrawing.shapes.get(clicked).xpoints[i] == x_average) {
                                if (FiguresDrawing.shapes.get(clicked).ypoints[i] > y_average)
                                    FiguresDrawing.shapes.get(clicked).xpoints[i] --;
                                if (FiguresDrawing.shapes.get(clicked).ypoints[i] < y_average)
                                    FiguresDrawing.shapes.get(clicked).ypoints[i] ++;
                            }
                        }
                        repaint();
                    }
                }
            }
        }
    }
}