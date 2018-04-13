package com.zombie.shooter.enums;

public enum BulletType {
    BULLET(1f,1f, 40f, 1.5f, 0.5f);

    private float width;
    private float height;
    private float x;
    private float y;
    private float density;

    BulletType(float width, float height, float x, float y, float density) {
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

    public float getDensity() {
        return density;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
