package game.model.object.movable;

import game.model.MovementConstants;
import game.model.behaviour.movement.Rebound;

import java.awt.*;
import java.util.Random;

import static game.model.object.ObjectType.ENEMY;

public class Enemy extends MovableObject {
    private static final int WIDTH = 20;
    private static final int HEIGHT = 20;
    private static final int MINIMUM_SPEED = 1;

    public Enemy(double x, double y) {
        super(x, y, ENEMY, new MovementConstants(3, 3, 6, 1));
        Random random = new Random();
        xSpeed = random.nextDouble() * (getMovementConstants().getMaxSpeed() - MINIMUM_SPEED) + MINIMUM_SPEED;
        ySpeed = random.nextDouble() * (getMovementConstants().getMaxSpeed() - MINIMUM_SPEED) + MINIMUM_SPEED;
        this.addBehaviour(new Rebound(this));
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.fillRect((int) Math.round(x - WIDTH / 2.0), (int) Math.round(y - HEIGHT / 2.0), WIDTH, HEIGHT);
    }
}
