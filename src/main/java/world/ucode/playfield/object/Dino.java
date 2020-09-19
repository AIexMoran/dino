package world.ucode.playfield.object;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class Dino implements GameObject {

    private static HashMap<DinoState, ImageView> statesMap = new HashMap<>();
    private static final double WIDTH = 150;
    private static final double HEIGHT = 150;
    private static final double SPEED = 15;
    private static final double LEG_SECOND = 500 / SPEED * 7;
    private Direction direction = Direction.DEFAULT;
    private DinoState currentState = DinoState.UP_LEFT;
    private static final double START_Y = 350;
    private static final double MAX_Y = 280;
    private final double x = 50;
    private double y = START_Y;
    private long lastTime = 0;


    private enum DinoState {
        UP_LEFT,
        UP_RIGHT,
        DOWN_LEFT,
        DOWN_RIGHT
    }

    private enum Direction {
        UP,
        DOWN,
        LIE,
        DEFAULT
    }

    static {
        initImage(DinoState.UP_LEFT, "assets/upLeft.png");
        initImage(DinoState.UP_RIGHT, "assets/upRight.png");
        initImage(DinoState.DOWN_LEFT, "assets/downLeft.png");
        initImage(DinoState.DOWN_RIGHT, "assets/downRight.png");
    }

    private static void initImage(DinoState state, String asset) {
        Image image = new Image(asset, WIDTH, HEIGHT, true, true);
        ImageView imageView = new ImageView(image);

        statesMap.put(state, imageView);
    }

    public Dino() {

    }

    @Override
    public void update(Hashtable<String, Boolean> activeKeys) {
        if (direction == Direction.DEFAULT || direction == Direction.LIE) {
            if (System.currentTimeMillis() - lastTime >= LEG_SECOND) {
                if (direction == Direction.DEFAULT) {
                    if (currentState == DinoState.UP_LEFT) {
                        currentState = DinoState.UP_RIGHT;
                    } else {
                        currentState = DinoState.UP_LEFT;
                    }
                } else if (direction == Direction.LIE) {
                    if (currentState == DinoState.DOWN_LEFT) {
                        currentState = DinoState.DOWN_RIGHT;
                    } else {
                        currentState = DinoState.DOWN_LEFT;
                    }
                }
                lastTime = System.currentTimeMillis();
            }
        }
        if (activeKeys.get("UP") && direction == Direction.DEFAULT) {
            this.direction = Direction.UP;
        } else if (activeKeys.get("DOWN") && direction == Direction.DEFAULT) {
            this.direction = Direction.LIE;
            currentState = DinoState.DOWN_LEFT;
        } else if (this.direction == Direction.LIE && !activeKeys.get("DOWN")) {
            this.direction = Direction.DEFAULT;
            currentState = DinoState.UP_LEFT;
        }
        if (direction == Direction.UP && y > START_Y - MAX_Y) {
            y -= SPEED;
        } else if (direction == Direction.UP && y <= START_Y - MAX_Y) {
            direction = Direction.DOWN;
        }
        if (direction == Direction.DOWN && y < START_Y) {
            y += SPEED;
        } else if (direction == Direction.DOWN && y >= START_Y) {
            direction = Direction.DEFAULT;
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        if (direction == Direction.LIE) {
            gc.drawImage(statesMap.get(currentState).getImage(), x, y + 77);
            return;
        }
        gc.drawImage(statesMap.get(currentState).getImage(), x, y);
    }
}
