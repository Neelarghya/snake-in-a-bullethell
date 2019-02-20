package game.view.ui;

import game.common.Observable;
import game.common.Observer;
import game.model.object.movable.Player;

import java.awt.*;

import static game.model.object.ObjectType.PLAYER;

public class HeadsUpDisplay implements Observer {
    private static final int MAX_WIDTH = 200;
    private static final int HEIGHT = 30;
    private static final int X_OFFSET = 30;
    private static final int Y_OFFSET = 30;

    private int playerHealth;
    private int width;

    public HeadsUpDisplay() {
        this.width = 0;
        this.playerHealth = 0;
    }

    public void tick() {
        width = (int) (playerHealth / 100.0 * MAX_WIDTH);
    }

    public void render(Graphics graphics) {
        graphics.setColor(Color.GRAY);
        graphics.fillRect(X_OFFSET, Y_OFFSET, MAX_WIDTH, HEIGHT);

        graphics.setColor(Color.GREEN);
        graphics.fillRect(X_OFFSET, Y_OFFSET, width, HEIGHT);

        graphics.setColor(Color.WHITE);
        graphics.drawRect(X_OFFSET, Y_OFFSET, MAX_WIDTH, HEIGHT);
    }

    @Override
    public void update(Observable observable) {
        if (observable.is() == PLAYER) {
            playerHealth = ((Player) observable).getHealth();
        }
    }
}
