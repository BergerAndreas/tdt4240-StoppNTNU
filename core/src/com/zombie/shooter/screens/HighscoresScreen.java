package com.zombie.shooter.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.BitmapFontLoader;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
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
import com.zombie.shooter.managers.SaveFile;
import com.zombie.shooter.utils.ResourceManager;

import static com.zombie.shooter.utils.B2DConstants.PPM;

/**
 * Created by Erikkvo on 13-Apr-18.
 */

public class HighscoresScreen extends AbstractScreen {

    private BitmapFont font;
    private GlyphLayout layout;

// Keeping track of highscores
    private long[] highscores;
    private String[] names;


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


    public HighscoresScreen(final ZombieShooter game, ResourceManager resourceManager) {
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
        gameCam.position.set(this.gameCam.viewportWidth / 2, this.gameCam.viewportHeight / 2, 0);
        gameCam.update();
        // Initializes box2d renderer
        b2dr = new Box2DDebugRenderer();
        //Initialize skins
        this.resourceManager = resourceManager;
        atlas = new TextureAtlas(Gdx.files.internal("skins/neutralizer-ui.atlas"));
        skin = new Skin(Gdx.files.internal("skins/neutralizer-ui.json"));
        background = this.resourceManager.getBackground();
        stage = new Stage(gamePort, app.batch);

//        To handle width (and height) of font
        layout = new GlyphLayout();
        font = new BitmapFont();

        SaveFile.load();
        highscores = SaveFile.gameData.getHighscores();
        names = SaveFile.gameData.getNames();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        b2dr.render(world, gameCam.combined.cpy().scl(PPM));

        app.batch.begin();
        String s = "Highscores";
        font.getData().setScale(2, 2);
        layout.setText(font, s);
        float width = layout.width;

        font.draw(app.batch, s, (ZombieShooter.APP_DESKTOP_WIDTH - width)/2, ZombieShooter.APP_DESKTOP_HEIGHT - 100);
        for(int i=0; i<highscores.length; i++){
            s = String.format("%2d. %7s %s", i+1, highscores[i], names[i]);
            layout.setText(font, s);
            float w = layout.width;
            font.draw(app.batch, s, (ZombieShooter.APP_DESKTOP_WIDTH - w)/2, 400 - 30*i);
        }


        app.batch.end();

        //Make stage show stuff
        this.stage.act();
        this.stage.draw();
    }

    @Override
    public void update(float delta) {
        // Move world forward
        world.step(1f / ZombieShooter.APP_FPS, 6, 2);
        //Handle updates here
        this.stage.act(delta);
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

        TextButton backButton = new TextButton("Back", skin);


        //Add listeners to buttons
        //        Back takes player back to "Game Over" screen
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.gsm.resetPlayScreen();
                app.gsm.setScreen(GameScreenManager.STATE.GAME_OVER);
            }
        });

        backButton.setPosition((ZombieShooter.APP_DESKTOP_WIDTH - backButton.getWidth())/2,50);
        stage.addActor(backButton);
    }


}
