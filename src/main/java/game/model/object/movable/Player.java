package game.model.object.movable;

import game.common.Observable;
import game.common.Observer;
import game.model.MovementConstants;
import game.model.object.ObjectType;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static game.model.object.ObjectType.PLAYER;

public class Player extends MovableObject implements Observable {
    private static final int WIDTH = 40;
    private static final int HEIGHT = 40;
    private static final double DAMAGE_TAKEN_PER_HIT = 0.5;

    private final List<Observer> observers;
    private double health;

    public Player(double x, double y) {
        super(x, y, WIDTH, HEIGHT, PLAYER, new MovementConstants(3, 3, 8, .94));
        health = 100;
        observers = new ArrayList<>();
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.BLUE);
        graphics.fillRect((int) Math.round(x - WIDTH / 2.0), (int) Math.round(y - HEIGHT / 2.0), WIDTH, HEIGHT);
    }

    public double getHealth() {
        return health;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
        notifyObservers();
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
        return PLAYER;
    }

    public void takeDamage() {
        health = health > 0 ? health - DAMAGE_TAKEN_PER_HIT : 0;
        System.out.println("Health : " + health);
        notifyObservers();
    }
}
