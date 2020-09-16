package window;

import menu.MainScene;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.util.HashMap;

public class Window {

    private static final int WIDTH = 1800;
    private static final int HEIGHT = 900;
    private static final String WINDOW_NAME = "Game";

    private HashMap<Scenes, JPanel> scenes;
    private JFrame frame;

    public enum Scenes {
        MAIN_SCENE,
        GAME_SCENE,
        GAME_OVER_SCENE
    }

    public void close() {
        this.frame.dispatchEvent(new WindowEvent(this.frame, WindowEvent.WINDOW_CLOSING));
    }

    public void setScene(Scenes scene) {
        this.frame.getContentPane().removeAll();
        this.frame.getContentPane().add(scenes.get(scene));
    }

    private void initScenes() {
        scenes.put(Scenes.MAIN_SCENE, MainScene.getMainScene(this).getPanel());
    }

    public Window init() {
        this.frame = new JFrame(WINDOW_NAME);
        this.frame.setSize(WIDTH, HEIGHT);
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.scenes = new HashMap<>();

        this.initScenes();
        this.setScene(Scenes.MAIN_SCENE);
        return this;
    }



    public Window start() {
        this.frame.setVisible(true);

        return this;
    }

}
