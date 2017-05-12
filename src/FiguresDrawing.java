import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.List;

/**
 * Class is responsible for drawing figures.
 * @author Maciej Hajduk
 */
public class FiguresDrawing extends PaintingListener {

    /** ArrayList of figures. */
    protected static List<Polygon> shapes = new ArrayList<>();
    /** The figure we are working on. */
    static Polygon currentShape = new Polygon();

    /** Polygon drawing method.
     * @param g Standard graphical parameter.
     * @param polygon Reference to ArrayList.
     */
    static void drawPolygon(Graphics g, Polygon polygon) {

        if (polygon.npoints < 3) {
            if (polygon.npoints == 1) {
                g.fillRect(polygon.xpoints[0] - 3, polygon.ypoints[0] - 3, 6, 6);
            } else if (polygon.npoints == 2) {
                g.drawLine(polygon.xpoints[0], polygon.ypoints[0], polygon.xpoints[1], polygon.ypoints[1]);
            }
        }
        else {
            for (int i = 0; i < polygon.npoints; i++) {
                g.fillPolygon(polygon);
            }
        }
    }

    /** Rectangle drawing method.
     * @param g Standard graphical parameter.
     * @param rect Reference to ArrayList.
     */
    static void drawRectangle(Graphics g, Polygon rect) {

        rect.xpoints[2] = Math.abs(rect.xpoints[0] - rect.xpoints[1]);
        rect.ypoints[2] = Math.abs(rect.ypoints[0] - rect.ypoints[1]);
        rect.xpoints[3] = Math.min(rect.xpoints[0], rect.xpoints[1]);
        rect.ypoints[3] = Math.min(rect.ypoints[0], rect.ypoints[1]);
        g.fillRect(rect.xpoints[3], rect.ypoints[3], rect.xpoints[2], rect.ypoints[2]);
    }

    /** Circle drawing method.
     * @param g Standard graphical parameter.
     * @param circle Reference to ArrayList.
     */
    static void drawCircle(Graphics g, Polygon circle) {

        circle.xpoints[2]=Math.abs(circle.xpoints[0] - circle.xpoints[1]);
        circle.ypoints[2]=Math.abs(circle.ypoints[0] - circle.ypoints[1]);
        circle.xpoints[3]=Math.min(circle.xpoints[0], circle.xpoints[1]);
        circle.ypoints[3]=Math.min(circle.ypoints[0], circle.ypoints[1]);
        g.fillOval(circle.xpoints[3], circle.ypoints[3], circle.xpoints[2], circle.ypoints[2]);
    }

    /** Method adds figure to ArrayList. */
    static void createShape() {
        if (index == 1 && currentShape.npoints > 1 ||
                index == 2 && currentShape.npoints > 2 ||
                index == 3 && currentShape.npoints > 1) {
            try {
                if (var < counter.length)
                    shapes.add(currentShape);
                counter[var] = index;
                var++;
            } catch(ArrayIndexOutOfBoundsException ex) {
                index = 6;
                clearAll();
            }
        }
        clearCurrentShape();
    }

    /** Clears current figure. */
    static void clearCurrentShape() {
        currentShape = new Polygon();
    }
}