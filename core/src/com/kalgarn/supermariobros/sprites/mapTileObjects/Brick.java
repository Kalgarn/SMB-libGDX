package com.kalgarn.supermariobros.sprites.mapTileObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.kalgarn.supermariobros.SuperMarioBros;
import com.kalgarn.supermariobros.scenes.Hud;
import com.kalgarn.supermariobros.screens.PlayScreen;
import com.kalgarn.supermariobros.sprites.Collider;
import com.kalgarn.supermariobros.sprites.Mario;

/**
 * Created by Jerome on 13/01/2016.
 */
public class Brick extends InteractiveTileObject {
    public Brick(PlayScreen screen,float x, float y, TiledMapTileMapObject mapObject){
        super(screen, x, y, mapObject);
//        fixture.setUserData(this);
        setCategoryFilter(SuperMarioBros.BRICK_BIT);
    }

//
//    public void onHeadHit(Mario mario) {
//        Gdx.app.log("brick","collision");
//        if(mario.isBig()) {
//            setCategoryFilter(SuperMarioBros.DESTROYED_BIT);
//            getCell().setTile(null);
//            Hud.addScore(200);
//            SuperMarioBros.manager.get("audio/sounds/breakblock.wav", Sound.class).play();
//        }
//        SuperMarioBros.manager.get("audio/sounds/bump.wav", Sound.class).play();
//    }


    public void trigger(Collider other) {
        Gdx.app.log("brick","collision");
        if (other.getFilter().categoryBits == SuperMarioBros.MARIO_HEAD_BIT) {

                if (((Mario) other.getUserData()).marioIsBig()) {
                        setCategoryFilter(SuperMarioBros.DESTROYED_BIT);
                        getCell().setTile(null);
                        Hud.addScore(200);
                        SuperMarioBros.manager.get("audio/sounds/breakblock.wav", Sound.class).play();
                    }
                    SuperMarioBros.manager.get("audio/sounds/bump.wav", Sound.class).play();
        }}

    @Override
    protected void defineBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(getX(), getY());
        bodyDef.type = BodyDef.BodyType.KinematicBody;

        b2body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(16 / SuperMarioBros.PPM / 2, 16 / SuperMarioBros.PPM / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.filter.categoryBits = SuperMarioBros.GROUND_BIT;
        fixtureDef.shape = shape;

        b2body.createFixture(fixtureDef).setUserData(this);

        shape.dispose();
    }

    @Override
    public void update(float delta) {
        if (destroyed) {
            return;
        }

        if (toBeDestroyed) {
            setBounds(0, 0, 0, 0);
            world.destroyBody(b2body);
            destroyed = true;
            return;
        }
    }


}
