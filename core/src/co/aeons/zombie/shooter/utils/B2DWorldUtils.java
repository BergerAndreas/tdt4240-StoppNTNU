package co.aeons.zombie.shooter.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import co.aeons.zombie.shooter.box2d.EnemyUserData;
import co.aeons.zombie.shooter.box2d.PlayerUserData;
import co.aeons.zombie.shooter.box2d.UserData;
import co.aeons.zombie.shooter.box2d.WallUserData;
import co.aeons.zombie.shooter.enums.EnemyType;
import co.aeons.zombie.shooter.enums.UserDataType;

/**
 * Created by Torstein on 4/5/2018.
 */

public class B2DWorldUtils {

    public static World createWorld() {
        return new World(B2DConstants.WORLD_GRAVITY, true);
    }

    public static Body createEnemy(World world, float x, float y) {
        EnemyType enemyType = EnemyType.BASIC_ENEMY;

        //Jaaaaank
        enemyType.setY(y);
        enemyType.setX(x);
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(enemyType.getX(), enemyType.getY()));
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(enemyType.getWidth() / 2, enemyType.getHeight() / 2);
        Body body = world.createBody(bodyDef);
        body.createFixture(shape, enemyType.getDensity());
        body.resetMassData();
        EnemyUserData userData = new EnemyUserData(enemyType.getWidth(), enemyType.getHeight());
        body.setUserData(userData);
        shape.dispose();
        return body;
    }

    public static Body createRunner(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(B2DConstants.PLAYER_X, B2DConstants.PLAYER_Y));
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(B2DConstants.PLAYER_WIDTH / 2, B2DConstants.PLAYER_HEIGHT / 2);
        Body body = world.createBody(bodyDef);
        body.setGravityScale(B2DConstants.PLAYER_GRAVITY_SCALE);
        body.createFixture(shape, B2DConstants.PLAYER_DENSITY);
        body.resetMassData();
        body.setUserData(new PlayerUserData(B2DConstants.PLAYER_WIDTH, B2DConstants.PLAYER_HEIGHT));
        shape.dispose();
        return body;
    }

    public static Body createWall(World world, float x, float y, float width, float height) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(
                new Vector2(x, y)
        );

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.0f, height);

        Body body = world.createBody(bodyDef);
        body.createFixture(shape, B2DConstants.WALL_DENSITY);

        WallUserData wallUserData = new WallUserData(B2DConstants.WALL_WIDTH, B2DConstants.WALL_HEIGHT);
        body.setUserData(wallUserData);
        shape.setAsBox(0.0f, B2DConstants.WALL_HEIGHT);
        shape.dispose();
        return body;
    }

    public static Body createChainShape(World world, Vector2[] verts) {

        Body body;
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        body = world.createBody(bodyDef);

        ChainShape shape = new ChainShape();
        shape.createChain(verts);
        body.createFixture(shape, 1.0f);
        shape.dispose();
        body.setUserData("Edge");

        return body;

    }

    public static boolean bodyIsEnemy(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.ENEMY;
    }

    public static boolean bodyIsWall(Body body) {
        UserData userData = (UserData) body.getUserData();
        return userData != null && userData.getUserDataType() == UserDataType.WALL;
    }

    public static void stopAnimation(Body body){

    }
}