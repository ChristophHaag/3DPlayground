import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerKeyListener implements KeyListener {
    Player p;
    PlayerKeyListener(Player p) {
        this.p = p;
    }
    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_UP:
                p.input(Player.DIRECTION.FORWARD);
                break;
            case KeyEvent.VK_DOWN:
                p.input(Player.DIRECTION.BACKWARD);
                break;
            case KeyEvent.VK_LEFT:
                p.input(Player.DIRECTION.LEFTSTRAFE);
                break;
            case KeyEvent.VK_RIGHT:
                p.input(Player.DIRECTION.RIGHTSTRAFE);
                break;
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
            case KeyEvent.VK_INSERT:
                p.input(Player.DIRECTION.UP);
                break;
            case KeyEvent.VK_DELETE:
                p.input(Player.DIRECTION.DOWN);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        p.input(Player.DIRECTION.NOTHING);
    }

}
