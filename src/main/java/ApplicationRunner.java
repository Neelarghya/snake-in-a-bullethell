import game.common.Constant;
import game.controller.Game;
import game.input.GameKeyInput;
import game.input.MovementKeyInput;
import game.model.object.Handler;
import game.view.ui.HeadsUpDisplay;

public class ApplicationRunner {
    public static void main(String[] args) {
        new Constant();
        new Game(new GameKeyInput(), new MovementKeyInput(), new Handler(), new HeadsUpDisplay());
    }
}
