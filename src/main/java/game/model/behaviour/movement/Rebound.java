package game.model.behaviour.movement;

import game.model.object.movable.MovableObject;

import static game.common.Constant.WINDOW_HEIGHT;
import static game.common.Constant.WINDOW_WIDTH;

public class Rebound extends Movement {
    public Rebound(MovableObject object) {
        super(object);
    }

    @Override
    public void behave() {
        double x = object.getX();
        double y = object.getY();

        if (x <= 0 || x >= WINDOW_WIDTH) object.reboundX();
        if (y <= 0 || y >= WINDOW_HEIGHT) object.reboundY();
    }
}
