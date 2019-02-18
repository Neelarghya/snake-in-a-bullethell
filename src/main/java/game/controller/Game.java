package game.controller;

import game.input.KeyInput;
import game.view.Window;

import java.awt.*;
import java.awt.image.BufferStrategy;

import static game.common.Constant.*;

public class Game extends Canvas implements Runnable {
    private Thread thread;
    private boolean running;
    private World world;

    public Game(Handler handler) {
        running = false;
        world = new World(handler);
        KeyInput keyInput = new KeyInput(world.getMacroKeyMovementBehaviour());
        this.addKeyListener(keyInput);
        new Window(WINDOW_WIDTH, WINDOW_HEIGHT, TITLE, this);
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        runGameLoop();
    }

    private void runGameLoop() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running)
                render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
//                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
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
    }

    private void tick() {
        world.tick();
    }
}
