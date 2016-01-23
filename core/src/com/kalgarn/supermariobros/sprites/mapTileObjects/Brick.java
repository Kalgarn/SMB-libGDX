package com.kalgarn.supermariobros.sprites.mapTileObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.MapObject;
import com.kalgarn.supermariobros.SuperMarioBros;
import com.kalgarn.supermariobros.scenes.Hud;
import com.kalgarn.supermariobros.screens.PlayScreen;
import com.kalgarn.supermariobros.sprites.Mario;

/**
 * Created by Jerome on 13/01/2016.
 */
public class Brick extends InteractiveTileObject {
    public Brick(PlayScreen screen, MapObject object){
        super(screen, object);
        fixture.setUserData(this);
        setCategoryFilter(SuperMarioBros.BRICK_BIT);
    }

    @Override
    public void onHeadHit(Mario mario) {
        Gdx.app.log("brick","collision");
        if(mario.isBig()) {
            setCategoryFilter(SuperMarioBros.DESTROYED_BIT);
            getCell().setTile(null);
            Hud.addScore(200);
            SuperMarioBros.manager.get("audio/sounds/breakblock.wav", Sound.class).play();
        }
        SuperMarioBros.manager.get("audio/sounds/bump.wav", Sound.class).play();
    }
}
