package game.view.ui.components;

import game.common.CustomColors;

import java.awt.*;

public class ScoreBoard implements HeadsUpDisplayComponent {
    private long score;

    public ScoreBoard() {
        this.score = 0;
    }

    @Override
    public void tick() {
        score++;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(CustomColors.translucentWhite);
        graphics.setFont(new Font("TimesRoman", Font.BOLD, 30));
        graphics.drawString(String.valueOf(score), 850, 40);
    }
}
