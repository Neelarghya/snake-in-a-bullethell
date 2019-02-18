package game.model.behaviour.movement;

import game.model.object.GameObject;
import game.model.object.movable.MovableObject;

public class Follow extends Movement{
    private GameObject target;

    public Follow(MovableObject object, GameObject target) {
        super(object);
        this.target = target;
    }

    @Override
    public void behave() {
        double xDiff = target.getX() - object.getX();
        double yDiff = target.getY() - object.getY();

        double angle = Math.atan2(yDiff, xDiff);

        double xAcceleration = object.getMovementConstants().getXAcceleration() * Math.cos(angle);
        double yAcceleration = object.getMovementConstants().getYAcceleration() * Math.sin(angle);

        object.accelerate(xAcceleration, yAcceleration);
    }
}
