package game.model.behaviour.movement.keymovement;

import game.model.MovementConstants;
import game.model.behaviour.movement.Movement;
import game.model.object.movable.MovableObject;

public class MacroKeyMovementBehaviour extends Movement {
    private boolean movingRight;
    private boolean movingLeft;
    private boolean movingUp;
    private boolean movingDown;
    private MovementConstants movementConstants;

    public MacroKeyMovementBehaviour(MovableObject object) {
        super(object);
        this.movementConstants = object.getMovementConstants();
    }

    public void moveRight() {
        movingRight = true;
    }

    public void moveLeft() {
        movingLeft = true;
    }

    public void moveUp() {
        movingUp = true;
    }

    public void moveDown() {
        movingDown = true;
    }

    public void stopMovingRight() {
        movingRight = false;
    }

    public void stopMovingLeft() {
        movingLeft = false;
    }

    public void stopMovingUp() {
        movingUp = false;
    }

    public void stopMovingDown() {
        movingDown = false;
    }

    private void accelerate() {
        double xAcceleration = 0;
        double yAcceleration = 0;
        if (movingRight) xAcceleration = movementConstants.getXAcceleration();
        if (movingLeft) xAcceleration = -movementConstants.getXAcceleration();
        if (movingUp) yAcceleration = -movementConstants.getYAcceleration();
        if (movingDown) yAcceleration = movementConstants.getYAcceleration();

        object.accelerate(xAcceleration, yAcceleration);
    }

    @Override
    public void behave() {
        accelerate();
    }
}
