import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main {

    public static void main(String[] args) {
        World w = new World();
        Player p = new Player();

        w.gameobjects.add(new Cube(5, 0, 10, 2, 2, 2));
        w.gameobjects.add(new Cube(-4, 0, 7, 4, 4, 4));
        w.gameobjects.add(new Cube(1,5,5,2,2,2));
        w.gameobjects.add(new Cube(1,-5,3,1,1,1));

        JFrame frame = new JFrame("3D Game");
        frame.setSize(1920, 1080);
        frame.setVisible(true);
        RenderArea ra = new RenderArea(w, p);
        frame.add(ra);

        frame.addKeyListener(new PlayerKeyListener(p));

        //ra.repaint();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
}
