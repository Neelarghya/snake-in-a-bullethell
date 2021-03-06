package game.model.object.behavingobject;

import game.model.behaviour.Behaviour;
import game.model.object.GameObject;
import game.model.object.ObjectType;

import java.util.ArrayList;
import java.util.List;

public abstract class BehavingObject extends GameObject {
    private final List<Behaviour> behaviours;

    protected BehavingObject(double x, double y, int width, int height, ObjectType type) {
        super(x, y, width, height, type);
        behaviours = new ArrayList<>();
    }

    @Override
    public void tick() {
        behaviours.forEach(Behaviour::behave);
    }

    public void addBehaviour(Behaviour behaviour) {
        behaviours.add(behaviour);
    }
}
