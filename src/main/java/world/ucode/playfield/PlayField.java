package world.ucode.playfield;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import world.ucode.playfield.object.Dino;
import world.ucode.playfield.object.GameObject;
import world.ucode.playfield.object.Grounds;

import java.io.File;
import java.util.*;

public class PlayField {

    @FXML
    private Label scoreLabel;
    @FXML
    private Canvas fieldCanvas;

    private LinkedList<GameObject> gameObjects = new LinkedList<>();
    private Hashtable<String, Boolean> activeKeys = new Hashtable<>();
    private static final double START_SPEED = 10;
    private static final double VELOCITY = 0.005;
    private static final double MAX_SPEED = 25;
    public static double speed = START_SPEED;
    private int score;

    @FXML
    public void initialize() {
        initObjects();
        initKeys();
    }

    private void initKeys() {
        activeKeys.put("DOWN", false);
        activeKeys.put("UP", false);
    }

    private void initObjects() {
        gameObjects.add(new Grounds());
        gameObjects.add(new Dino());
    }

    private void initHandlers() {
        fieldCanvas.getScene().setOnKeyPressed(event -> {
            String code = event.getCode().toString();

            activeKeys.put(code, true);
        });
        fieldCanvas.getScene().setOnKeyReleased(event -> {
            String code = event.getCode().toString();

            activeKeys.put(code, false);
        });
    }

    public void startGame() {
        initHandlers();
        GraphicsContext gc = fieldCanvas.getGraphicsContext2D();

        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                gc.clearRect(0, 0, fieldCanvas.getWidth(), fieldCanvas.getHeight());
                gameObjects.forEach(el -> {
                    el.draw(gc);
                    el.update(activeKeys);
                });
                scoreLabel.setText("Score: " + score);
                score++;
                if (speed < MAX_SPEED) {
                    speed += VELOCITY;
                }
            }
        }.start();

    }


}
