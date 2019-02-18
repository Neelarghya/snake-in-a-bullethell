package game.view;

import game.controller.Game;

import javax.swing.*;
import java.awt.*;

public class Window extends Canvas {
    private final JFrame frame;

    public Window(int width, int height, String title, Game game) {
        frame = new JFrame(title);
        setupFrame(width, height, game);
        game.start();
    }

    private void setupFrame(int width, int height, Game game) {
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
    }
}
