package game.model.behaviour.collision;

import game.model.behaviour.Behaviour;
import game.model.object.GameObject;
import game.model.object.Handler;
import game.model.object.ObjectType;

import java.util.List;

public abstract class Collision implements Behaviour {
    private final GameObject object;
    private final Handler handler;
    private final ObjectType type;

    public Collision(GameObject object, Handler handler, ObjectType type) {
        this.object = object;
        this.handler = handler;
        this.type = type;
    }

    @Override
    public void behave() {
        List<GameObject> collidingObjects = this.handler.get(this.type);
        for (GameObject collidingObject : collidingObjects) {
            if (object.isColliding(collidingObject)) {
                onCollide();
            }
        }
    }

    protected abstract void onCollide();
}
