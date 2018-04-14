package com.zombie.shooter.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.zombie.shooter.ZombieShooter;
import com.zombie.shooter.box2d.EnemyUserData;

/**
 * Created by andreasberger on 11/03/2018.
 */

public class Enemy {
    private float stateTime;
    private float x;
    private float y;
    private float speed;
    private int hp;
    private int score;
    private Sprite sprite;
    private Texture texture;
    private SpriteBatch sb;

    public Enemy(float x, float y, SpriteBatch sb) {
        this.x = x;
        this.y = y;
        this.sb = sb;
        this.score = 1;
        this.sprite = new Sprite();
        texture = new Texture(Gdx.files.internal("enemy.png"));
        sprite = new Sprite(texture);
        this.sprite.setPosition(100, 200); // TODO: 13/04/2018 mulig at vi må fikse på dette
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

    public void draw(SpriteBatch sb){
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // moving sprite left and right


        sb.begin();
        sb.draw(sprite, 0, 0, ZombieShooter.WIDTH, ZombieShooter.HEIGHT);
        sb.end();
    }



}
