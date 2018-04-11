package com.zombie.shooter.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by Torstein on 4/5/2018.
 */

public class BasicZombie extends Enemy {

    private Animation<TextureRegion> runningAnimation;
    TextureAtlas atlas;
    // A variable for tracking elapsed time for the animation
    float stateTime;

    public BasicZombie(Body body, Animation<TextureRegion> animation) {
        super(body);
        this.stateTime = 0;
        this.runningAnimation = animation;
    }

    public void stopAnimation(){
        System.out.println("stop animation");
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        /*
        if (GameManager.getInstance().getGameState() != GameState.PAUSED) {
            stateTime += Gdx.graphics.getDeltaTime();
        }
        */
        //Updates statetime according to gametime to keep animation running
        stateTime += Gdx.graphics.getDeltaTime();
        //Fetches frame to display
        TextureRegion currentFrame = runningAnimation.getKeyFrame(stateTime, true);
        //Draws frame
        batch.draw(currentFrame, (screenRectangle.x - (screenRectangle.width * 0.1f)),
                screenRectangle.y, screenRectangle.width * 1.2f, screenRectangle.height * 1.1f);
    }
}
