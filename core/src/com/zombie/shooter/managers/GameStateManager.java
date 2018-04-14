package com.zombie.shooter.managers;

import com.zombie.shooter.screens.AbstractState;
import com.zombie.shooter.screens.GameOverState;
import com.zombie.shooter.screens.MenuState;
import com.zombie.shooter.screens.Splash;
import com.zombie.shooter.utils.ResourceManager;

import java.util.HashMap;

/**
 * Created by Erikkvo on 14-Apr-18.
 */

public class GameStateManager {

    public GameStateManager(){
        initGameStates();
    }

    //    public final ZombieShooter app;
    private HashMap<STATE, AbstractState> gameState;
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
//        setState(STATE.SPLASH);
//    }

    private void initGameStates(){
        this.gameState = new HashMap<GameStateManager.STATE, AbstractState>();

        // Add new game states here
//        this.gameState.put(GameStateManager.STATE.SINGLE_PLAYER, new PlayState(this, resourceManager));
        this.gameState.put(GameStateManager.STATE.MAIN_MENU, new MenuState(this, resourceManager));
        this.gameState.put(GameStateManager.STATE.SPLASH, new Splash(this, resourceManager));
        this.gameState.put(GameStateManager.STATE.GAME_OVER, new GameOverState(this, resourceManager));
    }

    public void setState(GameStateManager.STATE nextState) {

        app.setState(gameState.get(nextState));
    }

    public void resetPlayScreen(){
//        Ignore warning, this shit bang
//        this.gameState.replace(GameStateManager.STATE.SINGLE_PLAYER, new PlayState(this, resourceManager));
    }

    public void update(float dt){

    }

    public void draw(){

    }

    public void dispose(){
        for(AbstractState state : gameState.values()) {
            if(state != null) {
                state.dispose();
            }
        }
    }

}
