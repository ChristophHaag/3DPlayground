import java.util.List;

public abstract class GameObject {
    public abstract String getName();
    int x;
    int y;
    int z;
    int width;
    int height;
    int length;

    public abstract List<PointPair> getPointPairs();
}
