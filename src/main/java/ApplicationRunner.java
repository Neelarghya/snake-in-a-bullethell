import game.common.Constant;
import game.controller.Game;
import game.controller.Handler;

public class ApplicationRunner {
    public static void main(String[] args) {
        new Constant();
        Handler handler = new Handler();
        new Game(handler);
    }
}
