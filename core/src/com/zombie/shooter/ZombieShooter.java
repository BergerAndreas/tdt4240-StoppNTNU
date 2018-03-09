package com.zombie.shooter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.zombie.shooter.managers.GameScreenManager;

public class ZombieShooter extends Game {

	public static String APP_TITLE = "Tødden er ei dås";
	public static double APP_VERSION = 0.1;
	public static int APP_DESKTOP_WIDTH = 720;
	public static int APP_DESKTOP_HEIGHT = 420;
	public static int APP_FPS = 60; //Cinematic?!??!


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

	}

	@Override
	public void render () {
		super.render();

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
