public class PointPair {
    Point p1;
    Point p2;

    public PointPair(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    @Override
    public String toString() {
        return "(" + p1.x + ", " + p1.y + ", " + p1.z + ") -> (" + p2.x + ", " + p2.y + ", " + p2.z + ")";
    }
}
