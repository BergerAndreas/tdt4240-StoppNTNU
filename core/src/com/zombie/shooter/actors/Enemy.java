package com.zombie.shooter.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.zombie.shooter.box2d.EnemyUserData;

/**
 * Created by andreasberger on 11/03/2018.
 */

public abstract class Enemy {
    private float stateTime;
    private float x;
    private float y;
    private float speed;
    private int hp;
    private int score;

    public Enemy(float x, float y, float speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.score = 1;
    }

    public void update(float dt){
        x += this.speed*dt;
    }

    public boolean shouldRemove(){
        return false;
        // TODO: 13/04/2018 fiiiiiix
    }

    public int getScore(){
        return this.score;
    }
    
    public int getHeight(){
        return 100;
        // TODO: 13/04/2018 fiiiiiix
    }

    public int getWidth(){
        return 100;
        // TODO: 13/04/2018 fiiiiiix
    }


    public boolean contains(Bullet bullet){
        if(bullet.getx() - this.x < this.getWidth() && (bullet.getx() - this.x >= 0)){
            if(bullet.gety() - this.y < this.getHeight() && (bullet.gety() - this.y >= 0)){
                return true;
            }
        }
        return false;
    }




}
