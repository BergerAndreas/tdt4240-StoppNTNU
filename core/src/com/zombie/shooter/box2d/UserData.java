package com.zombie.shooter.box2d;

import com.zombie.shooter.enums.UserDataType;

/**
 * Created by andreasberger on 11/03/2018.
 */

public abstract class UserData {

    protected UserDataType userDataType;
    protected float width;
    protected float height;

    public UserData() {

    }

    public UserData(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public UserDataType getUserDataType() {
        return userDataType;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

}