package game.model.object.ui;

import game.model.object.GameObject;
import game.model.object.Observable;
import game.model.object.Observer;
import game.model.object.movable.Player;

import java.awt.*;

import static game.model.object.ObjectType.HUD;
import static game.model.object.ObjectType.PLAYER;

public class HeadsUpDisplay extends GameObject implements Observer {
    private static final int MAX_WIDTH = 100;
    private static final int HEIGHT = 30;
    private static final int X_OFFSET = 30;
    private static final int Y_OFFSET = 30;

    private int playerHealth;
    private int width;

    public HeadsUpDisplay() {
        super(X_OFFSET, Y_OFFSET, HUD);
        this.width = 0;
        this.playerHealth = 0;
    }

    public void tick() {
        width = playerHealth / 100 * MAX_WIDTH;
    }

    public void render(Graphics graphics) {
        graphics.setColor(Color.GREEN);
        graphics.fillRect(X_OFFSET, Y_OFFSET, width, HEIGHT);
    }

    @Override
    public void update(Observable observable) {
        if(observable.is() == PLAYER) {
            playerHealth = ((Player) observable).getHealth();
        }
    }
}
