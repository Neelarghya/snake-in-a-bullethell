package game.input;

import game.common.Action;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameKeyInput extends KeyAdapter {
    private Action togglePause;
    private Action close;

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ESCAPE) close.act();
        if (key == KeyEvent.VK_SPACE) togglePause.act();
    }

    public void setTogglePauseAction(Action togglePause) {
        this.togglePause = togglePause;
    }

    public void setCloseAction(Action close) {
        this.close = close;
    }
}
