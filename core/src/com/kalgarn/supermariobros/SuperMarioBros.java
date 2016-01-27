package com.kalgarn.supermariobros;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.kalgarn.supermariobros.screens.PlayScreen;

public class SuperMarioBros extends Game {

	//Virtual Screen size and Box2D Scale
	public static final int WINDOW_WIDTH = 400;
	public static final int WINDOW_HEIGHT = 208;

	public static final float VIEWPORT_WIDTH = 400;
	public static final float VIEWPORT_HEIGHT = 208;
	// pixel per meter
	public static final float PPM = 16;

	public static final Vector2 GRAVITY = new Vector2(0.0f, -9.8f * 4);

	public static final float STEP = 1 / 60.0f;

	//Box2D Collision Bits
	public static final short NOTHING_BIT = 0;
	public static final short GROUND_BIT = 1;
	public static final short MARIO_BIT = 2;
	public static final short BRICK_BIT = 4;
	public static final short COIN_BIT = 8;
	public static final short DESTROYED_BIT = 16;
	public static final short OBJECT_BIT = 32;
	public static final short ENEMY_BIT = 64;
	public static final short ENEMY_HEAD_BIT = 128;
	public static final short ITEM_BIT = 256;
	public static final short MARIO_HEAD_BIT = 512;
	public static final short FIREBALL_BIT = 1024;

	public SpriteBatch batch;

	/* WARNING Using AssetManager in a static way can cause issues, especially on Android.
Instead you may want to pass around Assetmanager to those the classes that need it.
We will use it in the static context to save time for now. */
	public static AssetManager manager;

	@Override
	public void create () {
		batch = new SpriteBatch();
		manager = new AssetManager();
        loadAudio();

		this.setScreen(new PlayScreen(this));
	}

	public void loadAudio(){
        manager.load("audio/music/mario_music.ogg", Music.class);
        manager.load("audio/music/game_over.ogg", Music.class);
        manager.load("audio/sounds/coin.wav", Sound.class);
        manager.load("audio/sounds/bump.wav", Sound.class);
        manager.load("audio/sounds/breakblock.wav", Sound.class);
        manager.load("audio/sounds/powerup_spawn.wav", Sound.class);
        manager.load("audio/sounds/powerup.wav", Sound.class);
        manager.load("audio/sounds/powerdown.wav", Sound.class);
        manager.load("audio/sounds/stomp.wav", Sound.class);
        manager.load("audio/sounds/mariodie.wav", Sound.class);
        manager.load("audio/sounds/jump_small.wav", Sound.class);
		manager.load("audio/sounds/jump_super.wav", Sound.class);
		manager.load("audio/sounds/fireball.ogg", Sound.class);
        manager.load("audio/sounds/kick.ogg", Sound.class);

        manager.finishLoading();
	}

	@Override
	public void dispose() {
		super.dispose();

		manager.dispose();
		batch.dispose();
	}

	@Override
	public void render () {
		super.render();

	}

}
