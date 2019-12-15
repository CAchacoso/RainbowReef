package Shell;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ShellControl implements KeyListener{
    private Shell s1;
    private final int right;
    private final int left;

    public ShellControl(Shell s1, int left, int right) {
        this.s1 = s1;
        this.right = right;
        this.left = left;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int keyPressed = ke.getKeyCode();
        if (keyPressed == left) {
            this.s1.toggleLeftPressed();
        }
        if (keyPressed == right) {
            this.s1.toggleRightPressed();
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int keyReleased = ke.getKeyCode();
        if (keyReleased  == left) {
            this.s1.unToggleLeftPressed();
        }
        if (keyReleased  == right) {
            this.s1.unToggleRightPressed();
        }
    }
}
