public class Point {
    float x;
    float y;
    float z;

    public Point(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public Point clone() {
        return new Point(x, y, z);
    }

    public void multiply(float i) {
        x *= i;
        y *= i;
        z *= i;
    }

    public void flip_y(float height) {
        y = height - y;
    }

    public void shiftxy(float xoffset, float yoffset) {
        x += xoffset;
        y += yoffset;
    }

    public float length() {
        return (float) Math.sqrt(x*x + y*y + z*z);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }

    float dot(Point p2) {
        return this.x * p2.x + this.y * p2.y + this.z * p2.z;
    }

    void subtract(Point p2) {
        this.x -= p2.x;
        this.y -= p2.y;
        this.z -= p2.z;
    }
    void add(Point p2) {
        this.x += p2.x;
        this.y += p2.y;
        this.z += p2.z;
    }
}
