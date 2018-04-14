package com.zombie.shooter.screens;

import com.zombie.shooter.managers.GameScreenManager;

/**
 * Created by Erikkvo on 14-Apr-18.
 */

public abstract class AbstractState{

    protected GameScreenManager gsm;

    protected AbstractState(GameScreenManager gsm){
        this.gsm = gsm;
        init();
    }

    public abstract void init();
    public abstract void update(float dt);
    public abstract void draw();
    public abstract void handleInput();
    public abstract void dispose();
}
