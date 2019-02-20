package game.model.object.movable;

import game.model.MovementConstants;
import game.model.object.ObjectType;

import java.awt.*;

public class TrailObject extends MovableObject {
    private final int WIDTH = 32;
    private final int HEIGHT = 32;

    TrailObject(double x, double y, MovementConstants movementConstants) {
        super(x, y, ObjectType.TRAIL_OBJECT, movementConstants);
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
