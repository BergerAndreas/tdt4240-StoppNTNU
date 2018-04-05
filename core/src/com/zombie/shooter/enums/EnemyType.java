package com.zombie.shooter.enums;

/**
 * Created by Torstein on 4/5/2018.
 */

public enum EnemyType {
    BASIC_ENEMY(1f, 1f, 25f, 1.5f, 0.5f,"basic_enemy.png");

    private float width;
    private float height;
    private float x;
    private float y;
    private float density;
    private String animationAssetsId; //FIXME: Maybe we don't need this

    EnemyType(float width, float height, float x, float y, float density, String animationAssetsId) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.density = density;
        this.animationAssetsId = animationAssetsId;
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

    public String getAnimationAssetId() {
        return animationAssetsId;
    }
}

