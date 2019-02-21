package game.model.object.behavingobject.immovable;

import game.model.object.behavingobject.BehavingObject;

import static game.model.object.ObjectType.COLLECTABLE;

public abstract class Collectable extends BehavingObject {
    Collectable(double x, double y, int width, int height) {
        super(x, y, width, height, COLLECTABLE);
    }
}
