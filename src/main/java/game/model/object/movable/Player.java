package game.model.object.movable;

import game.model.MovementConstants;
import game.model.object.ObjectType;

import java.awt.*;

public class Player extends MovableObject {
    private final int WIDTH = 40;
    private final int HEIGHT = 40;

    public Player(double x, double y) {
        super(x, y, ObjectType.PLAYER, new MovementConstants(3, 3, 8, .94));
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.fillRect((int) Math.round(x - WIDTH / 2.0), (int) Math.round(y - HEIGHT / 2.0), WIDTH, HEIGHT);
    }
}
