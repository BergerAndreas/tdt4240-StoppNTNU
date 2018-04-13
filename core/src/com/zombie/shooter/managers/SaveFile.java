package com.zombie.shooter.managers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Erikkvo on 13-Apr-18.
 */

public class SaveFile {

    public static GameData gameData;

//    Write to save file
    public static void save(){
        try{
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("highscores.sav"));
            out.writeObject(gameData);
            out.close();
        }
        catch (Exception e){
            e.printStackTrace();
            Gdx.app.exit();
        }
    }

//    Read from save file, and put into GameData
    public static void load(){
        try{
            if(!saveFileExists()){
                init();
                return;
            }
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("highscores.sav"));
            gameData = (GameData)in.readObject();
            in.close();
        }
        catch(Exception e){
            e.printStackTrace();
            Gdx.app.exit();
        }
    }

    public static boolean saveFileExists(){
        File f = new File("highscores.sav");
        return f.exists();
    }

    public static void init(){
        gameData = new GameData();
        gameData.init();
        save();
    }

}
