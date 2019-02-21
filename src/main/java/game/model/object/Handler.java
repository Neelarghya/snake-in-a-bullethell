package game.model.object;

import java.awt.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static game.model.object.Status.ALIVE;
import static game.model.object.Status.DEAD;

public class Handler {
    private ConcurrentHashMap<GameObject, Status> objects;

    public Handler() {
        objects = new ConcurrentHashMap<>();
    }

    public void tick() {
        purgeDeadObjects();

        objects.keySet()
                .stream()
                .filter(key -> objects.get(key) == ALIVE)
                .forEach(GameObject::tick);
    }

    private void purgeDeadObjects() {
        objects.keySet()
                .stream()
                .filter(key -> objects.get(key) == DEAD)
                .forEach(object -> objects.remove(object));
    }

    public void render(Graphics graphics) {
        objects.keySet()
                .stream()
                .filter(key -> objects.get(key) == ALIVE)
                .forEach(object -> object.render(graphics));
    }

    public void addObject(GameObject object) {
        objects.put(object, ALIVE);
    }

    public List<GameObject> get(ObjectType type) {
        return objects.keySet()
                .stream()
                .filter(object -> object.is(type))
                .collect(Collectors.toList());
    }

    public void remove(GameObject object) {
        objects.put(object, DEAD);
    }
}
