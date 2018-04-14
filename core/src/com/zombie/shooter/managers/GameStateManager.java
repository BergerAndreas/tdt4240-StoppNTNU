package com.zombie.shooter.managers;

import com.zombie.shooter.screens.GameOverScreen;
import com.zombie.shooter.screens.MenuScreen;
import com.zombie.shooter.screens.PlayScreen;
import com.zombie.shooter.screens.Splash;
import com.zombie.shooter.utils.ResourceManager;

import java.util.HashMap;

/**
 * Created by Erikkvo on 14-Apr-18.
 */

public class GameStateManager {

    public GameStateManager(){

    }

    //    public final ZombieShooter app;
    private HashMap<GameStateManager.STATE, AbstractScreen> gameState;
    public ResourceManager resourceManager;

    //Add additional screens as they are added
    public enum STATE{
        SPLASH,
        MAIN_MENU,
        SINGLE_PLAYER,
        MULTI_PLAYER_LOBBY,
        MULTI_PLAYER,
        SETTINGS,
        GAME_OVER
    }

//    public GameStateManager(final ZombieShooter app){
//        this.app = app;
//        //Initializes resource manager
//        this.resourceManager = new ResourceManager();
//
//        initGameStates();
//
//        //Set start screen here
//        setScreen(STATE.SPLASH);
//    }

    private void initGameStates(){
        this.gameState = new HashMap<GameStateManager.STATE, AbstractScreen>();

        // Add new game states here
        this.gameState.put(GameScreenManager.STATE.SINGLE_PLAYER, new PlayScreen(this, resourceManager));
        this.gameState.put(GameScreenManager.STATE.MAIN_MENU, new MenuScreen(this, resourceManager));
        this.gameState.put(GameScreenManager.STATE.SPLASH, new Splash(this, resourceManager));
        this.gameState.put(GameScreenManager.STATE.GAME_OVER, new GameOverScreen(this, resourceManager));
    }

    public void setScreen(GameScreenManager.STATE nextScreen) {
        app.setScreen(gameState.get(nextScreen));
    }

    public void resetPlayScreen(){
//        Ignore warning, this shit bang
        this.gameState.replace(GameScreenManager.STATE.SINGLE_PLAYER, new PlayScreen(this, resourceManager));
    }

    public void update(float dt){
        gameState.update(dt);
    }

    public void draw(){
        gameState.draw();
    }

    public void dispose(){
        for(AbstractScreen screen : gameState.values()) {
            if(screen != null) {
                screen.dispose();
            }
        }
    }

}
