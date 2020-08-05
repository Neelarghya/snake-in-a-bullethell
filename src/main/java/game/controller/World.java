package game.controller;

import game.input.MovementKeyInput;
import game.model.behaviour.collision.Collision;
import game.model.behaviour.movement.keymovement.MacroKeyMovementBehaviour;
import game.model.behaviour.visual.ResetColor;
import game.model.object.generator.GameObjectGenerator;
import game.model.object.Handler;
import game.model.object.generator.PeriodicGenerator;
import game.model.object.behavingobject.immovable.Collectable;
import game.model.object.behavingobject.immovable.HealthCollectable;
import game.model.object.behavingobject.movable.Enemy;
import game.model.object.behavingobject.movable.Player;
import game.model.object.behavingobject.movable.Trail;
import game.view.ui.components.HealthBar;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static game.common.Constant.WINDOW_HEIGHT;
import static game.common.Constant.WINDOW_WIDTH;
import static game.common.CustomColors.snakeBlue;
import static game.model.object.ObjectType.ENEMY;
import static game.model.object.ObjectType.PLAYER;

class World {
    private final Random random;
    private final Handler handler;
    private final MovementKeyInput movementKeyInput;
    private final List<PeriodicGenerator> periodicGenerators;

    World(Handler handler, MovementKeyInput movementKeyInput, HealthBar healthBar) {
        this.handler = handler;
        this.movementKeyInput = movementKeyInput;
        this.random = new Random();
        this.periodicGenerators = new ArrayList<>();
        build(handler, healthBar);
    }

    private void build(Handler handler, HealthBar healthBar) {
        Player player = addPlayer(handler, healthBar);
        addAllRandomEnemy(handler);
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

        PeriodicGenerator healthCollectableGenerator = new PeriodicGenerator(4, 400, handler, collectableGenerator, true);
        periodicGenerators.add(healthCollectableGenerator);
    }

    private void addAllRandomEnemy(Handler handler) {
        int numberOfEnemies = random.nextInt(2) + 1;
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
        PeriodicGenerator periodicEnemyGenerator = new PeriodicGenerator(2, 500, handler, enemyGenerator, false);
        periodicGenerators.add(periodicEnemyGenerator);
    }

    private Player addPlayer(Handler handler, HealthBar healthBar) {
        Player player = new Player(random.nextInt(WINDOW_WIDTH), random.nextInt(WINDOW_HEIGHT), snakeBlue);
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
        player.addObserver(healthBar);

        addTrail(handler, player);
        handler.addObject(player);
        return player;
    }

    private void addTrail(Handler handler, Player player) {
        Trail trail = new Trail(player);
        trail.build(6, 50, 6, 8, snakeBlue);
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
