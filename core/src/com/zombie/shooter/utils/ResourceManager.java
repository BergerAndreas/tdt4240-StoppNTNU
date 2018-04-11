package com.zombie.shooter.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Torstein on 4/11/2018.
 */

public class ResourceManager {

    //
    public static Animation<TextureRegion> createIdleAnimation() {
        //Opens textureAtlas containing enemy spritesheet information
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("GreenGoblin/pack.atlas"));
        //Fetches all sprites matchin keyword 'spoder'
        Animation<TextureRegion> runningAnimation =
                new Animation<TextureRegion>(0.1f, atlas.findRegions("spoder"), Animation.PlayMode.LOOP);
        //Initializes statetime for this animation
        return runningAnimation;
    }

}
