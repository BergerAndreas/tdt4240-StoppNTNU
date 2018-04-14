package com.zombie.shooter.screens;

import com.zombie.shooter.managers.GameScreenManager;
import com.zombie.shooter.managers.GameStateManager;

/**
 * Created by Erikkvo on 14-Apr-18.
 */

public abstract class AbstractState{

    protected GameStateManager gsm;

    protected AbstractState(GameStateManager gsm){
        this.gsm = gsm;
        init();
    }

    public abstract void init();
    public abstract void update(float dt);
    public abstract void draw();
    public abstract void handleInput();
    public abstract void dispose();
}
