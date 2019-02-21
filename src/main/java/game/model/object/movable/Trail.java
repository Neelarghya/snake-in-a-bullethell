package game.model.object.movable;

import game.common.Action;
import game.model.MovementConstants;
import game.model.behaviour.visual.ResetColor;
import game.model.behaviour.collision.Collision;
import game.model.behaviour.movement.Follow;
import game.model.object.GameObject;
import game.model.object.Handler;
import game.model.object.ObjectType;

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

    public void build(int trailLength, int elasticity, double maxAcceleration, double maxSpeed, Color color) {
        GameObject lastObject = target;

        for (int index = elasticity; index < trailLength + elasticity; index++) {
            double percentage = (double) (trailLength + elasticity - index - 1) / trailLength;
            double acceleration = calculatePercentage(maxAcceleration, percentage);
            double speed = calculatePercentage(maxSpeed, percentage);
            MovementConstants movementConstants = new MovementConstants(acceleration, acceleration, speed, .96);

            TrailObject trailObject = new TrailObject(x, y, movementConstants, color);
            trailObject.addBehaviour(new Follow(trailObject, lastObject));
            lastObject = trailObject;
            trail.add(trailObject);
        }
    }

    private double calculatePercentage(double maximum, double percentage) {
        double variable = maximum * RATIO;
        return (percentage) * variable + (maximum - variable);
    }

    public void addCollision(Handler handler, ObjectType type, Action action, Color collisionColor) {
        trail.forEach(trailObject -> {
            ResetColor resetColor = new ResetColor(trailObject, 20);
            Collision collision = new Collision(trailObject, handler, type) {
                @Override
                protected void onCollide() {
                    action.act();
                    resetColor.setColor(collisionColor);
                }
            };
            trailObject.addBehaviour(collision);
            trailObject.addBehaviour(resetColor);
        });
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
