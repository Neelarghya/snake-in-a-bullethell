package game.model.object.movable;

import game.model.MovementConstants;
import game.model.behaviour.movement.Rebound;

import java.awt.*;

import static game.model.object.ObjectType.ENEMY;

public class Enemy extends MovableObject {
    private static final int WIDTH = 20;
    private static final int HEIGHT = 20;

    Enemy(double x, double y, MovementConstants movementConstants) {
        super(x, y, ENEMY, movementConstants);
        this.addBehaviour(new Rebound(this));
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.BLUE);
        graphics.fillRect((int) Math.round(x - WIDTH / 2.0), (int) Math.round(y - HEIGHT / 2.0), WIDTH, HEIGHT);
    }
}
