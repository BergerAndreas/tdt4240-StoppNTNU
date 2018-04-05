package com.zombie.shooter.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.zombie.shooter.box2d.EnemyUserData;
import com.zombie.shooter.enums.EnemyType;

/**
 * Created by Torstein on 4/5/2018.
 */

public class B2DWorldUtils {

    public static World createWorld() {
        return new World(B2DConstants.WORLD_GRAVITY, true);
    }

    public static Body createEnemy(World world) {
        EnemyType enemyType = EnemyType.BASIC_ENEMY;
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(new Vector2(enemyType.getX(), enemyType.getY()));
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(enemyType.getWidth() / 2, enemyType.getHeight() / 2);
        Body body = world.createBody(bodyDef);
        body.createFixture(shape, enemyType.getDensity());
        body.resetMassData();
        EnemyUserData userData = new EnemyUserData(enemyType.getWidth(), enemyType.getHeight(),
                enemyType.getAnimationAssetId());
        body.setUserData(userData);
        shape.dispose();
        return body;
    }

}