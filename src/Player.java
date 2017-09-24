import java.awt.event.KeyEvent;

public class Player {
    float x = 0;
    float y = 0;
    float z = 0;

    private float speed = 0;
    DIRECTION d = DIRECTION.NOTHING;

    public float getms() {
        return speed;
    }

    float fasterDelta(float speed) {
        return (float) (1-Math.log10(speed + 8)) /20;
    }
    public void doMove() {
        //System.out.println("do move " + d + " " + speed);
        if (speed < 0.5 && d != DIRECTION.NOTHING) {
            speed += fasterDelta(speed);
        } else if (d == DIRECTION.NOTHING) {
            speed = 0;
        }


        switch (d) {
            case FORWARD:
                z += speed;
                break;
            case BACKWARD:
                z -= speed;
                break;
            case LEFTSTRAFE:
                x -= speed;
                break;
            case RIGHTSTRAFE:
                x += speed;
                break;
            case UP:
                y += speed;
                break;
            case DOWN:
                y -= speed;
                break;
            case NOTHING:
                break;
        }
    }

    public void input(DIRECTION d) {
        this.d = d;
    }

    public enum DIRECTION {
        FORWARD,
        BACKWARD,
        LEFTSTRAFE,
        RIGHTSTRAFE,
        UP,
        DOWN,
        NOTHING
    }
}
