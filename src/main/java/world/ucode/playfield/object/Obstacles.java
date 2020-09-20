package world.ucode.playfield.object;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Random;

import static world.ucode.playfield.PlayField.speed;

public class Obstacles implements GameObject {

    private static ImageView[] cactusAssets = new ImageView[2];
    private LinkedList<Obstacle> obstacles = new LinkedList<>();
    private static double WIDTH = 100;
    private static double HEIGHT = 100;
    private static double gapObstacles = 500;
    private static final double START_POS = 1500;
    private static final double START_SMALL_Y = 440;
    private static final double START_BIG_Y = 400;
    private Obstacle current;
    private Random rand = new Random();

    static {
        initImage(0, "assets/cactus1.png");
        initImage(1, "assets/cactus2.png");
    }

    private static void initImage(int index, String asset) {
        Image image = new Image(asset, WIDTH, HEIGHT, true, true);

        cactusAssets[index] = new ImageView(image);
    }

    private double genGap() {
        return (gapObstacles * speed * 0.2) * 0.4 + rand.nextInt(800);
    }

    public Obstacles() {
        Random r = new Random();

        for (int i = 0; i < 4; i++) {
            int randInt = r.nextInt(cactusAssets.length);

            if (randInt == 1) {
                obstacles.add(new Obstacle(cactusAssets[randInt], START_POS + gapObstacles * i,START_SMALL_Y));
                continue;
            }
            obstacles.add(new Obstacle(cactusAssets[randInt], START_POS + gapObstacles * i,START_BIG_Y));
        }
        current = obstacles.getLast();
    }

    @Override
    public void update(Hashtable<String, Boolean> activeKeys) {
        Obstacle first = obstacles.get(0);

        if (-first.x >= first.width) {
            first.x = obstacles.peekLast().x + genGap();
            obstacles.addLast(first);
            obstacles.remove(0);
        }
        obstacles.forEach(el -> {
            el.x -= speed;
        });
    }

    @Override
    public void draw(GraphicsContext gc) {
        obstacles.forEach(el -> {
            gc.drawImage(el.asset.getImage(), el.x, el.y);
        });
    }

    @Override
    public Rectangle2D getRect() {
        return obstacles.get(0).getRect();
    }

    class Obstacle {
        public double x;
        public double y;
        public double width;
        public double height;
        private ImageView asset;

        public Obstacle(ImageView asset, double x, double y) {
            this.asset = asset;
            this.x = x;
            this.y = y;
            this.width = asset.getImage().getWidth();
            this.height = asset.getImage().getHeight();
        }

        public Rectangle2D getRect() {
            Obstacle last = obstacles.getLast();
            double width = last.asset.getImage().getWidth();
            double height = last.asset.getImage().getHeight();

            return new Rectangle2D(this.x + 20, this.y + 20, width - 20, height - 20);
        }
    }
}
