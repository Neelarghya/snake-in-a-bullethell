package game.controller;

import game.model.object.GameObject;
import game.model.object.ObjectType;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Handler {
    private List<GameObject> objects;

    public Handler() {
        objects = new ArrayList<>();
    }

    void tick() {
        for (GameObject object : objects) {
            object.tick();
        }
    }

    void render(Graphics graphics) {
        for (GameObject object : objects) {
            object.render(graphics);
        }
    }

    void addObject(GameObject object) {
        objects.add(object);
    }

    public List<GameObject> get(ObjectType type) {
        return objects.stream()
                .filter((o) -> o.is(type))
                .collect(Collectors.toList());
    }
}
