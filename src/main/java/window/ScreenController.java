package window;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.HashMap;

public class ScreenController {

    private HashMap<GameScene, Scene> screenMap = new HashMap<>();
    private Stage stage;
    private static ScreenController instance;

    public enum GameScene {
        MAIN_MENU,
        GAME_PLAY,
        GAVE_OVER,
        RECORD_MENU,
    }

    private ScreenController() {

    }

    public void addScreen(GameScene screen, Scene scene) {
        screenMap.put(screen, scene);
    }

    public void removeScreen(GameScene scene) {
        screenMap.remove(scene);
    }

    public void activate(GameScene screen) {
        stage.setScene(screenMap.get(screen));
    }

    public ScreenController init(Stage stage){
        this.stage = stage;

        return this;
    }

    public static ScreenController getScreenController() {
        if (instance == null) {
            instance = new ScreenController();
        }
        return instance;
    }

}
