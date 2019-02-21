package game.model.object.behavingobject.immovable;

import java.awt.*;

public class HealthCollectable extends Collectable {
    private static int WIDTH = 20;
    private static int HEIGHT = 20;
    private static int THICKNESS = 8;

    public HealthCollectable(double x, double y) {
        super(x, y, WIDTH, HEIGHT);
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.GREEN);
        graphics.fillRect((int) Math.round(x - THICKNESS / 2.0), (int) Math.round(y - HEIGHT / 2.0), THICKNESS, HEIGHT);
        graphics.fillRect((int) Math.round(x - WIDTH / 2.0), (int) Math.round(y - THICKNESS / 2.0), WIDTH, THICKNESS);
    }
}
