package com.zombie.shooter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.zombie.shooter.managers.GameScreenManager;
import com.zombie.shooter.utils.AudioUtils;

public class ZombieShooter extends Game {

	public static String APP_TITLE = "Ugly Z";
	public static double APP_VERSION = 0.1;
	public static int APP_DESKTOP_WIDTH = 1024;
	public static int APP_DESKTOP_HEIGHT = 576;
	public static int APP_FPS = 60; //Cinematic?!??!
	public static OrthographicCamera cam;

	public static int WIDTH;
	public static int HEIGHT;


	// Mangers
	public AssetManager assets;
	public GameScreenManager gsm;

	public SpriteBatch batch;
	public ShapeRenderer shapeBatch;


	@Override
	public void create () {
		shapeBatch = new ShapeRenderer();
		batch = new SpriteBatch();

		assets = new AssetManager();
		gsm = new GameScreenManager(this);
//		Initialize audio
		AudioUtils.getInstance().init();

		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();

		cam = new OrthographicCamera(WIDTH, HEIGHT);
		cam.translate(WIDTH / 2, HEIGHT / 2);
		cam.update();


	}

	@Override
	public void render () {
		super.render();

		// clear screen to black
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.draw();

		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
			Gdx.app.exit();
		}
	}
	
	@Override
	public void dispose () {
		super.dispose();
		batch.dispose();
		shapeBatch.dispose();
		assets.dispose();
	}
}
