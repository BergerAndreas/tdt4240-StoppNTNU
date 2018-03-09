package com.zombie.shooter.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.zombie.shooter.ZombieShooter;

import static com.zombie.shooter.utils.B2DConstants.PPM;

/**
 *  Created by opium on 09.03.18.
 */

/**
 * This is the main game screen
 * Methods that does not come from AbstractScreen should be placed on the bottom
 */

public class PlayScreen extends AbstractScreen {

    OrthographicCamera gameCam;
    Viewport gamePort;

    //Box2D
    World world;
    Box2DDebugRenderer b2dr; //Debug mode to make our life easy

    //Game Items put in world

    public PlayScreen(final ZombieShooter game) {
        super(game);
        this.gameCam = new OrthographicCamera();
        this.gamePort = new FitViewport(
                ZombieShooter.APP_DESKTOP_WIDTH,
                ZombieShooter.APP_DESKTOP_HEIGHT,
                gameCam
        );

    }

    @Override
    public void show() {

        world = new World(new Vector2(0f, 0f), false);
        app.shapeBatch.setProjectionMatrix(gameCam.combined);
        app.batch.setProjectionMatrix(gameCam.combined);

        InitGame();

    }

    @Override
    public void update(float delta) {
        // Move world forward
        // Why 6 and 2? I don't know
        world.step(1f / ZombieShooter.APP_FPS, 6, 2 );

        //Handle updates here

        stage.act(delta);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        b2dr.render(world, gameCam.combined.cpy().scl(PPM));
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

        super.dispose();
        world.dispose();

    }

    //Place own methods here
    private void InitGame() {
        //Add stuff here
    }

}
