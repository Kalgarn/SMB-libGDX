package com.kalgarn.supermariobros.sprites.mapTileObjects;

import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.kalgarn.supermariobros.SuperMarioBros;
import com.kalgarn.supermariobros.screens.PlayScreen;
import com.kalgarn.supermariobros.sprites.Mario;

/**
 * Created by Jerome on 26/01/2016.
 */
public class Pipe extends InteractiveTileObject{
    public Pipe(PlayScreen playScreen, float x, float y, TiledMapTileMapObject mapObject) {
        super(playScreen, x, y, mapObject);
    }

    @Override
    protected void defineBody() {

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(getX(), getY());
        bodyDef.type = BodyDef.BodyType.StaticBody;

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

    }

    @Override
    public void onHeadHit(Mario mario) {

    }
}
