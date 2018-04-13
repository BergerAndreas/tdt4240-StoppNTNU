package co.aeons.zombie.shooter.screens;

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
import co.aeons.zombie.shooter.ZombieShooter;
import co.aeons.zombie.shooter.managers.GameScreenManager;
import co.aeons.zombie.shooter.tween.SpriteAccessor;
import co.aeons.zombie.shooter.utils.ResourceManager;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

/**
 * Created by Erikkvo on 09-Apr-18.
 */

public class Splash extends AbstractScreen {

    public final ZombieShooter app;
    // Cameras and viewport
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Stage stage;

    private Texture background;

    private ResourceManager resourceManager;
    private SpriteBatch batch;
    private Sprite splash;
    private TweenManager tweenManager;

    public Splash(final ZombieShooter game, ResourceManager resourceManager) {
        super(game);
        this.gameCam = new OrthographicCamera();
        this.app = game;

        // Initializes a new viewport
        this.gamePort = new FitViewport(
                ZombieShooter.APP_DESKTOP_WIDTH,
                ZombieShooter.APP_DESKTOP_HEIGHT,
                gameCam
        );
        gamePort.apply();

        // Sets up camera
        gameCam.position.set(this.gameCam.viewportWidth / 2, this.gameCam.viewportHeight / 2, 0);
        gameCam.update();
        // Initializes box2d renderer
        this.resourceManager = resourceManager;
        background = new Texture("logo.png");
        stage = new Stage(gamePort, app.batch);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        tweenManager = new TweenManager();
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
        Tween.to(splash, SpriteAccessor.ALPHA, 3f).target(1).repeatYoyo(1, 0f).setCallback(new TweenCallback() { // Delay and fade out


            //        Switch to menu screen
            @Override
            public void onEvent(int type, BaseTween<?> source) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MenuScreen(app, resourceManager));
            }
        }).start(tweenManager);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        tweenManager.update(delta);

        batch.begin();
        splash.draw(batch);
        batch.end();
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
