package co.aeons.zombie.shooter.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import co.aeons.zombie.shooter.box2d.UserData;
import co.aeons.zombie.shooter.box2d.WallUserData;
import co.aeons.zombie.shooter.utils.B2DConstants;

public class Wall extends GameActor {

    private float stateTime;
    private Sprite wallSprite;

    public Wall(Body body) {
        super(body);
        wallSprite = new Sprite(new Texture("wall.jpg"));
        stateTime = 0f;
    }


    @Override
    public WallUserData getUserData() {
        return (WallUserData) userData;
    }

    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        stateTime += Gdx.graphics.getDeltaTime();

        //FIXME: Add variables to constants config
        batch.draw(wallSprite, 100, 0, 60, 600);

    }


    //TODO: ADD hit logic
    public void wallHit() {

    }


}
