package game.model.object.movable;

import game.model.MovementConstants;
import game.model.object.ObjectType;

import java.awt.*;
import java.util.Random;

public class TrailObject extends MovableObject {
    private static final int WIDTH = 32;
    private static final int HEIGHT = 32;
    private final Color originalColor;
    private Color color;

    TrailObject(double x, double y, MovementConstants movementConstants) {
        super(x, y, WIDTH, HEIGHT, ObjectType.TRAIL_OBJECT, movementConstants);
        originalColor = Color.BLUE;
        color = originalColor;
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

    void setColor(Color color) {
        this.color = color;
    }

    private void reSetColor() {
        this.color = originalColor;
    }
}
