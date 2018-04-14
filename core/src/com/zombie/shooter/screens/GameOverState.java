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
import com.zombie.shooter.enums.GameState;
import com.zombie.shooter.managers.GameScreenManager;
import com.zombie.shooter.managers.GameStateManager;
import com.zombie.shooter.utils.ResourceManager;

import static com.zombie.shooter.utils.B2DConstants.PPM;

/**
 * Created by Erikkvo on 12-Apr-18.
 */

public class GameOverState extends AbstractState {

    //Skins, textures and sprites
    private ResourceManager resourceManager;
    private Skin skin;
    private TextureAtlas atlas;
    private Texture background;


    public GameOverState(GameStateManager gsm, ResourceManager resourceManager) {
        super(gsm);

        //Initialize skins
        this.resourceManager = resourceManager;
        atlas = new TextureAtlas(Gdx.files.internal("skins/neutralizer-ui.atlas"));
        skin = new Skin(Gdx.files.internal("skins/neutralizer-ui.json"));
        background = this.resourceManager.getBackground();
    }

    public void render(float delta) {

    }

    public void init() {

    }

    public void update(float delta) {

    }

    public void draw() {

    }

    public void handleInput() {

    }

    public void show() {
        InitMenu();
    }

    public void dispose() {

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
        TextButton HighscoresButton = new TextButton("Highscores", skin);
        TextButton QuitGameButton = new TextButton("Quit Game", skin);


        //Add listeners to buttons
        restartButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
//                TODO change state to whatever the state was prior to "restart press"
//                app.gsm.resetPlayScreen();
//                app.gsm.setScreen(GameScreenManager.STATE.SINGLE_PLAYER);
                //((Game)Gdx.app.getApplicationListener()).setScreen(new PlayScreen(app));
            }
        });
//        Quit Game takes player to main menu screen
        QuitGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
//                app.gsm.resetPlayScreen();
//                app.gsm.setScreen(GameScreenManager.STATE.MAIN_MENU);
            }
        });


        //Add buttons to table
        mainTable.add(restartButton);
        mainTable.row();
//        mainTable.add(highscoresButton);
//        mainTable.row();
        mainTable.add(QuitGameButton);

        // Adds maintable to stage
//        stage.addActor(mainTable);
    }

}
