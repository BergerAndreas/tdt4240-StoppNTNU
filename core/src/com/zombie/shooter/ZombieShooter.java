package com.zombie.shooter;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.zombie.shooter.managers.GameStateManager;

public class ZombieShooter implements ApplicationListener {

	public static String APP_TITLE = "Ugly Z";
	public static double APP_VERSION = 0.1;
//	public static int APP_DESKTOP_WIDTH = 1024;
//	public static int APP_DESKTOP_HEIGHT = 576;
	public static int APP_FPS = 60; //Cinematic?!??!

	public static int WIDTH;
	public static int HEIGHT;
	public static OrthographicCamera cam;


	// Mangers
	public AssetManager assets;
	private GameStateManager gsm;

	public SpriteBatch batch;
	public ShapeRenderer shapeBatch;


	@Override
	public void create () {

		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		cam = new OrthographicCamera(WIDTH, HEIGHT);
		// Place camera into screen region
		cam.translate(WIDTH / 2, HEIGHT / 2);
		cam.update();

		gsm = new GameStateManager();

		shapeBatch = new ShapeRenderer();
		batch = new SpriteBatch();

		assets = new AssetManager();
//		Initialize audio
//		AudioUtils.getInstance().init();

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void render () {

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
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose () {
		batch.dispose();
		shapeBatch.dispose();
		assets.dispose();
	}
}
