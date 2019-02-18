package game.model.object.movable;

import game.model.MovementConstants;
import game.model.object.ObjectType;

import java.awt.*;

public class Trail extends MovableObject {
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
        graphics.fillRect((int) Math.round(x), (int) Math.round(y), 32, 32);
    }
}
