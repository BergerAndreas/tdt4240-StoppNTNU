package com.zombie.shooter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.zombie.shooter.managers.GameStateManager;
import com.zombie.shooter.utils.ResourceManager;

/**
 * Created by Torstein on 3/11/2018.
 */

public class MenuState extends AbstractState {

    //Skins, textures and sprites
    private ResourceManager resourceManager;
    private Skin skin;
    private TextureAtlas atlas;
    private Texture background;

    public MenuState(GameStateManager gsm, ResourceManager resourceManager) {
        super(gsm);
        //Initialize skins
        this.resourceManager = resourceManager;
        atlas = new TextureAtlas(Gdx.files.internal("skins/neutralizer-ui.atlas"));
        skin = new Skin(Gdx.files.internal("skins/neutralizer-ui.json"));
        background = resourceManager.getBackground();
    }


    public void show() {

//        // Enables menu to control input
//        Gdx.input.setInputProcessor(this.stage);
//        InitMenu();

    }

    @Override
    public void init() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void draw() {

    }

    @Override
    public void handleInput() {

    }

    public void render(float delta) {
//        super.render(delta);
//
//        //Sets background of menustate
//        app.batch.begin();
//        app.batch.end();
//
//        //Make stage show stuff
//        this.stage.act();
//        this.stage.draw();
    }

    @Override
    public void dispose() {
//
//        super.dispose();
//        this.world.dispose();
//        this.skin.dispose();
//        this.atlas.dispose();
//        this.background.dispose();

    }

    //Place own methods here
    private void InitMenu() {
//        //Add stuff here
//        //Create Table
//        Table mainTable = new Table();
//        //Set table to fill stage
//        mainTable.setFillParent(true);
//        //Set alignment of contents in the table.
//        mainTable.center();
//
//        //Create buttons
//        TextButton singleplayerButton = new TextButton("Singleplayer", skin);
//        TextButton multiplayerButton = new TextButton("Multiplayer", skin);
//        TextButton optionsButton = new TextButton("Options", skin);
//        TextButton exitButton = new TextButton("Exit", skin);
//
//
//        //Add listeners to buttons
//        singleplayerButton.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                app.gsm.setScreen(GameScreenManager.STATE.SINGLE_PLAYER);
//                //((Game)Gdx.app.getApplicationListener()).setScreen(new PlayScreen(app));
//            }
//        });
//        //TODO: Add multiplayer listener
//        exitButton.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                Gdx.app.exit();
//            }
//        });
//
//
//        //Add buttons to table
//        mainTable.add(singleplayerButton);
//        mainTable.row();
//        mainTable.add(multiplayerButton);
//        mainTable.row();
//        mainTable.add(optionsButton);
//        mainTable.row();
//        mainTable.add(exitButton);
//
//        // Adds maintable to stage
//        stage.addActor(mainTable);

    }

}
