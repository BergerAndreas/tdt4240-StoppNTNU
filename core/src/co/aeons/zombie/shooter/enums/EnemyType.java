package co.aeons.zombie.shooter.enums;

/**
 * Created by Torstein on 4/5/2018.
 */

public enum EnemyType {
    BASIC_ENEMY(1f, 1f, 25f, 1.5f, 0.5f);

    private float width;
    private float height;
    private float x;
    private float y;
    private float density;

    EnemyType(float width, float height, float x, float y, float density) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.density = density;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getDensity() {
        return density;
    }

}

