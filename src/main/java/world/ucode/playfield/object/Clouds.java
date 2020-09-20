package world.ucode.playfield.object;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Random;

import static world.ucode.playfield.PlayField.speed;

public class Clouds implements GameObject {

    private static ImageView cloudAsset;
    private LinkedList<Cloud> clouds = new LinkedList<>();
    private static final double WIDTH = 200;
    private static final double HEIGHT = 200;
    private static final int MAX_Y = 170;
    private static final int MIN_Y = 50;
    private Random r = new Random();

    static {
        initImage("assets/cloud.png");
    }

    private static void initImage(String asset) {
        Image image = new Image(asset, WIDTH, HEIGHT, true, true);

        cloudAsset = new ImageView(image);
    }

    private void initClouds() {
        for (int i = 0; i < 10; i++) {
            clouds.add(new Cloud(cloudAsset,  i * (WIDTH + 200) + r.nextInt(200),r.nextInt(MAX_Y - MIN_Y) + MIN_Y));
        }
    }

    public double genGap() {
        return WIDTH + r.nextInt(420);
    }

    public Clouds() {
        initClouds();
    }

    @Override
    public void update(Hashtable<String, Boolean> activeKeys) {
        Cloud first = clouds.get(0);

        if (-first.x >= WIDTH) {
            first.x = clouds.peekLast().x + genGap();
            clouds.addLast(first);
            clouds.remove(0);
        }
        clouds.forEach(el -> {
            el.x -= speed / 15;
        });
    }

    @Override
    public void draw(GraphicsContext gc) {
        clouds.forEach(el -> {
            gc.drawImage(el.asset.getImage(), el.x, el.y);
        });
    }

    @Override
    public Rectangle2D getRect() {
        return null;
    }

    class Cloud {
        public double x;
        public double y;
        private ImageView asset;

        public Cloud(ImageView asset, double x, double y) {
            this.asset = asset;
            this.x = x;
            this.y = y;
        }
    }
}
