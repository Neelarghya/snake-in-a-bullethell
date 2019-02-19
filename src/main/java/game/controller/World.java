package game.controller;

import game.input.KeyInput;
import game.model.MovementConstants;
import game.model.behaviour.movement.Follow;
import game.model.behaviour.movement.keymovement.MacroKeyMovementBehaviour;
import game.model.object.GameObject;
import game.model.object.Handler;
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
    }

    private void addPlayer(Handler handler) {
        int numberOfEnemies = 50;
        Player player = new Player(random.nextInt(WINDOW_WIDTH), random.nextInt(WINDOW_HEIGHT));
        MacroKeyMovementBehaviour macroKeyMovementBehaviour = new MacroKeyMovementBehaviour(player);
        player.addBehaviour(macroKeyMovementBehaviour);
        keyInput.addBehaviour(macroKeyMovementBehaviour);

        handler.addObject(player);
        addTrail(handler, numberOfEnemies, player);
    }

    private void addTrail(Handler handler, int numberOfEnemies, Player player) {
        GameObject lastObject = player;

        for (int index = 0; index < numberOfEnemies - 35; index++) {
            double percentage = (double) (numberOfEnemies - index - 1) / numberOfEnemies;
            double acceleration = (percentage) * 1 + 3;
            double maxSpeed = (percentage) * 3 + 5;
            MovementConstants movementConstants = new MovementConstants(acceleration, acceleration, maxSpeed, .96);

            Trail trail = new Trail(random.nextDouble() * WINDOW_WIDTH, random.nextDouble() * WINDOW_HEIGHT, movementConstants);
            trail.addBehaviour(new Follow(trail, lastObject));
            handler.addObject(trail);
            lastObject = trail;
        }
    }

    void tick() {
        handler.tick();
    }

    void render(Graphics graphics) {
        handler.render(graphics);
    }
}
