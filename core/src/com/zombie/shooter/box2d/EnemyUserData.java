package com.zombie.shooter.box2d;

/**
 * Created by Torstein on 4/5/2018.
 */

import com.badlogic.gdx.math.Vector2;
import com.zombie.shooter.ZombieShooter;
import com.zombie.shooter.enums.UserDataType;
import com.zombie.shooter.utils.B2DConstants;

public class EnemyUserData extends UserData {

    private Vector2 linearVelocity;

    public EnemyUserData(float width, float height) {
        super(width, height);
        userDataType = UserDataType.ENEMY;
        linearVelocity = B2DConstants.ENEMY_LINEAR_VELOCITY;
    }

    public void setLinearVelocity(Vector2 linearVelocity) {
        this.linearVelocity = linearVelocity;
    }

    public Vector2 getLinearVelocity() {
        return linearVelocity;
    }


}