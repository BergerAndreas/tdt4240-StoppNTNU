package com.zombie.shooter.screens;


/**
 * Created by håvar opium on 09.03.18.
 */

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.zombie.shooter.ZombieShooter;
import com.zombie.shooter.actors.Bullet;
import com.zombie.shooter.actors.Enemy;
import com.zombie.shooter.actors.Player;
import com.zombie.shooter.managers.GameScreenManager;

import java.util.ArrayList;

/**
 * This is the main game screen
 * Methods that does not come from AbstractScreen should be placed on the bottom
 */

public class PlayScreen extends AbstractScreen{

    private SpriteBatch sb;
    private ShapeRenderer sr;

    private Player player;

    private BitmapFont font;

    private ArrayList<Bullet> bullets;
    private ArrayList<Enemy> enemies;

    public PlayScreen(GameScreenManager gsm){
        super(gsm);
        init();
    }

    @Override
    public void init() {

    }

    public void update(float dt) {
        // get user input
        // TODO: 13/04/2018 fiiix

        // update player bullets
        for(int i = 0; i < bullets.size(); i++) {
            bullets.get(i).update(dt);
            if(bullets.get(i).shouldRemove()) {
                bullets.remove(i);
                i--;
            }
        }

        // update asteroids
        for(int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update(dt);
            if(enemies.get(i).shouldRemove()) {
                enemies.remove(i);
                i--;
            }
        }

        // check collision
        checkCollisions();

    }

    private void checkCollisions() {

        // TODO: 13/04/2018 zombie wall collision

        // bullet-enemy collision
        for(int i = 0; i < bullets.size(); i++) {
            Bullet b = bullets.get(i);
            for(int j = 0; j < enemies.size(); j++) {
                Enemy a = enemies.get(j);
                if(a.contains(b)) {
                    bullets.remove(i);
                    i--;
                    enemies.remove(j);
                    j--;
                    player.incrementScore(a.getScore());
                    break;
                }
            }
        }

    }

    public void draw() {
        sb.setProjectionMatrix(ZombieShooter.cam.combined);
        sr.setProjectionMatrix(ZombieShooter.cam.combined);

        // draw enemies
        for(int i = 0; i < enemies.size(); i++) {
            enemies.get(i).draw(sb);
        }


    }

    @Override
    public void handleInput() {

    }

    @Override
    public void dispose() {

    }

}
