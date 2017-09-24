public class Plane {
    //Plane(Point p1, Point p2, Point p3) {
    //}

    Point origin;
    Point normalvector;

    Plane(Point origin, Point normalvector) {
        this.origin = origin;
        this.normalvector = normalvector;
    }

    //http://geomalgorithms.com/a05-_intersect-1.html
    public Point intersection(PointPair pp) {
        Point p1 = pp.p1.clone();
        Point p2 = pp.p2.clone();
        Point upper = origin.clone();
        upper.subtract(p1);
        Point lower = pp.p2;
        lower.subtract(pp.p1);
        float s1 = normalvector.dot(upper) / normalvector.dot(lower);
        //System.out.println("s1 " + s1);

        Point ret = p1.clone();
        Point inner = pp.p2.clone();
        inner.subtract(pp.p1);
        inner.multiply(s1);
        ret.add(inner);
        return ret;
    }
}
