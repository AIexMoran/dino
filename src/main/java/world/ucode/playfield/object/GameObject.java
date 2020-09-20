package world.ucode.playfield.object;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.RecursiveAction;

public interface GameObject {
    void update(Hashtable<String, Boolean> activeKeys);
    void draw(GraphicsContext gc);
    Rectangle2D getRect();
}
