package world.ucode.playfield.object;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public interface GameObject {
    void update(Hashtable<String, Boolean> activeKeys);
    void draw(GraphicsContext gc);
}
