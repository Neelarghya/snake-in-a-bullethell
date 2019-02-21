package game.view.ui;

import game.common.Observable;
import game.common.Observer;
import game.model.object.ObjectType;
import game.model.object.movable.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static game.model.object.ObjectType.HUD;
import static game.model.object.ObjectType.PLAYER;

public class HeadsUpDisplay implements Observer, Observable {
    private static final int MAX_WIDTH = 200;
    private static final int HEIGHT = 25;
    private static final int X_OFFSET = 30;
    private static final int Y_OFFSET = 30;

    private double playerHealth;
    private int width;
    private final List<Observer> observers;

    public HeadsUpDisplay() {
        this.width = 0;
        this.playerHealth = 0;
        observers = new ArrayList<>();
    }

    public void tick() {
        width = (int) (playerHealth / 100 * MAX_WIDTH);
    }

    public void render(Graphics graphics) {
        Color translucentGrey = new Color(0.7f, 0.7f, 0.7f, .6f);
        graphics.setColor(translucentGrey);
        graphics.fillRect(X_OFFSET, Y_OFFSET, MAX_WIDTH, HEIGHT);

        Color translucentGreen = new Color(0.0f, 1f, 0.0f, .5f);
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
        observers.forEach((observer) -> observer.update(this));
    }

    @Override
    public ObjectType is() {
        return HUD;
    }
}
