package game.controller;

import game.input.GameKeyInput;
import game.input.MovementKeyInput;
import game.model.object.Handler;
import game.view.Window;
import game.view.ui.HeadsUpDisplay;

import java.awt.*;
import java.awt.image.BufferStrategy;

import static game.common.Constant.*;

public class Game extends Canvas implements Runnable {
    private Thread thread;
    private World world;
    private boolean running;
    private HeadsUpDisplay headsUpDisplay;
    private boolean closed = false;
    private Window window;

    public Game(GameKeyInput gameKeyInput, MovementKeyInput movementKeyInput, Handler handler) {
        headsUpDisplay = new HeadsUpDisplay();
        headsUpDisplay.addObserver(observable -> close());
        running = false;
        world = new World(handler, movementKeyInput, headsUpDisplay);
        gameKeyInput.setTogglePauseAction(this::togglePause);
        gameKeyInput.setCloseAction(this::close);
        this.addKeyListener(movementKeyInput);
        this.addKeyListener(gameKeyInput);
        window = new Window(WINDOW_WIDTH, WINDOW_HEIGHT, TITLE, this);
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    private synchronized void stop() {
        try {
//            window.close();
            running = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        this.requestFocus();
        while (!closed) {
            runGameLoop();
        }
        stop();
    }

    private void runGameLoop() {
        long lastTime = System.nanoTime();
        double ticksPerSecond = 60.0;
        double nanoSecondsPerFrame = 1000000000 / ticksPerSecond;
        double delta = 0;
        long timer = System.currentTimeMillis();

        while (!closed && running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nanoSecondsPerFrame;
            lastTime = now;

            while (delta >= 1) {
                tick();
                render();
                delta--;
            }

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
            }
        }
    }

    private void tick() {
        world.tick();
        headsUpDisplay.tick();
    }

    private void render() {
        BufferStrategy bufferStrategy = this.getBufferStrategy();
        if (null == bufferStrategy) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics graphics = bufferStrategy.getDrawGraphics();
        draw(graphics);
        graphics.dispose();
        bufferStrategy.show();
    }

    private void draw(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        world.render(graphics);
        headsUpDisplay.render(graphics);
    }

    private void togglePause() {
        running = !running;
    }

    private void close() {
        closed = true;
    }
}
