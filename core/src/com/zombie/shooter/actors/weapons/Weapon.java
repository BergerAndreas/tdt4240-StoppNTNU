package com.zombie.shooter.actors.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.zombie.shooter.actors.bullets.Bullet;
import com.zombie.shooter.utils.B2DWorldUtils;

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
    private World world;

    public Weapon(String weaponName,
                  String texturePath,
                  int clipSize,
                  int fireRate,
                  float bulletSpeed,
                  Vector2 bulletDirection,
                  World world,
                  float spawn_y
    ) {

        this.clip = new LinkedList<Bullet>();
        this.weaponName = weaponName;
        this.texture = new Texture(texturePath);
        this.clipSize = clipSize;
        this.bulletDirection = bulletDirection;
        this.bulletSpeed = bulletSpeed;
        this.world = world;



        reload();
    }

    public void reload() {
        clip.clear();
        System.out.println("Reload");
        for (int i = 0; i < clipSize; i++) {
            clip.add(new Bullet("Weapons/pistol1.png", 30, B2DWorldUtils.createBullet(world)));
        }
        setClipAmount(this.getClipAmount() - 1);
    }

    public Bullet shoot(float y) {
        if (clip.isEmpty()) {
            System.out.println("Gun is empty, reload");
            return null;
        }

        Bullet currentBullet = clip.poll();
        currentBullet.setPosi_y(y);
        currentBullet.getBody().setLinearVelocity(10f, 0f);
        currentBullet.setBulletDirection(getBulletDirection());
        return currentBullet;
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
