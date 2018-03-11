package com.zombie.shooter.actors;

/**
 * Created by andreasberger on 11/03/2018.
 */

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.zombie.shooter.enums.GameState;

public class Score extends Actor {

    private int score;
    private int multiplier;
    private Rectangle bounds;
    private BitmapFont font;

    public Score(Rectangle bounds) {
        this.bounds = bounds;
        setWidth(bounds.width);
        setHeight(bounds.height);
        score = 0;
        multiplier = 5;
    }



    public int getScore() {
        return score;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

}