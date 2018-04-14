package com.zombie.shooter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.zombie.shooter.ZombieShooter;
import com.zombie.shooter.managers.GameScreenManager;
import com.zombie.shooter.utils.AudioUtils;

/**
 *  Created by opium on 09.03.18.
 */

public abstract class AbstractScreen {

//    Trenger vi denne under?
//    protected final ZombieShooter app;
    protected GameScreenManager gsm;

    protected AbstractScreen(GameScreenManager gsm){
        this.gsm = gsm;
    }

    public abstract void init();
    public abstract void update(float dt);
    public abstract void draw();
    public abstract void handleInput();
    public abstract void dispose();

//    @Override
//    public void render(float delta){
//        update(delta);
//        Gdx.gl.glClearColor(0f,.0f,.0f,1f);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//    }

//    Unsure whether resize is required. Might be
//    @Override
//    public void resize(int width, int height){
//        stage.getViewport().update(width, height, true);
//    }


}
