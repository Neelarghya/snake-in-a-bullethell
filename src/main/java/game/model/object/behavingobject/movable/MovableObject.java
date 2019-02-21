package game.model.object.behavingobject.movable;

import game.model.MovementConstants;
import game.model.object.behavingobject.BehavingObject;
import game.model.object.ObjectType;

import static game.common.Constant.WINDOW_HEIGHT;
import static game.common.Constant.WINDOW_WIDTH;

public abstract class MovableObject extends BehavingObject {
    private final MovementConstants movementConstants;
    protected double xSpeed;
    protected double ySpeed;

    MovableObject(double x, double y, int width, int height, ObjectType type, MovementConstants movementConstants) {
        super(x, y, width, height, type);
        this.movementConstants = movementConstants;
        xSpeed = 0;
        ySpeed = 0;
    }

    @Override
    public void tick() {
        super.tick();
        move();
    }

    private void move() {
        regulateSpeed();
        moveAsParSpeed();
        checkBoundaries();
        decaySpeed();
    }

    private void checkBoundaries() {
        double halfWidth = width / 2.0;
        double halfHeight = height / 2.0;

        if (x < halfWidth) x = halfWidth;
        if (y < halfHeight) y = halfHeight;
        if (x > WINDOW_WIDTH - halfWidth) x = WINDOW_WIDTH - halfWidth;
        if (y > WINDOW_HEIGHT - halfHeight) y = WINDOW_HEIGHT - halfHeight;
    }

    private void moveAsParSpeed() {
        x += xSpeed;
        y += ySpeed;
    }

    public void accelerate(double xAcceleration, double yAcceleration) {
        xSpeed += xAcceleration;
        ySpeed += yAcceleration;
    }

    private void regulateSpeed() {
        double speed = Math.sqrt(xSpeed * xSpeed + ySpeed * ySpeed);
        if (Math.abs(speed) >= movementConstants.getMaxSpeed()) {
            xSpeed = (xSpeed * movementConstants.getMaxSpeed()) / speed;
            ySpeed = (ySpeed * movementConstants.getMaxSpeed()) / speed;
        }
    }

    private void decaySpeed() {
        xSpeed *= movementConstants.getFriction();
        ySpeed *= movementConstants.getFriction();
    }

    public MovementConstants getMovementConstants() {
        return movementConstants;
    }

    public void reboundX() {
        xSpeed *= -1;
    }

    public void reboundY() {
        ySpeed *= -1;
    }
}
