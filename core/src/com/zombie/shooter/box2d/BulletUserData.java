package com.zombie.shooter.box2d;

import com.badlogic.gdx.math.Vector2;
import com.zombie.shooter.enums.UserDataType;

public class BulletUserData extends UserData{

    private final Vector2 bulletPosition =
            new Vector2(4,4);

    public BulletUserData(float width, float height) {
        super(width, height);
        userDataType = UserDataType.BULLET;
    }

}
