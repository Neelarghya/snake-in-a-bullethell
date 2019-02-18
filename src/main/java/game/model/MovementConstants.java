package game.model;

public class MovementConstants {
    private final double xAcceleration;
    private final double yAcceleration;
    private final double maxSpeed;
    private final double friction;

    public MovementConstants(double xAcceleration, double yAcceleration, double maxSpeed, double friction) {
        this.xAcceleration = xAcceleration;
        this.yAcceleration = yAcceleration;
        this.maxSpeed = maxSpeed;
        this.friction = friction;
    }

    public double getXAcceleration() {
        return xAcceleration;
    }

    public double getYAcceleration() {
        return yAcceleration;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public double getFriction() {
        return friction;
    }
}
