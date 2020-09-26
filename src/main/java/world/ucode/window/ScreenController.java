package world.ucode.window;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.HashMap;

public class ScreenController {

    private HashMap<GameScene, Scene> scenesMap = new HashMap<>();
    private HashMap<GameScene, FXMLLoader> loadersMap = new HashMap<>();
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

    public void addScreen(GameScene screen, String resource) {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource(resource));
        try {
            scenesMap.put(screen, new Scene(loader.load()));
            loadersMap.put(screen, loader);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void removeScreen(GameScene scene) {
        scenesMap.remove(scene);
        loadersMap.remove(scene);
    }

    public FXMLLoader activate(GameScene screen) {
        stage.setScene(scenesMap.get(screen));
        return loadersMap.get(screen);
    }

    public ScreenController init(Stage stage){
        this.stage = stage;
        stage.setResizable(false);
        return this;
    }

    public static ScreenController getScreenController() {
        if (instance == null) {
            instance = new ScreenController();
        }
        return instance;
    }

}
