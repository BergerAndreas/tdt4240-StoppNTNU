package com.zombie.shooter.actors.bullets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Bullet {

    private Texture texture;
    private float damage;
    private Vector2 bulletDirection;

    //TODO: Add userData


    public Bullet(Texture texture, float damage) {
        this.texture = texture;
        this.damage = damage;

        //The bullets get a speed set when fired from a weapon
        this.bulletDirection = new Vector2(0,0);
    }

    public void setBulletDirection(Vector2 bulletDirection) {
        this.bulletDirection = bulletDirection;
    }

    //Getters and setters
    //All the interesting code is above here
    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }
}
