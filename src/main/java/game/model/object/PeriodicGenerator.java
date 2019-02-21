package game.model.object;

import java.util.Random;

public class PeriodicGenerator {
    private final int quantityAtATime;
    private final int delay;
    private final Handler handler;
    private final GameObjectGenerator generator;
    private final boolean randomize;
    private final Random random;

    private int counter;

    public PeriodicGenerator(int quantityAtATime, int delay, Handler handler, GameObjectGenerator generator, boolean randomize) {
        this.quantityAtATime = quantityAtATime;
        this.delay = delay;
        this.handler = handler;
        this.generator = generator;
        this.randomize = randomize;
        this.random = new Random();
        this.counter = 0;
    }

    public void tick() {
        if (counter > delay) {
            int quantity = (int) Math.round(quantityAtATime * (randomize ? random.nextDouble() : 1));
            for (int index = 0; index < quantity; index++) {
                handler.addObject(generator.generate());
            }
            counter = 0;
        }

        counter++;
    }
}
