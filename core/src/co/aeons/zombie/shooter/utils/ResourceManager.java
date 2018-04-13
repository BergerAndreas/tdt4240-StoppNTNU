package co.aeons.zombie.shooter.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Torstein on 4/11/2018.
 */

public class ResourceManager {

    private Animation<TextureRegion> runningAnimation;
    private Texture background;
    private Sprite playerSprite;

    // Create all resources
    public void createIdleAnimation() {
        //Opens textureAtlas containing enemy spritesheet information
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("GreenGoblin/pack.atlas"));
        //Fetches all sprites matchin keyword 'spoder'
        Animation<TextureRegion> runningAnimation =
                new Animation<TextureRegion>(0.1f, atlas.findRegions("spoder"), Animation.PlayMode.LOOP);
        //Initializes statetime for this animation
        this.runningAnimation = runningAnimation;
    }
    
    public void createBackground() {
        this.background = new Texture("street.jpg");
    }

    public void createPlayerSprite(){
        this.playerSprite = new Sprite(new Texture("player.png"));
    }
    // Fetch resources
    public Animation<TextureRegion> getRunningAnimation(){
        return this.runningAnimation;
    }

    public Texture getBackground() {
        return background;
    }

    public Sprite getPlayerSprite(){
        return this.playerSprite;
    }

    //Destroy resources if not needed anymore (Maybe we don't need this?)




}
