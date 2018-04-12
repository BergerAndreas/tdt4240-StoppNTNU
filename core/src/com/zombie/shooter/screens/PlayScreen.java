package com.zombie.shooter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.JointEdge;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.zombie.shooter.ZombieShooter;
import com.zombie.shooter.actors.BasicZombie;
import com.zombie.shooter.actors.Enemy;
import com.zombie.shooter.actors.Player;
import com.zombie.shooter.actors.Wall;
import com.zombie.shooter.actors.buttons.FireButton;
import com.zombie.shooter.box2d.WallUserData;
import com.zombie.shooter.enums.UserDataType;
import com.zombie.shooter.managers.GameScreenManager;
import com.zombie.shooter.utils.AudioUtils;
import com.zombie.shooter.utils.B2DConstants;
import com.zombie.shooter.utils.B2DWorldUtils;
import com.zombie.shooter.utils.ResourceManager;
import com.zombie.shooter.utils.utils;

import java.util.ArrayList;

import static com.zombie.shooter.utils.B2DConstants.PPM;

/**
 * Created by opium on 09.03.18.
 */

/**
 * This is the main game screen
 * Methods that does not come from AbstractScreen should be placed on the bottom
 */

public class PlayScreen extends AbstractScreen implements ContactListener {
    // Cameras and viewport
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Stage stage;

    //Box2D physics engine
    private World world;
    private Box2DDebugRenderer b2dr; //Debug mode to make our life easy

    //Add player
    private Player player;

    //Add walls
    private ArrayList<Wall> walls;
    private Wall mainWall;

    //Touchinput
    private Vector3 touchPoint;
    private Rectangle playerLane;
    private Rectangle fireBounds;

    //buttons
    private FireButton fireButton;

    //Difficulty counter
    private int difficulty;
    private float gameTime;
    private float prevVal;

    //Textures, plz use resourcemanager
    private ResourceManager resourceManager;
    private Texture background;
    private Animation<TextureRegion> basicEnemyAnimation;
    private Sprite playerSprite;


    public PlayScreen(final ZombieShooter game, ResourceManager resourceManager) {
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
        //Initialize texture
        this.resourceManager = resourceManager;
        stage = new Stage(gamePort, app.batch);
        //Initialize audio
        AudioUtils.getInstance().init();

        this.difficulty = 10;
        this.gameTime = 1.0f;
        this.prevVal = 1.0f;
    }

    @Override
    public void show() {

        // Initialize new world for this screen
        world = new World(new Vector2(0f, 0f), false);
        //Set the world's contact listener to this class
        world.setContactListener(this);
        app.shapeBatch.setProjectionMatrix(gameCam.combined);
        app.batch.setProjectionMatrix(gameCam.combined);
        InitGame();

    }

    @Override
    public void update(float delta) {
        // Move world forward
        // Why 6 and 2? I don't know
        world.step(1f / ZombieShooter.APP_FPS, 6, 2);

        //Handle updates here
        this.stage.act(delta);

        this.gameTime += delta;

        if (Math.floor(gameTime) != prevVal) {
            //Spawn Zombies
            if (Math.floor(this.gameTime) % 9 == 0) {
                spawnZombieWave();
            }
            prevVal += 1;

            if (prevVal % 17 == 0) {
                System.out.println("Difficulty increased");
                difficulty += 5;
            }
        }
        //Game over logic
        if (mainWall.getUserData().getWallHealth() <= 0) {
            //TODO: Add game over
            System.out.println("Game over");
            app.gsm.setScreen(GameScreenManager.STATE.GAME_OVER);
        }


    }

    @Override
    public void render(float delta) {
        super.render(delta);
        b2dr.render(world, gameCam.combined.cpy().scl(PPM));

        //Sets background of gamescreen
        app.batch.begin();
        //Renders game background

        app.batch.draw(background, 0, 0, ZombieShooter.APP_DESKTOP_WIDTH,
                ZombieShooter.APP_DESKTOP_HEIGHT);
        app.batch.end();

        //Make stage show stuff
        this.stage.act();
        this.stage.draw();
    }

    //Place own methods here
    private void InitGame() {
        //Add stuff here
        setupResources();
        spawnZombieWave();
        setUpRunner();
        setUpWall();
        setUpTouchControlAreas();
        setupInput();
        setupEdges();

    }

    private void setupResources() {
        background = resourceManager.getBackground();
        basicEnemyAnimation = resourceManager.getRunningAnimation();
        playerSprite = resourceManager.getPlayerSprite();
    }

    // Creates enemy
    private void createEnemy(float x, float y) {
        //Initializes basic zombie
        Enemy enemy = new BasicZombie(B2DWorldUtils.createEnemy(world, x, y), basicEnemyAnimation);

        //Adds enemy to current stage
        stage.addActor(enemy);
    }

    //Sets up player
    private void setUpRunner() {
        //Removes existing players upon setup
        if (player != null) {
            player.remove();
        }
        player = new Player(B2DWorldUtils.createRunner(world), playerSprite);

        //adds enemy to current stage
        stage.addActor(player);
    }

    //Set up wall
    private void setUpWall() {
        //Set up main wall
        mainWall = new Wall(B2DWorldUtils.createWall(
                world,
                B2DConstants.WALL_X,
                B2DConstants.WALL_Y,
                B2DConstants.WALL_WIDTH,
                B2DConstants.WALL_HEIGHT
        ));
        stage.addActor(mainWall);
    }

    private void setupEdges() {
        float height = gameCam.viewportHeight / PPM;
        float width = gameCam.viewportWidth / PPM;

        Vector2[] v_roof = new Vector2[3];
        v_roof[0] = new Vector2(0f, height - 1);
        v_roof[1] = new Vector2(width, height - 1);
        v_roof[2] = new Vector2(0f, height - 1);

        Vector2[] v_floor = new Vector2[3];
        v_floor[0] = new Vector2(0f, 0f);
        v_floor[1] = new Vector2(width, 0f);
        v_floor[2] = new Vector2(0, 0f);

        B2DWorldUtils.createChainShape(world, v_floor);
        B2DWorldUtils.createChainShape(world, v_roof);
    }

    private void setUpTouchControlAreas() {
        touchPoint = new Vector3();
        //TODO: Update this when constants available, also, draw them
        playerLane = new Rectangle(0, 0, 7 * B2DConstants.PPM,
                this.stage.getCamera().viewportHeight);
        //Creates bounds for firebutton
        fireBounds = new Rectangle(this.stage.getCamera().viewportWidth - 200, 0,
                this.stage.getCamera().viewportWidth / 8, this.stage.getCamera().viewportHeight / 6);
        //Creates a new firebutton with above bounds
        fireButton = new FireButton(fireBounds, new GameFireButtonListener());
        //Adds firebutton to stage
        this.stage.addActor(fireButton);
    }
    private void setupInput() {
        // Enables playscreen to control input
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int x, int y, int pointer, int button) {
                // Need to get the actual coordinates
                Vector2 tmpVec2 = new Vector2();
                translateScreenToWorldCoordinates(x, y);
                stage.getViewport().unproject(tmpVec2.set(x, y));

                // Passes touch control to button observer thingy ¯\_(ツ)_/¯
                if (fireButton.getBounds().contains(tmpVec2.x, tmpVec2.y)) {
                    stage.touchDown(x, y, pointer, button);
                }
                return true;
            }

            @Override
            public boolean touchUp(int x, int y, int pointer, int button) {
                // your touch up code here
                return true; // return true to indicate the event was handled
            }

            @Override
            public boolean touchDragged(int x, int y, int pointer) {
                Vector2 tmpVec2 = new Vector2();
                translateScreenToWorldCoordinates(x, y);
                stage.getViewport().unproject(tmpVec2.set(x, y));

                if (playerLaneTouched(tmpVec2.x, tmpVec2.y)) {
                    player.setTransform(new Vector2(player.getUserData().getRunningPosition().x, tmpVec2.y / B2DConstants.PPM), 0);
                }
                return true;
            }
        });
    }

    private boolean playerLaneTouched(float x, float y) {
        return playerLane.contains(x, y);
    }



    //Contact listeners
    @Override
    public void beginContact(Contact contact) {
        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();
        if (((B2DWorldUtils.bodyIsWall(b)) && (B2DWorldUtils.bodyIsEnemy(a)))) {
            //TODO: Make the wall health decease continuously
            mainWall.getUserData().decreaseHealth();
            System.out.println(mainWall.getUserData().getWallHealth());

        }
    }

    // Button listeners

    //Firebutton listener class
    private class GameFireButtonListener implements FireButton.FireButtonListener {

        // Adds observer
        @Override
        public void onFire() {
            //Calls this method when button is pressed
            onFireButtonPressed();
        }
    }

    //Method called when FireButton pressed
    private void onFireButtonPressed() {
        //TODO: implement fire button logic
        System.out.println("Button pressed");
    }

    private void spawnZombieWave() {
        int waveCount = utils.randInt(this.difficulty - 6, this.difficulty);

        for (int i = 0; i < waveCount; i++) {
            // Don't mind the magic space numbers
            int spawnCordY = utils.randInt(5, (int) gameCam.viewportHeight - 80);
            int spawnCordX = utils.randInt(30, 50);
            createEnemy(spawnCordX, spawnCordY / PPM);
        }

    }

    //Helper function to get actual world coordinates used for touch input
    private void translateScreenToWorldCoordinates(int x, int y) {
        this.stage.getCamera().unproject(touchPoint.set(x, y, 0));
    }

    @Override
    public void resize(int width, int height) {
        // Ensures resizing works properly
        this.gamePort.update(width, height);
        this.gameCam.position.set(this.gameCam.viewportWidth / 2, this.gameCam.viewportHeight / 2, 0);
        this.gameCam.update();
    }

    @Override
    public void dispose() {

        super.dispose();
        this.world.dispose();
        this.background.dispose();

    }


    //Unused methods, but necessary because inheritance

    //AbstractScreen

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    //Box2D
    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

}
