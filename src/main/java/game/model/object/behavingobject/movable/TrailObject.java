package game.model.object.behavingobject.movable;

import game.model.MovementConstants;
import game.model.object.behavingobject.ColorResettable;
import game.model.object.ObjectType;

import java.awt.*;
import java.util.Random;

public class TrailObject extends MovableObject implements ColorResettable {
    private static final int WIDTH = 32;
    private static final int HEIGHT = 32;
    private final Color originalColor;
    private Color color;

    TrailObject(double x, double y, MovementConstants movementConstants, Color color) {
        super(x, y, WIDTH, HEIGHT, ObjectType.TRAIL_OBJECT, movementConstants);
        this.originalColor = color;
        this.color = originalColor;
    }

    @Override
    public void tick() {
        super.tick();
        if (new Random().nextInt(20) < 3) reSetColor();
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(color);
        graphics.fillRect((int) Math.round(x - WIDTH / 2.0), (int) Math.round(y - HEIGHT / 2.0), WIDTH, HEIGHT);
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void reSetColor() {
        this.color = originalColor;
    }
}
