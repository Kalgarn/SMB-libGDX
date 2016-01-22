package com.kalgarn.supermariobros;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by Jerome on 16/01/2016.
 */
public class Assets implements Disposable{

    public static Assets instance = null;

    private AssetManager manager;

    public Assets() {
        if (instance == null) {
            instance = this;
        }
        if (manager == null){
            manager = new AssetManager();
        }
        loadAssets();
    }

    private void loadAssets(){
        manager.load("audio/music/mario_music.ogg", Music.class);
        manager.load("audio/sounds/coin.wav", Sound.class);
        manager.load("audio/sounds/bump.wav", Sound.class);
        manager.load("audio/sounds/breakblock.wav", Sound.class);
        manager.load("audio/sounds/powerup_spawn.wav", Sound.class);
        manager.load("audio/sounds/powerup.wav", Sound.class);
        manager.load("audio/sounds/powerdown.wav", Sound.class);
        manager.load("audio/sounds/stomp.wav", Sound.class);
        manager.load("audio/sounds/mariodie.wav", Sound.class);

        manager.finishLoading();
    }

    @Override
    public void dispose() {
        manager.dispose();
    }
}
