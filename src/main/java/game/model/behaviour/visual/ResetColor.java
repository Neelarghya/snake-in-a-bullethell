package game.model.behaviour.visual;

import game.model.behaviour.Behaviour;
import game.model.object.ColorResettable;

import java.awt.*;

public class ResetColor implements Behaviour {
    private ColorResettable object;
    private final int resetTime;
    private int setTimer;

    public ResetColor(ColorResettable object, int resetTime) {
        this.object = object;
        this.resetTime = resetTime;
        this.setTimer = 0;
    }

    @Override
    public void behave() {
        if (setTimer > 0) {
            setTimer--;
            if (setTimer == 0) object.reSetColor();
        }
    }

    public void setColor(Color color) {
        object.setColor(color);
        setTimer = resetTime;
    }
}
