package co.aeons.zombie.shooter.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import co.aeons.zombie.shooter.box2d.EnemyUserData;

/**
 * Created by andreasberger on 11/03/2018.
 */

public abstract class Enemy extends GameActor {
    private float stateTime;

    public Enemy(Body body) {
        super(body);
        stateTime = 0f;
    }

    @Override
    public EnemyUserData getUserData() {
        return (EnemyUserData) userData;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        body.setLinearVelocity(getUserData().getLinearVelocity());
    }

}
