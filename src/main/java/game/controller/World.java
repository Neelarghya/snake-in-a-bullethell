package game.controller;

import game.input.KeyInput;
import game.model.behaviour.movement.keymovement.MacroKeyMovementBehaviour;
import game.view.ui.HeadsUpDisplay;
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

    World(Handler handler, KeyInput keyInput, HeadsUpDisplay headsUpDisplay) {
        this.handler = handler;
        this.keyInput = keyInput;
        this.random = new Random();
        build(handler, headsUpDisplay);
    }

    private void build(Handler handler, HeadsUpDisplay headsUpDisplay) {
        addPlayer(handler, headsUpDisplay);
        for (int i = 0; i < 6; i++) {
            addRandomEnemy(handler);
        }
    }

    private void addRandomEnemy(Handler handler) {
        handler.addObject(new Enemy(random.nextInt(WINDOW_WIDTH), random.nextInt(WINDOW_HEIGHT)));
    }

    private void addPlayer(Handler handler, HeadsUpDisplay headsUpDisplay) {
        Player player = new Player(random.nextInt(WINDOW_WIDTH), random.nextInt(WINDOW_HEIGHT));
        MacroKeyMovementBehaviour macroKeyMovementBehaviour = new MacroKeyMovementBehaviour(player);
        keyInput.addBehaviour(macroKeyMovementBehaviour);
        player.addBehaviour(macroKeyMovementBehaviour);
        player.addObserver(headsUpDisplay);

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
