package com.kalgarn.supermariobros.sprites.mapTileObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.kalgarn.supermariobros.SuperMarioBros;
import com.kalgarn.supermariobros.items.ItemDef;
import com.kalgarn.supermariobros.items.Mushroom;
import com.kalgarn.supermariobros.scenes.Hud;
import com.kalgarn.supermariobros.screens.PlayScreen;
import com.kalgarn.supermariobros.sprites.Mario;

/**
 * Created by Jerome on 13/01/2016.
 */
public class Coin extends InteractiveTileObject{
    private static TiledMapTileSet tileSet;
    private final int BLANK_COIN = 28; // sprite id 27, add 1

    public Coin(PlayScreen screen, float x, float y, TiledMapTileMapObject mapObject){
        super(screen, x, y ,mapObject);
//        tileSet = map.getTileSets().getTileSet("tileset_gutter");
//        fixture.setUserData(this);
//        setCategoryFilter(SuperMarioBros.COIN_BIT);
    }

//    @Override
//    public void onHeadHit(Mario mario) {
//        Gdx.app.log("coin","collision");
//        if(getCell().getTile().getId() == BLANK_COIN)
//            SuperMarioBros.manager.get("audio/sounds/bump.wav", Sound.class).play();
//        else {
//            if(mapObject.getProperties().containsKey("mushroom")) {
//                screen.spawnItem(new ItemDef(new Vector2(body.getPosition().x, body.getPosition().y + 16 / SuperMarioBros.PPM),
//                        Mushroom.class));
//                SuperMarioBros.manager.get("audio/sounds/powerup_spawn.wav", Sound.class).play();
//            }
//            else
//                SuperMarioBros.manager.get("audio/sounds/coin.wav", Sound.class).play();
//            getCell().setTile(tileSet.getTile(BLANK_COIN));
//            Hud.addScore(100);
//        }
//    }

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

    }
}
