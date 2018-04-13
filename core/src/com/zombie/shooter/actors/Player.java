package com.zombie.shooter.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Torstein on 4/5/2018.
 */

public class Player {


    private float stateTime;
    private Sprite player;
    private float y;
    private float x;
    private long score;


    public Player() {
        this.player = player;
        stateTime = 0f;
        this.y = 100;
        this.x = 100;
        this.score = 0;
    }

    public void update(float dt) {

    }

    public void incrementScore(long p){
        this.score += p;
    }

    public void setPosition(float y){
        this.y = y;
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {

    }


}
