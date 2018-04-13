package com.zombie.shooter.box2d;

import com.badlogic.gdx.math.Vector2;
import com.zombie.shooter.enums.UserDataType;
import com.zombie.shooter.utils.B2DConstants;

/**
 * Created by Torstein on 4/5/2018.
 */

public class PlayerUserData extends UserData {
    private final Vector2 runningPosition = new Vector2(B2DConstants.PLAYER_X, B2DConstants.PLAYER_Y);
    public float x;
    public float y;

    public PlayerUserData(float width, float height, float x, float y) {
        super(width, height);
        this.x = x;
        this.y = y;
        userDataType = UserDataType.PLAYER;
    }

    public Vector2 getRunningPosition() {
        return runningPosition;
    }

}
