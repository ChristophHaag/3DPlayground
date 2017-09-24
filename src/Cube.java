import java.util.ArrayList;
import java.util.List;

public class Cube extends GameObject {

    List<Point> points = new ArrayList<>();
    List<PointPair> connections = new ArrayList<>();
    public Cube(int x, int y, int z, int width, int height, int length) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.width = width;
        this.height = height;
        this.length = length;

        Point top_left = new Point(x - width/2.f, y - height/2.f, z + length/2.f); //front
        Point top_right = new Point(x + width/2.f, y - height/2.f, z + length/2.f);
        Point bottom_left = new Point(x - width/2.f, y + height/2.f, z + length/2.f);
        Point bottom_right = new Point(x + width/2.f, y + height/2.f, z + length/2.f);
        Point top_left2 = new Point(x - width/2.f, y - height/2.f, z - length/2.f); // back
        Point top_right2 = new Point(x + width/2.f, y - height/2.f, z - length/2.f);
        Point bottom_left2 = new Point(x - width/2.f, y + height/2.f, z - length/2.f);
        Point bottom_right2 = new Point(x + width/2.f, y + height/2.f, z - length/2.f);

        points.add(top_left);
        points.add(top_right);
        points.add(bottom_left);
        points.add(bottom_right);

        points.add(top_left2);
        points.add(top_right2);
        points.add(bottom_left2);
        points.add(bottom_right2);

        PointPair top_side = new PointPair(top_left, top_right); // front square
        PointPair left_side = new PointPair(top_left, bottom_left);
        PointPair bottom_side = new PointPair(bottom_left, bottom_right);
        PointPair right_side = new PointPair(top_right, bottom_right);

        PointPair top_side2 = new PointPair(top_left2, top_right2); // back square
        PointPair left_side2 = new PointPair(top_left2, bottom_left2);
        PointPair bottom_side2 = new PointPair(bottom_left2, bottom_right2);
        PointPair right_side2 = new PointPair(top_right2, bottom_right2);

        PointPair topleft_frontback = new PointPair(top_left, top_left2); // connections front to back
        PointPair topright_frontback = new PointPair(top_right, top_right2);
        PointPair bottomleft_frontback = new PointPair(bottom_left, bottom_left2);
        PointPair bottomright_frontback = new PointPair(bottom_right, bottom_right2);

        connections.add(top_side);
        connections.add(left_side);
        connections.add(bottom_side);
        connections.add(right_side);
        connections.add(top_side2);
        connections.add(left_side2);
        connections.add(bottom_side2);
        connections.add(right_side2);
        connections.add(topleft_frontback);
        connections.add(topright_frontback);
        connections.add(bottomleft_frontback);
        connections.add(bottomright_frontback);
    }


    @Override
    public String getName() {
        return "Cube";
    }

    @Override
    public List<PointPair> getPointPairs() {
        return connections;
    }
}
