package com.zombie.shooter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
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
    // Cameras and viewport
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Stage stage;

    //Box2D physics engine
    private World world;
    private Box2DDebugRenderer b2dr; //Debug mode to make our life easy

    //Skins, textures and sprites
    private Texture background;

    public PlayScreen(final ZombieShooter game) {
        super(game);
        this.gameCam = new OrthographicCamera();

        // Initializes a new viewport
        this.gamePort = new FitViewport(
                ZombieShooter.APP_DESKTOP_WIDTH,
                ZombieShooter.APP_DESKTOP_HEIGHT,
                gameCam
        );
        gamePort.apply();

        //sets up camera
        gameCam.position.set(this.gameCam.viewportWidth/2, this.gameCam.viewportHeight/2, 0);
        gameCam.update();
        // Initializes box2d renderer
        b2dr = new Box2DDebugRenderer();
        //Initialize texture
        background = new Texture("street.jpg");
        stage = new Stage(gamePort, app.batch);

    }

    @Override
    public void show() {

        // Initialize new world for this screen
        world = new World(new Vector2(0f, 0f), false);
        app.shapeBatch.setProjectionMatrix(gameCam.combined);
        app.batch.setProjectionMatrix(gameCam.combined);

        // Enables menu to control input
        Gdx.input.setInputProcessor(this.stage);
        InitGame();

    }

    @Override
    public void update(float delta) {
        // Move world forward
        // Why 6 and 2? I don't know
        world.step(1f / ZombieShooter.APP_FPS, 6, 2 );

        //Handle updates here

        this.stage.act(delta);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        b2dr.render(world, gameCam.combined.cpy().scl(PPM));

        //Sets background of gamescreen
        app.batch.begin();
        app.batch.draw(background, 0, 0, ZombieShooter.APP_DESKTOP_WIDTH,
                ZombieShooter.APP_DESKTOP_HEIGHT);
        app.batch.end();

        //Make stage show stuff
        this.stage.act();
        this.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // Ensures resizing works properly
        this.gamePort.update(width, height);
        this.gameCam.position.set(this.gameCam.viewportWidth / 2, this.gameCam.viewportHeight / 2, 0);
        this.gameCam.update();
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
        this.world.dispose();
        this.background.dispose();

    }


    //Place own methods here
    private void InitGame() {
        //Add stuff here
    }

}
