package com.zombie.shooter.actors;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.zombie.shooter.utils.B2DConstants;
import com.zombie.shooter.utils.UserData;

/**
 * Created by andreasberger on 11/03/2018.
 */

public abstract class Player extends Actor {
    protected Body body;
    protected UserData userData;
    protected Rectangle screenRectangle;

    public Player(Body body){
        this.body = body;
        this.userData = (UserData) body.getUserData();
        screenRectangle = new Rectangle();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        /*
        if (GameManager.getInstance().getGameState() == GameState.PAUSED) {
            return;
        }
        */
        if (body.getUserData() != null) {
            updateRectangle();
        } else {
            // This means the world destroyed the body (enemy or runner went out of bounds)
            remove();
        }

    }

    public abstract UserData getUserData();

    private void updateRectangle() {
        screenRectangle.x = transformToScreen(body.getPosition().x - userData.getWidth() / 2);
        screenRectangle.y = transformToScreen(body.getPosition().y - userData.getHeight() / 2);
        screenRectangle.width = transformToScreen(userData.getWidth());
        screenRectangle.height = transformToScreen(userData.getHeight());
    }

    protected float transformToScreen(float n) {
        return B2DConstants.PPM * n;
    }


}
