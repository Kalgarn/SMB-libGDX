package com.kalgarn.supermariobros.sprites.mapTileObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Vector2;
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

    public Coin(PlayScreen screen, MapObject object){
        super(screen, object);
        tileSet = map.getTileSets().getTileSet("tileset_gutter");
        fixture.setUserData(this);
        setCategoryFilter(SuperMarioBros.COIN_BIT);
    }

    @Override
    public void onHeadHit(Mario mario) {
        Gdx.app.log("coin","collision");
        if(getCell().getTile().getId() == BLANK_COIN)
            SuperMarioBros.manager.get("audio/sounds/bump.wav", Sound.class).play();
        else {
            if(object.getProperties().containsKey("mushroom")) {
                screen.spawnItem(new ItemDef(new Vector2(body.getPosition().x, body.getPosition().y + 16 / SuperMarioBros.PPM),
                        Mushroom.class));
                SuperMarioBros.manager.get("audio/sounds/powerup_spawn.wav", Sound.class).play();
            }
            else
                SuperMarioBros.manager.get("audio/sounds/coin.wav", Sound.class).play();
            getCell().setTile(tileSet.getTile(BLANK_COIN));
            Hud.addScore(100);
        }
    }
}
