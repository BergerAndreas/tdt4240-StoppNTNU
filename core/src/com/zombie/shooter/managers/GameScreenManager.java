package com.zombie.shooter.managers;

import com.badlogic.gdx.Screen;
import com.zombie.shooter.ZombieShooter;
import com.zombie.shooter.screens.AbstractScreen;
import com.zombie.shooter.screens.MenuScreen;
import com.zombie.shooter.screens.PlayScreen;
import com.zombie.shooter.screens.Splash;
import com.zombie.shooter.utils.ResourceManager;

import java.util.HashMap;

/**
 *  Created by opium on 09.03.18.
 */

public class GameScreenManager {

    public final ZombieShooter app;
    private HashMap<STATE, AbstractScreen> gameScreen;
    public ResourceManager resourceManager;

    //Add additional screens as they are added
    public enum STATE{
        SPLASH,
        MAIN_MENU,
        SINGLE_PLAYER,
        MULTI_PLAYER_LOBBY,
        MULTI_PLAYER,
        SETTINGS
    }

    public GameScreenManager(final ZombieShooter app){
        this.app = app;
        //Initializes resource manager
        this.resourceManager = new ResourceManager();

        initGameStates();

        //Set start screen here
        setScreen(STATE.SPLASH);
    }

    private void initGameStates(){
        this.gameScreen = new HashMap<STATE, AbstractScreen>();

        // Add new game states here
        this.gameScreen.put(STATE.SINGLE_PLAYER, new PlayScreen(app,resourceManager));
        this.gameScreen.put(STATE.MAIN_MENU, new MenuScreen(app,resourceManager));
        this.gameScreen.put(STATE.SPLASH, new Splash(app,resourceManager));
    }

    public void setScreen(STATE nextScreen) {
        app.setScreen(gameScreen.get(nextScreen));
    }

    public void dispose(){
        for(AbstractScreen screen : gameScreen.values()) {
            if(screen != null) {
                screen.dispose();
            }
        }
    }
}
