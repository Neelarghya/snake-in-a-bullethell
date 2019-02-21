package game.view.ui;

import game.view.ui.components.HeadsUpDisplayComponent;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class HeadsUpDisplay implements HeadsUpDisplayComponent{
    private List<HeadsUpDisplayComponent> components;

    public HeadsUpDisplay() {
        this.components = new ArrayList<>();
    }

    @Override
    public void tick() {
        components.forEach(HeadsUpDisplayComponent::tick);
    }

    @Override
    public void render(Graphics graphics) {
        components.forEach(component -> component.render(graphics));
    }

    public void addComponent(HeadsUpDisplayComponent component){
        components.add(component);
    }
}
