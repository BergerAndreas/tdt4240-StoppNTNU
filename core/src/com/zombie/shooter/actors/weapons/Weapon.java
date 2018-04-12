package com.zombie.shooter.actors.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.zombie.shooter.actors.bullets.Bullet;

import java.util.LinkedList;
import java.util.Queue;

public class Weapon {

    private String weaponName;
    private Texture texture;
    private int clipSize;
    private int fireRate;
    private Vector2 bulletDirection;
    private float bulletSpeed;
    private Queue<Bullet> clip;
    private int clipAmount; //How many clips the weapon has

    //TODO: Add userData

    public Weapon(String weaponName,
                  String texturePath,
                  int clipSize,
                  int fireRate,
                  Vector2 bulletSpeed,
                  Vector2 bulletDirection) {

        this.clip = new LinkedList<Bullet>();
        this.weaponName = weaponName;
        this.texture = new Texture(texturePath);
        this.clipSize = clipSize;
        this.bulletDirection = bulletDirection;
        this.bulletDirection = bulletSpeed;
    }

    public void reload(Bullet bullet) {
        clip.clear();
        for (int i = 0; i < clipSize; i++) {
            clip.add(bullet);
        }
        setClipAmount(this.getClipAmount() - 1);
    }

    public void shoot() {
        if (clip.isEmpty()) {
            System.out.println("Gun is empty, reload");
            return;
        }

        Bullet currentBullet = clip.poll();
        currentBullet.setBulletDirection(getBulletDirection());
    }

    //Getters and setters
    //All the interesting code is above
    public Queue<Bullet> getClip() {
        return clip;
    }

    public void setClip(Queue<Bullet> clip) {
        this.clip = clip;
    }

    public int getClipAmount() {
        return clipAmount;
    }

    public void setClipAmount(int clipAmount) {
        this.clipAmount = clipAmount;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public int getClipSize() {
        return clipSize;
    }

    public int getFireRate() {
        return fireRate;
    }

    public Vector2 getBulletDirection() {
        return bulletDirection;
    }

}
