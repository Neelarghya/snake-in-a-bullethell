package game.controller;

import game.input.MovementKeyInput;
import game.model.behaviour.collision.Collision;
import game.model.behaviour.movement.keymovement.MacroKeyMovementBehaviour;
import game.model.behaviour.visual.ResetColor;
import game.model.object.GameObjectGenerator;
import game.model.object.Handler;
import game.model.object.PeriodicGenerator;
import game.model.object.immovable.Collectable;
import game.model.object.immovable.HealthCollectable;
import game.model.object.movable.Enemy;
import game.model.object.movable.Player;
import game.model.object.movable.Trail;
import game.view.ui.HeadsUpDisplay;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static game.common.Constant.WINDOW_HEIGHT;
import static game.common.Constant.WINDOW_WIDTH;
import static game.model.object.ObjectType.ENEMY;
import static game.model.object.ObjectType.PLAYER;

class World {
    private Handler handler;
    private MovementKeyInput movementKeyInput;
    private Random random;
    private List<PeriodicGenerator> periodicGenerators;

    World(Handler handler, MovementKeyInput movementKeyInput, HeadsUpDisplay headsUpDisplay) {
        this.handler = handler;
        this.movementKeyInput = movementKeyInput;
        this.random = new Random();
        this.periodicGenerators = new ArrayList<>();
        build(handler, headsUpDisplay);
    }

    private void build(Handler handler, HeadsUpDisplay headsUpDisplay) {
        Player player = addPlayer(handler, headsUpDisplay);
//        addAllRandomEnemy(handler);
        periodicallyAddRandomEnemy(handler);
        addHealthCollectables(handler, player);
    }

    private void addHealthCollectables(Handler handler, Player player) {
        GameObjectGenerator collectableGenerator = () -> {
            Collectable collectable = new HealthCollectable(16 + random.nextInt(WINDOW_WIDTH - 32), 16 + random.nextInt(WINDOW_HEIGHT - 32));
            Collision collision = new Collision(collectable, handler, PLAYER) {
                @Override
                protected void onCollide() {
                    player.gainHealth();
                    handler.remove(collectable);
                }
            };
            collectable.addBehaviour(collision);
            return collectable;
        };

        PeriodicGenerator healthCollectableGenerator = new PeriodicGenerator(3, 400, handler, collectableGenerator, true);
        periodicGenerators.add(healthCollectableGenerator);
    }

    private void addAllRandomEnemy(Handler handler) {
        int numberOfEnemies = random.nextInt(5) + 5;
        for (int i = 0; i < numberOfEnemies; i++) {
            handler.addObject(new Enemy(random.nextInt(WINDOW_WIDTH), random.nextInt(WINDOW_HEIGHT)));
        }
    }

    private void periodicallyAddRandomEnemy(Handler handler) {
        GameObjectGenerator enemyGenerator = () -> {
            int expectedThickness = 10;
            int x = (int) (expectedThickness + Math.round(random.nextDouble()) * (WINDOW_WIDTH - 2 * expectedThickness));
            int y = (int) (expectedThickness + Math.round(random.nextDouble()) * (WINDOW_HEIGHT - 2 * expectedThickness));
            if (random.nextBoolean()) {
                x = random.nextInt(WINDOW_WIDTH);
            } else {
                y = random.nextInt(WINDOW_HEIGHT);
            }
            return new Enemy(x, y);
        };
        PeriodicGenerator periodicEnemyGenerator = new PeriodicGenerator(1, 500, handler, enemyGenerator, false);
        periodicGenerators.add(periodicEnemyGenerator);
    }

    private Player addPlayer(Handler handler, HeadsUpDisplay headsUpDisplay) {
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
        movementKeyInput.addBehaviour(macroKeyMovementBehaviour);
        player.addBehaviour(macroKeyMovementBehaviour);
        player.addBehaviour(collisionForPlayer);
        player.addBehaviour(resetColor);
        player.addObserver(headsUpDisplay);

        addTrail(handler, player);
        handler.addObject(player);
        return player;
    }

    private void addTrail(Handler handler, Player player) {
        Trail trail = new Trail(player);
        trail.build(6, 50, 6, 8, Color.BLUE);
        trail.addCollision(handler, ENEMY, player::takeDamage, Color.RED);
        handler.addObject(trail);
    }

    void tick() {
        handler.tick();
        periodicGenerators.forEach(PeriodicGenerator::tick);
    }

    void render(Graphics graphics) {
        handler.render(graphics);
    }
}
