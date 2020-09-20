package world.ucode.playfield;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import world.ucode.over.GameOver;
import world.ucode.playfield.object.*;
import world.ucode.utils.ObjectTimer;
import world.ucode.window.ScreenController;

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
    private static final double VELOCITY = 0.0015;
    private static final double MAX_SPEED = 45;
    public static double speed = START_SPEED;
    private ObjectTimer timer = new ObjectTimer();
    private int score;

    @FXML
    public void initialize() {
        initObjects();
        initKeys();
    }

    private void initKeys() {
        activeKeys.put("DOWN", false);
        activeKeys.put("UP", false);
        activeKeys.put("SPACE", false);
    }

    private void initObjects() {
        gameObjects.add(new Clouds());
        gameObjects.add(new Grounds());
        gameObjects.add(new Obstacles());
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

    private void reset() {
        gameObjects.clear();
        speed = START_SPEED;
        score = 0;
        initObjects();
        activeKeys.clear();
        initKeys();
    }

    public void startGame() {
        reset();
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
                gameObjects.forEach(el -> {
                    gameObjects.forEach(el2 -> {
                        if (el.getRect() == null || el2.getRect() == null || el == el2) {
                            return;
                        }
                        if (el.getRect().intersects(el2.getRect()) || el2.getRect().intersects(el.getRect())) {
                            this.stop();
                            ScreenController screenController = ScreenController.getScreenController();

                            FXMLLoader ctrl = screenController.activate(ScreenController.GameScene.GAVE_OVER);
                            GameOver over = ctrl.getController();
                            over.setScore(score);
                        }
                    });
                });
                if (timer.isGone(ObjectTimer.SECOND / 4)) {
                    score++;
                    scoreLabel.setText("Score: "+score);
                }
                if (speed < MAX_SPEED) {
                    speed += VELOCITY;
                }
            }
        }.start();

    }


}
