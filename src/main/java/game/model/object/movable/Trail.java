package game.model.object.movable;

import game.model.MovementConstants;
import game.model.behaviour.movement.Follow;
import game.model.object.GameObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static game.model.object.ObjectType.TRAIL;

public class Trail extends GameObject {
    private static final double RATIO = 0.1;
    private final List<TrailObject> trail;
    private final GameObject target;

    public Trail(GameObject target) {
        super(target.getX(), target.getY(), 0, 0, TRAIL);
        this.target = target;
        trail = new ArrayList<>();
    }

    public void build(int trailLength, int elasticity, double maxAcceleration, double maxSpeed) {
        GameObject lastObject = target;

        for (int index = elasticity; index < trailLength + elasticity; index++) {
            double percentage = (double) (trailLength + elasticity - index - 1) / trailLength;
            double acceleration = calculatePercentage(maxAcceleration, percentage);
            double speed = calculatePercentage(maxSpeed, percentage);
            MovementConstants movementConstants = new MovementConstants(acceleration, acceleration, speed, .96);

            TrailObject trailObject = new TrailObject(x, y, movementConstants);
            trailObject.addBehaviour(new Follow(trailObject, lastObject));
            lastObject = trailObject;
            trail.add(trailObject);
        }
    }

    private double calculatePercentage(double maximum, double percentage) {
        double variable = maximum * RATIO;
        return (percentage) * variable + (maximum - variable);
    }

    @Override
    public void tick() {
        trail.forEach(TrailObject::tick);
    }

    @Override
    public void render(Graphics graphics) {
        trail.forEach((object) -> object.render(graphics));
    }
}
