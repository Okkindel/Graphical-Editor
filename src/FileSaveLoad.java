import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

/**
 * Class is responsible for saving figures shown on the screen.
 * @author Maciej Hajduk
 */
class FileSaveLoad implements Serializable {

    /** Exports to jpg file.
     *  @param image Represents an image with 8-bit RGB color components packed into integer pixels.
     */
    static void export(BufferedImage image) {
        try {
            ImageIO.write(image, "jpg", new File("save/file.jpg"));
        }
        catch (IOException ex) {
            System.out.println("Error with exporting file");
        }
    }

    /** Saves separately ArrayList, colors and states of figures. */
    static void save() {
        try {
            FileOutputStream fout = new FileOutputStream("save/file");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(FiguresDrawing.shapes);
            fout.close();
            JOptionPane.showMessageDialog(null, "File saved.");
        }
        catch (Exception ex) {
            System.out.println("Error with saving file");
        }
        try
        {
            PrintWriter fCount = new PrintWriter("save/file_counter");
            for (int i = 0; i < PaintingListener.counter.length; i++)
                fCount.println(PaintingListener.counter[i]);
            fCount.close();
            PrintWriter fColor = new PrintWriter("save/file_colour");
            for (int i = 0; i < PaintingListener.colors.length; i++)
                fColor.println(PaintingListener.colors[i]);
            fColor.close();
        }
        catch (Exception ex) {
            System.out.println("Error with saving.");
        }
    }

    /** Loads everything and writes to its place. */
    static void read() {
        try {
            FileInputStream fin = new FileInputStream("save/file");
            ObjectInputStream ois = new ObjectInputStream(fin);
            FiguresDrawing.shapes = (ArrayList<Polygon>) ois.readObject();
            fin.close();
            JOptionPane.showMessageDialog(null, "File loaded.");
        }
        catch (Exception ex) {
            System.out.println("Error with reading file.");
        }
        try {
            Scanner fCount = new Scanner(new File("save/file_counter"));
            for (int i = 0; i < PaintingListener.counter.length; i++)
                PaintingListener.counter[i] = fCount.nextInt();
            Scanner fColor = new Scanner(new File("save/file_colour"));
            fColor.useDelimiter("\\D+");
            for (int i = 0; i < PaintingListener.colors.length; i++)
                PaintingListener.colors[i] = new Color(fColor.nextInt(), fColor.nextInt(), fColor.nextInt());
        }
        catch (Exception ex){
            System.out.println("No such file exists.");
        }
    }
}