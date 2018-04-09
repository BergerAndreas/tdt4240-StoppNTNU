package com.zombie.shooter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.zombie.shooter.ZombieShooter;
import com.zombie.shooter.actors.BasicZombie;
import com.zombie.shooter.actors.Enemy;
import com.zombie.shooter.actors.Player;
import com.zombie.shooter.actors.Wall;
import com.zombie.shooter.actors.buttons.FireButton;
import com.zombie.shooter.utils.B2DConstants;
import com.zombie.shooter.utils.B2DWorldUtils;

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

    //Skins, textures and sprites
    private Texture background;

    //Add player
    private Player player;

    //Add walls
    private ArrayList<Wall> walls;
  
    //Touchinput
    private Vector3 touchPoint;
    private Rectangle bottomLane;
    private Rectangle midLane;
    private Rectangle topLane;
    private Rectangle fireBounds;

    //buttons
    private FireButton fireButton;

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
        gameCam.position.set(this.gameCam.viewportWidth / 2, this.gameCam.viewportHeight / 2, 0);
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
        InitGame();

    }

    @Override
    public void update(float delta) {
        // Move world forward
        // Why 6 and 2? I don't know
        world.step(1f / ZombieShooter.APP_FPS, 6, 2);

        //Handle updates here
        this.stage.act(delta);
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
        createEnemy();
        setUpRunner();
        setUpWall();
        setUpTouchControlAreas();
        setupInput();
    }

    // Creates enemy
    private void createEnemy() {
        //Initializes basic zombie
        Enemy enemy = new BasicZombie(B2DWorldUtils.createEnemy(world));

        //Adds enemy to current stage
        stage.addActor(enemy);
    }

    //Sets up player
    private void setUpRunner() {
        //Removes existing players upon setup
        if (player != null) {
            player.remove();
        }
        player = new Player(B2DWorldUtils.createRunner(world));

        //adds enemy to current stage
        stage.addActor(player);
    }

    private void setUpWall() {
        Wall wall = new Wall(B2DWorldUtils.createWall(world));
        stage.addActor(wall);
    }
  
    private void setUpTouchControlAreas() {
        touchPoint = new Vector3();
        //TODO: Update this when constants available, also, draw them
        bottomLane = new Rectangle(0, 0, this.stage.getCamera().viewportWidth / 2,
                this.stage.getCamera().viewportHeight);
        midLane = new Rectangle(0, 0, this.stage.getCamera().viewportWidth / 2,
                this.stage.getCamera().viewportHeight);
        topLane = new Rectangle(0, 0, this.stage.getCamera().viewportWidth / 2,
                this.stage.getCamera().viewportHeight);

        //Creates bounds for firebutton
        fireBounds = new Rectangle(this.stage.getCamera().viewportWidth - 200, 0,
                this.stage.getCamera().viewportWidth / 8, this.stage.getCamera().viewportHeight / 6);
        //Creates a new firebutton with above bounds
        fireButton = new FireButton(fireBounds, new GameFireButtonListener());
        //Adds firebutton to stage
        this.stage.addActor(fireButton);
    }

    private boolean bottomLaneTouched(float x, float y) {
        return bottomLane.contains(x, y);
    }

    private boolean midLaneTouched(float x, float y) {
        return midLane.contains(x, y);
    }

    private boolean topLaneTouched(float x, float y) {
        return topLane.contains(x, y);
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
            public boolean touchDragged(int x, int y, int pointer){
                System.out.println(pointer);
                Vector2 tmpVec2 = new Vector2();
                translateScreenToWorldCoordinates(x, y);
                stage.getViewport().unproject(tmpVec2.set(x, y));
                player.setTransform(new Vector2(player.getUserData().getRunningPosition().x,tmpVec2.y/B2DConstants.PPM), 0);
                return true;
            }
        });
    }

    //Helper function to get actual world coordinates used for touch input
    private void translateScreenToWorldCoordinates(int x, int y) {
        this.stage.getCamera().unproject(touchPoint.set(x, y, 0));
    }

    //Contact listeners
    @Override
    public void beginContact(Contact contact) {

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

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

}
