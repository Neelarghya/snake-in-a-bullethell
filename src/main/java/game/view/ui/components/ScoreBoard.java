package game.view.ui.components;

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
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("TimesRoman", Font.BOLD, 20));
        graphics.drawString(String.valueOf(score), 900, 40);
    }
}
