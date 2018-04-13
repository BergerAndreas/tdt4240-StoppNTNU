package com.zombie.shooter.managers;

import com.badlogic.gdx.Game;

import java.io.Serializable;

/**
 * Created by Erikkvo on 13-Apr-18.
 */

public class GameData implements Serializable{

    private static final long serialVersionUID = 1;
//    Max number of scores to keep track of in highscore list
    private final int MAX_SCORES = 10;
    private long[] highscores;
    private String[] names;
    private int tentativeScore;

    public GameData(){
        highscores = new long[MAX_SCORES];
        names = new String[MAX_SCORES];

    }

//    Set up empty highscore table
    public void init(){
        for(int i=0; i<MAX_SCORES; i++){
            highscores[i] = 0;
            names[i] = "---";
        }
    }

    public long[] getHighscores(){
        return highscores;
    }

    public String[] getNames(){
        return names;
    }

    public long getTentativeScore(){
        return tentativeScore;
    }

    public void setTentativeScore(int i){
        this.tentativeScore = i;
    }

    public boolean isHighscore(long score){
        return score > highscores[MAX_SCORES-1];
    }

    public void addHighscore(long newHighscore, String name){
        if(isHighscore(newHighscore)){
            highscores[MAX_SCORES-1] = newHighscore;
            names[MAX_SCORES-1] = name;
            sortHighscores();
        }
    }

//    Sort highscore list
    public void sortHighscores(){
        for(int i=0; i<MAX_SCORES; i++){
            long score = highscores[i];
            String name = names[i];
            int j;
            for(j=i-1; j>=0 && highscores[j]<score; j++){
                highscores[j+1] = highscores[j];
                names[j+1] = names[j];
            }
            highscores[j+1] = score;
            names[j+1] = name;
        }
    }

}
