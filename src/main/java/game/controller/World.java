package game.controller;

import game.input.KeyInput;
import game.model.behaviour.movement.keymovement.MacroKeyMovementBehaviour;
import game.model.object.Handler;
import game.model.object.movable.Enemy;
import game.model.object.movable.Player;
import game.model.object.movable.Trail;

import java.awt.*;
import java.util.Random;

import static game.common.Constant.WINDOW_HEIGHT;
import static game.common.Constant.WINDOW_WIDTH;

class World {
    private Handler handler;
    private KeyInput keyInput;
    private Random random;

    World(Handler handler, KeyInput keyInput) {
        this.handler = handler;
        this.keyInput = keyInput;
        this.random = new Random();
        build(handler);
    }

    private void build(Handler handler) {
        addPlayer(handler);
        addRandomEnemy(handler);
        addRandomEnemy(handler);
        addRandomEnemy(handler);
        addRandomEnemy(handler);
        addRandomEnemy(handler);
    }

    private void addRandomEnemy(Handler handler) {
        handler.addObject(new Enemy(random.nextInt(WINDOW_WIDTH), random.nextInt(WINDOW_HEIGHT)));
    }

    private void addPlayer(Handler handler) {
        Player player = new Player(random.nextInt(WINDOW_WIDTH), random.nextInt(WINDOW_HEIGHT));
        MacroKeyMovementBehaviour macroKeyMovementBehaviour = new MacroKeyMovementBehaviour(player);
        player.addBehaviour(macroKeyMovementBehaviour);
        keyInput.addBehaviour(macroKeyMovementBehaviour);

        handler.addObject(player);
        Trail trail = new Trail(player);
        trail.build(7, 40, 5, 8);
        handler.addObject(trail);
    }

    void tick() {
        handler.tick();
    }

    void render(Graphics graphics) {
        handler.render(graphics);
    }
}
