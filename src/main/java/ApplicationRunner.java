import game.common.Constant;
import game.controller.Game;
import game.input.KeyInput;
import game.model.object.Handler;

public class ApplicationRunner {
    public static void main(String[] args) {
        new Constant();
        new Game(new KeyInput(), new Handler());
    }
}
