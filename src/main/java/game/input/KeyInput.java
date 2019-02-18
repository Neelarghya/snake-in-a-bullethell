package game.input;

import game.model.behaviour.movement.keymovement.MacroKeyMovementBehaviour;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    private final MacroKeyMovementBehaviour behaviour;

    public KeyInput(MacroKeyMovementBehaviour behaviour) {
        this.behaviour = behaviour;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W) behaviour.moveUp();
        if (key == KeyEvent.VK_A) behaviour.moveLeft();
        if (key == KeyEvent.VK_S) behaviour.moveDown();
        if (key == KeyEvent.VK_D) behaviour.moveRight();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W) behaviour.stopMovingUp();
        if (key == KeyEvent.VK_A) behaviour.stopMovingLeft();
        if (key == KeyEvent.VK_S) behaviour.stopMovingDown();
        if (key == KeyEvent.VK_D) behaviour.stopMovingRight();
    }
}
