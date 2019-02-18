package game.model.object;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Handler {
    private List<GameObject> objects;

    public Handler() {
        objects = new ArrayList<>();
    }

    public void tick() {
        for (GameObject object : objects) {
            object.tick();
        }
    }

    public void render(Graphics graphics) {
        for (GameObject object : objects) {
            object.render(graphics);
        }
    }

    public void addObject(GameObject object) {
        objects.add(object);
    }

    public List<GameObject> get(ObjectType type) {
        return objects.stream()
                .filter((o) -> o.is(type))
                .collect(Collectors.toList());
    }
}
