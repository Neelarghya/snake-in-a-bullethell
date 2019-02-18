package game.model.behaviour.movement;

import game.model.behaviour.Behaviour;
import game.model.object.movable.MovableObject;

public abstract class Movement implements Behaviour {

    protected final MovableObject object;

    public Movement(MovableObject object) {
        this.object = object;
    }
}
