package com.zombie.shooter.actors.buttons;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Torstein on 4/8/2018.
 */

public class FireButton extends GameButton{

    public interface FireButtonListener {
        public void onFire();
    }

    private FireButtonListener listener;

    public FireButton(Rectangle bounds, FireButtonListener listener) {
        super(bounds);
        this.listener = listener;
    }


    @Override
    protected String getTexturePath() {
        return "fireButton.png";
    }

    @Override
    public void touched() {
        listener.onFire();
    }

}
