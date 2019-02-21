package game.view;

import game.controller.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class Window extends Canvas {
    private final JFrame frame;

    public Window(int width, int height, String title, Game game) {
        frame = new JFrame(title);
        setupFrame(width, height, game);
        game.start();
    }

    private void setupFrame(int width, int height, Game game) {
        frame.getContentPane().setPreferredSize(new Dimension(width, height));
        frame.getContentPane().setMaximumSize(new Dimension(width, height));
        frame.getContentPane().setMinimumSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
    }

    public void close(){
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }
}
