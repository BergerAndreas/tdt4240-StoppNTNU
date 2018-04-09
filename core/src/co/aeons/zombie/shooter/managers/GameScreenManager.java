package co.aeons.zombie.shooter.managers;

import com.badlogic.gdx.Screen;
import co.aeons.zombie.shooter.ZombieShooter;
import co.aeons.zombie.shooter.screens.AbstractScreen;
import co.aeons.zombie.shooter.screens.MenuScreen;
import co.aeons.zombie.shooter.screens.PlayScreen;

import java.util.HashMap;

/**
 *  Created by opium on 09.03.18.
 */

public class GameScreenManager {

    public final ZombieShooter app;
    private HashMap<STATE, AbstractScreen> gameScreen;

    //Add additional screens as they are added
    public enum STATE{
        MAIN_MENU,
        SINGLE_PLAYER,
        MULTI_PLAYER_LOBBY,
        MULTI_PLAYER,
        SETTINGS
    }

    public GameScreenManager(final ZombieShooter app){
        this.app = app;

        initGameStates();

        //Set start screen here
        setScreen(STATE.MAIN_MENU);
    }

    private void initGameStates(){
        this.gameScreen = new HashMap<STATE, AbstractScreen>();

        // Add new game states here
        this.gameScreen.put(STATE.SINGLE_PLAYER, new PlayScreen(app));
        this.gameScreen.put(STATE.MAIN_MENU, new MenuScreen(app));
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