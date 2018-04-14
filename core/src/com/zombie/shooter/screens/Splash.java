package com.zombie.shooter.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.zombie.shooter.ZombieShooter;
import com.zombie.shooter.managers.GameScreenManager;
import com.zombie.shooter.managers.GameStateManager;
import com.zombie.shooter.tween.SpriteAccessor;
import com.zombie.shooter.utils.ResourceManager;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import static com.badlogic.gdx.Gdx.app;

/**
 * Created by Erikkvo on 09-Apr-18.
 */

public class Splash extends AbstractState {

    private SpriteBatch batch;

    private Texture background;
    private ResourceManager resourceManager;
    private Sprite splash;
    private TweenManager tweenManager;

    public Splash(GameStateManager gsm, ResourceManager resourceManager) {
        super(gsm);

        this.resourceManager = resourceManager;
        background = new Texture("logo.png");
    }

    @Override
    public void init() {
        batch = new SpriteBatch();
        tweenManager = new TweenManager();
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw() {

    }

    @Override
    public void handleInput() {

    }

    public void show() {
//        Tween does not know there is a successor, thus we need to register it
        Tween.registerAccessor(Sprite.class, new SpriteAccessor());

        Texture splashTexture = new Texture("logo.png");
        splash = new Sprite(splashTexture);
        splash.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        //        Creating the animations
        Tween.set(splash, SpriteAccessor.ALPHA).target(0).start(tweenManager); // Ends at transparency = 1 (visible)
        //        Actual animation

        //Initialize resources while splash screen shows
        initializeResources();
//        Tween.to(splash, SpriteAccessor.ALPHA, 3f).target(1).repeatYoyo(1, 0f).setCallback(new TweenCallback() { // Delay and fade out
//
//
//            //        Switch to menu screen
//            @Override
//            public void onEvent(int type, BaseTween<?> source) {
//                ((Game) app.getApplicationListener()).setScreen(new MenuScreen(this, resourceManager));
//            }
//        }).start(tweenManager);
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        tweenManager.update(delta);

        batch.begin();
        splash.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        splash.getTexture().dispose();
    }

    private void initializeResources() {
        resourceManager.createIdleAnimation();
        resourceManager.createBackground();
        resourceManager.createPlayerSprite();
        //FIXME: Maybe remove resource initialized message
        System.out.println("Resources initialized");
    }
}
