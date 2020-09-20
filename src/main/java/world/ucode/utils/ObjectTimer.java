package world.ucode.utils;

public class ObjectTimer {

    private long lastTime;
    public static final double SECOND = 1000;

    public boolean isGone(double time) {
        if (System.currentTimeMillis() - lastTime >= time) {
            lastTime = System.currentTimeMillis();
            return true;
        }
        return false;
    }

}
