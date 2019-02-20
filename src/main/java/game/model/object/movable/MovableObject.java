package game.model.object.movable;

import game.model.MovementConstants;
import game.model.behaviour.Behaviour;
import game.model.object.GameObject;
import game.model.object.ObjectType;

import java.util.ArrayList;
import java.util.List;

import static game.common.Constant.WINDOW_HEIGHT;
import static game.common.Constant.WINDOW_WIDTH;

public abstract class MovableObject extends GameObject {
    private final MovementConstants movementConstants;
    private double xSpeed;
    private double ySpeed;

    private List<Behaviour> behaviours;

    MovableObject(double x, double y, ObjectType type, MovementConstants movementConstants) {
        super(x, y, type);
        behaviours = new ArrayList<>();
        this.movementConstants = movementConstants;
        xSpeed = 0;
        ySpeed = 0;
    }

    @Override
    public void tick() {
        move();
    }

    private void move() {
        behaviours.forEach(Behaviour::behave);
        regulateSpeed();
        x += xSpeed;
        y += ySpeed;
        checkBoundaries();
        decaySpeed();
    }

    private void checkBoundaries() {
        if (x < 0) x = 0;
        if (y < 0) y = 0;
        if (x > WINDOW_WIDTH) x = WINDOW_WIDTH;
        if (y > WINDOW_HEIGHT) y = WINDOW_HEIGHT;
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

    public void addBehaviour(Behaviour behaviour) {
        behaviours.add(behaviour);
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
