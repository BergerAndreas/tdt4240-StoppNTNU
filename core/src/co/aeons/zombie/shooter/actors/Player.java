package co.aeons.zombie.shooter.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import co.aeons.zombie.shooter.box2d.PlayerUserData;
import co.aeons.zombie.shooter.box2d.UserData;
import co.aeons.zombie.shooter.enums.GameState;

/**
 * Created by Torstein on 4/5/2018.
 */

public class Player extends GameActor {


    private float stateTime;
    private Sprite player;


    public Player(Body body) {
        super(body);
        player = new Sprite(new Texture("player.png"));
        stateTime = 0f;
    }

    @Override
    public PlayerUserData getUserData() {
        return (PlayerUserData) userData;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        float x = screenRectangle.x - (screenRectangle.width * 0.1f);
        float y = screenRectangle.y;
        float width = screenRectangle.width * 1.2f;

        /* TODO: Do something when hit
        if (hit) {
            // When he's hit we also want to apply rotation if the body has been rotated
            batch.draw(hitTexture, x, y, width * 0.5f, screenRectangle.height * 0.5f, width, screenRectangle.height, 1f,
                    1f, (float) Math.toDegrees(body.getAngle()));
        }
         */
            // Running
        //TODO: Add pause logic
        stateTime += Gdx.graphics.getDeltaTime();
        batch.draw(player, x, y, width, screenRectangle.height);
    }

    //TODO:Add hit logic
    /*
    public void hit() {
        body.applyAngularImpulse(getUserData().getHitAngularImpulse(), true);
        hit = true;
        AudioUtils.getInstance().playSound(hitSound);
    }

    public boolean isHit() {
        return hit;
    }
    */
    public void setGravityScale(float gravityScale) {
        body.setGravityScale(gravityScale);
        body.resetMassData();
    }
    public void setTransform(Vector2 vec, float angle){
        this.body.setTransform(vec, angle);
    }

}
