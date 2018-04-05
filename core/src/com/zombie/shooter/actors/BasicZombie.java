package com.zombie.shooter.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by Torstein on 4/5/2018.
 */

public class BasicZombie extends Enemy {

    private Sprite zombie;
    public BasicZombie(Body body) {
        super(body);
        zombie = new Sprite(new Texture("zombie.png"));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        /*
        if (GameManager.getInstance().getGameState() != GameState.PAUSED) {
            stateTime += Gdx.graphics.getDeltaTime();
        }
        */

        batch.draw(zombie, (screenRectangle.x - (screenRectangle.width * 0.1f)),
                screenRectangle.y, screenRectangle.width * 1.2f, screenRectangle.height * 1.1f);
    }
}
