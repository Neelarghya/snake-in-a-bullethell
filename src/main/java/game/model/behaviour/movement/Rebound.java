package game.model.behaviour.movement;

import game.model.object.behavingobject.movable.MovableObject;

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

        double halfWidth = object.getWidth() / 2.0;
        double halfHeight = object.getHeight() / 2.0;

        if (x <= halfWidth || x >= WINDOW_WIDTH - halfWidth) object.reboundX();
        if (y <= halfHeight || y >= WINDOW_HEIGHT - halfHeight) object.reboundY();
    }
}
