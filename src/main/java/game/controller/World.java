package game.controller;

import game.input.KeyInput;
import game.model.behaviour.visual.ResetColor;
import game.model.behaviour.collision.Collision;
import game.model.behaviour.movement.keymovement.MacroKeyMovementBehaviour;
import game.model.object.Handler;
import game.model.object.movable.Enemy;
import game.model.object.movable.Player;
import game.model.object.movable.Trail;
import game.view.ui.HeadsUpDisplay;

import java.awt.*;
import java.util.Random;

import static game.common.Constant.WINDOW_HEIGHT;
import static game.common.Constant.WINDOW_WIDTH;
import static game.model.object.ObjectType.ENEMY;

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
        int numberOfEnemies = random.nextInt(5) + 5;
        for (int i = 0; i < numberOfEnemies; i++) {
            addRandomEnemy(handler);
        }
    }

    private void addRandomEnemy(Handler handler) {
        handler.addObject(new Enemy(random.nextInt(WINDOW_WIDTH), random.nextInt(WINDOW_HEIGHT)));
    }

    private void addPlayer(Handler handler, HeadsUpDisplay headsUpDisplay) {
        Player player = new Player(random.nextInt(WINDOW_WIDTH), random.nextInt(WINDOW_HEIGHT), Color.BLUE);
        MacroKeyMovementBehaviour macroKeyMovementBehaviour = new MacroKeyMovementBehaviour(player);
        ResetColor resetColor = new ResetColor(player, 20);
        Collision collisionForPlayer = new Collision(player, handler, ENEMY) {
            @Override
            protected void onCollide() {
                player.takeDamage();
                resetColor.setColor(Color.RED);
            }
        };
        keyInput.addBehaviour(macroKeyMovementBehaviour);
        player.addBehaviour(macroKeyMovementBehaviour);
        player.addBehaviour(collisionForPlayer);
        player.addBehaviour(resetColor);
        player.addObserver(headsUpDisplay);
        handler.addObject(player);

        addTrail(handler, player);
    }

    private void addTrail(Handler handler, Player player) {
        Trail trail = new Trail(player);
        trail.build(7, 40, 5, 8, Color.BLUE);
        trail.addCollision(handler, ENEMY, player::takeDamage, Color.RED);
        handler.addObject(trail);
    }

    void tick() {
        handler.tick();
    }

    void render(Graphics graphics) {
        handler.render(graphics);
    }
}
