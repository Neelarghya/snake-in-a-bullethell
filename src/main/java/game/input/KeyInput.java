package game.input;

import game.model.behaviour.movement.keymovement.MacroKeyMovementBehaviour;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class KeyInput extends KeyAdapter {
    private final List<MacroKeyMovementBehaviour> behaviours;

    public KeyInput() {
        this.behaviours = new ArrayList<>();
    }

    public void addBehaviour(MacroKeyMovementBehaviour behaviour) {
        behaviours.add(behaviour);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for (MacroKeyMovementBehaviour behaviour : behaviours) {
            if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) behaviour.moveUp();
            if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) behaviour.moveLeft();
            if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) behaviour.moveDown();
            if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) behaviour.moveRight();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for (MacroKeyMovementBehaviour behaviour : behaviours) {
            if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) behaviour.stopMovingUp();
            if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) behaviour.stopMovingLeft();
            if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) behaviour.stopMovingDown();
            if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) behaviour.stopMovingRight();
            if (key == KeyEvent.VK_ESCAPE) System.exit(1);
        }
    }
}
