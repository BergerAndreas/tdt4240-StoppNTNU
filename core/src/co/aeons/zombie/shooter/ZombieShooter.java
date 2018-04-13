package co.aeons.zombie.shooter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import co.aeons.zombie.shooter.managers.GameScreenManager;

import de.golfgl.gdxgamesvcs.IGameServiceClient;
import de.golfgl.gdxgamesvcs.IGameServiceListener;
import de.golfgl.gdxgamesvcs.NoGameServiceClient;

public class ZombieShooter extends Game implements IGameServiceListener {

	public static String APP_TITLE = "Ugly Z";
	public static double APP_VERSION = 0.1;
	public static int APP_DESKTOP_WIDTH = 1024;
	public static int APP_DESKTOP_HEIGHT = 576;
	public static int APP_FPS = 60; //Cinematic?!??!


	//GPGs stuff
	public static final String LEADERBOARD1 = "BOARD1";
	public static final String ACHIEVEMENT1 = "ACH1";
	public static final String EVENT1 = "EVENT1";
	public static final String FILE_ID = "cloud";
	Stage stage;
	Label gsStatus;
	private TextButton signInButton;
	Label gsUsername;
	Skin skin;
	private TextureAtlas atlas;
	private TextField scoreFillin;
	private TextField cloudData;

	public IGameServiceClient gsClient;



	// Mangers
	public AssetManager assets;
	public GameScreenManager gsm;

	public SpriteBatch batch;
	public ShapeRenderer shapeBatch;


	@Override
	public void create () {
		stage = new Stage(new ExtendViewport(800, 450));
		Gdx.input.setInputProcessor(stage);
		shapeBatch = new ShapeRenderer();
		batch = new SpriteBatch();
		prepareSkin();

		assets = new AssetManager();
		//gsm = new GameScreenManager(this);
		gsClient.setListener(this);
		prepareUI();
		gsClient.resumeSession();
		refreshStatusLabel();

	}
	private void prepareUI(){
		gsStatus = new Label("", skin);
		gsUsername = new Label("", skin);
		scoreFillin = new TextField("100", skin);
		cloudData = new TextField("", skin);
		signInButton = new TextButton("", skin);
		signInButton.addListener(new ChangeListener() {
			public void changed(ChangeListener.ChangeEvent event, Actor actor) {
				gsSignInOrOut();
			}
		});

		Table table = new Table();
		table.setFillParent(true);
		table.defaults().pad(5);
		stage.addActor(table);

		table.add(new Label("Gdx-GameServices Demo App", skin)).colspan(3).padBottom(0);
		table.row();

		table.add(new Label("Game Service ID:", skin)).right();
		table.add(new Label(gsClient.getGameServiceId(), skin)).left();
		table.add();

		table.row();
		table.add(new Label("Status:", skin)).right();
		table.add(gsStatus).left();
		table.add(signInButton);

		table.row();
		table.add(new Label("User name:", skin)).right();
		table.add(gsUsername).left();
		table.add();
	}
	@Override
	public void render () {
		super.render();
		refreshStatusLabel();
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
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

	@Override
	public void gsOnSessionActive() {
		if (gsClient.isSessionActive())

			System.out.println("SESSION ACTIVE");
	}

	@Override
	public void gsOnSessionInactive() {

	}

	@Override
	public void gsShowErrorToUser(GsErrorType et, String msg, Throwable t) {

	}

	private void gsSignInOrOut() {
		if (gsClient.isSessionActive())
			gsClient.logOff();
		else {
			if (!gsClient.logIn())
				Gdx.app.error("GS_ERROR", "Cannot sign in: No credentials or session id given.");

		}
			refreshStatusLabel();
	}

	private void refreshStatusLabel() {
		String newStatusText;
		String newUserText;

		if (gsClient.isSessionActive())
			newStatusText = "SESSION ACTIVE";
		else if (gsClient.isConnectionPending())
			newStatusText = "CONNECTING SESSION...";
		else
			newStatusText = "NO SESSION";

		gsStatus.setText(newStatusText);

		signInButton.setText(gsClient.isSessionActive() ? "Sign out" : "Sign in");

		newUserText = gsClient.getPlayerDisplayName();
		gsUsername.setText(newUserText != null ? newUserText : "(none)");
	}


	private void prepareSkin() {
		// A skin can be loaded via JSON or defined programmatically, either is fine. Using a skin is optional but
		// strongly
		// recommended solely for the convenience of getting a texture, region, etc as a drawable, tinted drawable, etc.
		skin = new Skin();
		atlas = new TextureAtlas(Gdx.files.internal("skins/neutralizer-ui.atlas"));
		skin = new Skin(Gdx.files.internal("skins/neutralizer-ui.json"));
		skin.addRegions(atlas);

	}
}
