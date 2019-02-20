package game.model.object;

import java.awt.*;

public abstract class GameObject {
    protected double x;
    protected double y;
    protected final int width;
    protected final int height;
    private ObjectType type;

    protected GameObject(double x, double y, int width, int height, ObjectType type) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;
    }

    public abstract void tick();

    public abstract void render(Graphics graphics);

    boolean is(ObjectType type) {
        return this.type == type;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean isColliding(GameObject other) {
        double thisX = this.x - width;
        double otherX = other.x - other.width / 2.0;

        double thisY = this.y - height;
        double otherY = other.y - other.height / 2.0;

        Rectangle thisRectangle = new Rectangle((int) thisX, (int) thisY, width, height);
        Rectangle otherRectangle = new Rectangle((int) otherX, (int) otherY, other.width, other.height);

        return thisRectangle.intersects(otherRectangle);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
