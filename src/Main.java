import java.awt.EventQueue;

/**
 * Main.java
 * Just a main class.
 *
 * Created on April 2017.
 * @author Maciej Hajduk
 */

public class Main {
    public static void main (String[] args) {
        EventQueue.invokeLater(() -> {
            Interface menu = new Interface();
            menu.setVisible(true);
        });
    }
}