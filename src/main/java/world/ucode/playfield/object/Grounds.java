package world.ucode.playfield.object;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayDeque;
import java.util.Hashtable;
import java.util.Random;

import static world.ucode.playfield.PlayField.speed;

public class Grounds implements GameObject {

    private static ImageView[] groundsAssets = new ImageView[3];
    private ArrayDeque<Ground> grounds = new ArrayDeque<>();
    private static double WIDTH = 200;
    private static double HEIGHT = 200;
    private static double IMAGE_WIDTH;

    static {
        initImage(0, "assets/ground1.png");
        initImage(1, "assets/ground2.png");
        initImage(2, "assets/ground3.png");
    }

    private static void initImage(int index, String asset) {
        Image image = new Image(asset, WIDTH, HEIGHT, true, true);

        groundsAssets[index] = new ImageView(image);
        IMAGE_WIDTH = groundsAssets[0].getImage().getWidth();
    }

    public Grounds() {
        Random r = new Random();

        for (int i = 0; i < 10; i++) {
            int randInt = r.nextInt(groundsAssets.length);

            grounds.push(new Ground(new ImageView(groundsAssets[randInt].getImage()), i * IMAGE_WIDTH, 450));
        }
    }

    @Override
    public void update(Hashtable<String, Boolean> activeKeys) {
        Ground last = grounds.getLast();

        if (last != null && -last.x >= IMAGE_WIDTH) {
            last.x = (grounds.size() - 1)  * IMAGE_WIDTH;
            grounds.addFirst(last);
            grounds.removeLast();
        }
        grounds.forEach(el -> {
            el.x -= speed;
        });
    }

    @Override
    public void draw(GraphicsContext gc) {
        grounds.forEach(el -> {
            gc.drawImage(el.asset.getImage(), el.x, el.y);
        });
    }

    class Ground {
        public double x;
        public double y;
        private ImageView asset;

        public Ground(ImageView asset, double x, double y) {
            this.asset = asset;
            this.x = x;
            this.y = y;
        }
    }
}
