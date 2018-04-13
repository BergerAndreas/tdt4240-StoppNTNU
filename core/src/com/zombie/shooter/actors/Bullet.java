package com.zombie.shooter.actors;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Bullet {

    private float x;
    private float y;
    private float speed = 350;

    private boolean remove;

    public Bullet(float x, float y) {

        this.x = x;
        this.y = y;
    }

    public boolean shouldRemove() { return remove; }

    public void update(float dt) {

        x += this.speed * dt;
    }

    public void draw(ShapeRenderer sr) {
        sr.setColor(1, 1, 1, 1);
        sr.begin(ShapeRenderer.ShapeType.Circle);
        sr.circle(1,1,1);
        sr.end();
    }

    public float gety(){return y;}
    public float getx(){return x;}

}