package com.zombie.shooter.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.zombie.shooter.actors.buttons.MuteButton;


public class AudioUtils {

    private static AudioUtils ourInstance = new AudioUtils();
    private static Music music;
    private static Sound jumpSound;
    private static Sound hitSound;

    private static final String MUSIC_ON_PREFERENCE = "music_on";
    private static final String SOUND_ON_PREFERENCE = "sound_on";
    private boolean isMuted;

    private AudioUtils() {
    }

    public static AudioUtils getInstance() {
        return ourInstance;
    }

    public Music getMusic() {
        return music;
    }


    public void init() {
        music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        music.setLooping(true);
        this.isMuted = false;
        playMusic();
    }

    public Sound createSound(String soundFileName) {
        return Gdx.audio.newSound(Gdx.files.internal(soundFileName));
    }

    public void playMusic() {
        if (!music.isPlaying()){
            music.play();
        }
    }

    public boolean getIsMuted(){
        return this.isMuted;
    }

    public void toggleMuteMusic(){
        if(! this.isMuted){
            music.setVolume(0);
            this.isMuted = true;
            System.out.println("Mute pressed");
        }else{
            music.setVolume(100);
            this.isMuted = false;
            System.out.println("Unmute pressed");
        }

    }

    public static void dispose() {
        music.dispose();
        jumpSound.dispose();
        hitSound.dispose();
    }

    // TODO: 11/04/2018 unfuck disse dåsene
    public Sound getJumpSound() {
        return jumpSound;
    }

    public Sound getHitSound() {
        return hitSound;
    }
}