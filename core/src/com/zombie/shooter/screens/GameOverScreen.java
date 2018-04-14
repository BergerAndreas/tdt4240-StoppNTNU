package com.zombie.shooter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.zombie.shooter.ZombieShooter;
import com.zombie.shooter.managers.GameScreenManager;
import com.zombie.shooter.utils.ResourceManager;

import static com.zombie.shooter.utils.B2DConstants.PPM;

/**
 * Created by Erikkvo on 12-Apr-18.
 */

public class GameOverScreen extends AbstractScreen {

    // Cameras and viewport
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Stage stage;

    //Box2D physics engine
    private World world;
    private Box2DDebugRenderer b2dr; //Debug mode to make our life easy

    //Game Items put in world

    //Skins, textures and sprites
    private ResourceManager resourceManager;
    private Skin skin;
    private TextureAtlas atlas;
    private Texture background;


    public GameOverScreen(final ZombieShooter game, ResourceManager resourceManager) {
        super(game);

        this.gameCam = new OrthographicCamera();

        // Initializes a new viewport
        this.gamePort = new FitViewport(
                ZombieShooter.WIDTH,
                ZombieShooter.HEIGHT,
                gameCam
        );
        gamePort.apply();

        //sets up camera
        gameCam.position.set(this.gameCam.viewportWidth / 2, this.gameCam.viewportHeight / 2, 0);
        gameCam.update();
        //Initialize skins
        this.resourceManager = resourceManager;
        atlas = new TextureAtlas(Gdx.files.internal("skins/neutralizer-ui.atlas"));
        skin = new Skin(Gdx.files.internal("skins/neutralizer-ui.json"));
        background = this.resourceManager.getBackground();
        stage = new Stage(gamePort, app.batch);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        b2dr.render(world, gameCam.combined.cpy().scl(PPM));

        //Sets background of GameOverScreen
        app.batch.begin();
//        app.batch.draw(background, 0, 0, ZombieShooter.APP_DESKTOP_WIDTH,
//                ZombieShooter.APP_DESKTOP_HEIGHT);
        app.batch.end();

        //Make stage show stuff
        this.stage.act();
        this.stage.draw();
    }

    @Override
    public void init() {

    }

    @Override
    public void update(float delta) {
        // Move world forward
        world.step(1f / ZombieShooter.APP_FPS, 6, 2);
        //Handle updates here
        this.stage.act(delta);
    }

    @Override
    public void draw() {

    }

    @Override
    public void handleInput() {

    }

    @Override
    public void show() {
        // Set up new world for GameOverScreen
        world = new World(new Vector2(0f, 0f), false);
        app.shapeBatch.setProjectionMatrix(gameCam.combined);
        app.batch.setProjectionMatrix(gameCam.combined);

        // Control inputs
        Gdx.input.setInputProcessor(this.stage);
        InitMenu();
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
        this.skin.dispose();
        this.atlas.dispose();
        this.background.dispose();

    }

    //Place own methods here
    private void InitMenu() {
        //Add stuff here
        //Create Table
        Table mainTable = new Table();
        //Set table to fill stage
        mainTable.setFillParent(true);
        //Set alignment of contents in the table.
        mainTable.center();
        //Create buttons

        TextButton restartButton = new TextButton("Restart", skin);
//        TextButton HighscoresButton = new TextButton("Highscores", skin);
        TextButton QuitGameButton = new TextButton("Quit Game", skin);


        //Add listeners to buttons
        restartButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
//                TODO change state to whatever the state was prior to "restart press"
                app.gsm.resetPlayScreen();
                app.gsm.setScreen(GameScreenManager.STATE.SINGLE_PLAYER);
                //((Game)Gdx.app.getApplicationListener()).setScreen(new PlayScreen(app));
            }
        });
//        Quit Game takes player to main menu screen
        QuitGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.gsm.resetPlayScreen();
                app.gsm.setScreen(GameScreenManager.STATE.MAIN_MENU);
            }
        });


        //Add buttons to table
        mainTable.add(restartButton);
        mainTable.row();
//        mainTable.add(highscoresButton);
//        mainTable.row();
        mainTable.add(QuitGameButton);

        // Adds maintable to stage
        stage.addActor(mainTable);
    }

}
