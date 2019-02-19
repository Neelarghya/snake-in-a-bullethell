package game.model.object.movable;

import game.model.MovementConstants;
import game.model.object.ObjectType;

import java.awt.*;

public class Trail extends MovableObject {
    private final int WIDTH = 32;
    private final int HEIGHT = 32;

    public Trail(double x, double y, MovementConstants movementConstants) {
        super(x, y, ObjectType.TRAIL, movementConstants);
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
