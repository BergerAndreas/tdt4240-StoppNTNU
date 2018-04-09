package com.zombie.shooter.box2d;

import com.badlogic.gdx.math.Vector2;
import com.zombie.shooter.enums.UserDataType;
import com.zombie.shooter.utils.B2DConstants;

public class WallUserData extends UserData {

    private final Vector2 wallPosition =
            new Vector2(B2DConstants.WALL_X, B2DConstants.WALL_Y);


    public WallUserData(float width, float height){
        super(width, height);
        userDataType = UserDataType.WALL;
    }

}
