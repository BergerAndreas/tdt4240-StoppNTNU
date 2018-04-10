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

    public BasicZombie(Body body) {
        super(body);
        createIdleAnimation();
    }

    private void createIdleAnimation() {
        //Opens textureAtlas containing enemy spritesheet information
        atlas = new TextureAtlas(Gdx.files.internal("GreenGoblin/pack.atlas"));
        //Fetches all sprites matchin keyword 'spoder'
        runningAnimation =
                new Animation<TextureRegion>(0.1f, atlas.findRegions("spoder"), Animation.PlayMode.LOOP);
        //Initializes statetime for this animation
        stateTime = 0f;
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
