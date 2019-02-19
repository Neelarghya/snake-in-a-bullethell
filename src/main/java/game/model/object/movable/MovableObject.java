package game.model.object.movable;

import game.model.MovementConstants;
import game.model.behaviour.Behaviour;
import game.model.object.GameObject;
import game.model.object.ObjectType;

import java.util.ArrayList;
import java.util.List;

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
        decaySpeed();
    }

    public void accelerate(double xAcceleration, double yAcceleration){
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

    public void addBehaviour(Behaviour behaviour){
        behaviours.add(behaviour);
    }

    public MovementConstants getMovementConstants() {
        return movementConstants;
    }
}
