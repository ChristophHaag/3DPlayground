import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RenderArea extends JPanel {
    final float fps = 120;
    final float ms = (1/fps) * 1000;
    RenderArea(World w, Player p) {
        this.w = w;
        this.p = p;

        Timer t = new Timer((int) ((1./fps)*1000), new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.doMove();
                repaint();
            }
        });
        t.start();

    }
    World w;
    Player p;

    void drawLine(Graphics g, Point p1, Point p2, int outputwidth, int outputheight) {

        // remove this once we have proper cut off at screen edges
        if (p1.x < 0|| p1.y < 0 || p2.x < 0 || p2.y < 0 || p1.x > outputwidth || p1.y > outputheight || p2.x > outputwidth || p2.y > outputheight ||
                p1.z < 0 || p2.z < 0) {
            return;
        }

        //System.out.println("draw line " + p1 + " -> " + p2);
        g.drawLine((int) p1.x, (int) p1.y, (int)p2.x, (int)p2.y);
    }

    Point eye = new Point(0,0,0); // this is the user sitting 1 meter in front of the screen. That's the "real" origin used for our projection
    Point screenorigin = new Point(0,0,1); // the screen origin is the middle of the screen, it is 1 meter in front of the user
    Plane screenplane = new Plane(screenorigin, new Point(0,0,1));
    float meterpixelproportion = 500; //1 Meter on the screen is 500 pixels //TODO: fix with fov or so

    int f = 0;
    long last = System.currentTimeMillis();
    @Override
    public void paint(Graphics g) {
        long now = System.currentTimeMillis();
        if (now - last > ms+1) {
            System.out.println("too slow! " + (now - last) + " ms > " + ms);
            last = now;
            return;
        }
        last = now;
        int outputheight = getHeight();
        int outputwidth = getWidth();
        g.clearRect(0, 0, outputwidth, outputheight);

        //origin of the screen coordinate space will be in the middle
        int middlex = outputwidth/2;
        int middley = outputheight/2;

        // draw a dot into our origin
        g.drawOval(middlex-2, middley-2, 4, 4);

        // draw axes
        //g.drawLine(middlex,0,middlex, outputheight);
        //g.drawLine(0,middley,outputwidth, middley);

        g.drawString("(" + p.x + ", " + p.y + ", " + p.z + ") | " + p.getms() + "m/s", 0, 20);

        // System.out.println("height " + outputheight);
        for (GameObject go : w.gameobjects) {
            //System.out.println(go.getName() + ": " + go.getPointPairs());
            for (PointPair pp : go.getPointPairs()) {
                // points in world space, coordinates in meters
                Point p1 = pp.p1.clone();
                Point p2 = pp.p2.clone();

                //Player moves around, means the world moves in the opposite directions
                p1.x -= p.x;
                p1.y -= p.y;
                p1.z -= p.z;

                p2.x -= p.x;
                p2.y -= p.y;
                p2.z -= p.z;

                // intersect screen with line from eye to point1
                PointPair eyeToPoint1 = new PointPair(eye, p1);
                PointPair eyeToPoint2 = new PointPair(eye, p2);
                // don't care about this for now
                if (p1.z < 0 || p2.z < 0) {
                    continue;
                }
                Point p1is = screenplane.intersection(eyeToPoint1);
                Point p2is = screenplane.intersection(eyeToPoint2);
                //System.out.println(eyeToPoint1 + " intersection with screen: " + p1is);
                //System.out.println(eyeToPoint2 + " intersection with screen: " + p2is);

                // points projected on the screen, will be drawn according to only x and y pixel count
                // the origin will be the middle of the screen
                Point proj1 = p1is.clone();
                Point proj2 = p2is.clone();

                proj1.multiply(meterpixelproportion);
                proj2.multiply(meterpixelproportion);

                proj1.shiftxy(middlex, middley);
                proj2.shiftxy(middlex, middley);

                // Java graphics has y top left
                // our screen coordinate system has y bottom left
                // so simply flip our screen coordinates before displaying
                proj1.flip_y(outputheight);
                proj2.flip_y(outputheight);


                //System.out.println("draw " + f++);
                g.drawString("(" + pp.p1.x + ", " + pp.p1.y + ", " + pp.p1.z + ")", (int) proj1.x - 50, (int) proj1.y + 15);
                g.drawString("(" + pp.p2.x + ", " + pp.p2.y + ", " + pp.p2.z + ")", (int) proj2.x - 50, (int) proj2.y + 15);

                //g.drawString("(" + pp.p1.x + ", " + pp.p1.y + ", " + p1.z + ")", (int) proj1.x - 50, (int) proj1.y + 30);
                //g.drawString("(" + pp.p2.x + ", " + pp.p2.y + ", " + p2.z + ")", (int) proj2.x - 50, (int) proj2.y + 30);

                g.drawOval((int)proj1.x-1, (int)proj1.y-1, 2, 2);
                g.drawOval((int)proj2.x-1, (int)proj2.y-1, 2, 2);
                //if (true) continue;
                drawLine(g, proj1, proj2, outputwidth, outputheight);
            }
        }
    }
}
