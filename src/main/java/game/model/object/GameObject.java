package game.model.object;

import java.awt.*;

public abstract class GameObject {
    protected double x;
    protected double y;
    private ObjectType type;

    protected GameObject(double x, double y, ObjectType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public abstract void tick();
    public abstract void render(Graphics graphics);

    public boolean is(ObjectType type){
        return this.type == type;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
