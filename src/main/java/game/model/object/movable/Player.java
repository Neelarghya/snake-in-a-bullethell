package game.model.object.movable;

import game.model.MovementConstants;
import game.model.object.ObjectType;

import java.awt.*;

public class Player extends MovableObject {
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
        graphics.fillRect((int) Math.round(x), (int) Math.round(y), 32, 32);
    }
}
