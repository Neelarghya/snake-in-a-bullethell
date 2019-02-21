package game.view.ui.components;

import game.common.Observable;
import game.common.Observer;
import game.model.object.ObjectType;
import game.model.object.movable.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static game.common.CustomColors.translucentGreen;
import static game.common.CustomColors.translucentGrey;
import static game.model.object.ObjectType.HUD;
import static game.model.object.ObjectType.PLAYER;

public class HealthBar implements HeadsUpDisplayComponent, Observer, Observable {
    private static final int MAX_WIDTH = 200;
    private static final int HEIGHT = 25;
    private static final int X_OFFSET = 30;
    private static final int Y_OFFSET = 30;

    private double playerHealth;
    private int width;
    private final List<Observer> observers;

    public HealthBar() {
        this.width = 0;
        this.playerHealth = 0;
        observers = new ArrayList<>();
    }

    @Override
    public void tick() {
        width = (int) (playerHealth / Player.MAX_HEALTH * MAX_WIDTH);
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(translucentGrey);
        graphics.fillRect(X_OFFSET, Y_OFFSET, MAX_WIDTH, HEIGHT);

        graphics.setColor(translucentGreen);
        graphics.fillRect(X_OFFSET, Y_OFFSET, width, HEIGHT);

        graphics.setColor(Color.WHITE);
        graphics.drawRect(X_OFFSET, Y_OFFSET, MAX_WIDTH, HEIGHT);
    }

    @Override
    public void update(Observable observable) {
        if (observable.is() == PLAYER) {
            playerHealth = ((Player) observable).getHealth();
        }

        if (playerHealth == 0) notifyObservers();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(observer -> observer.update(this));
    }

    @Override
    public ObjectType is() {
        return HUD;
    }
}
