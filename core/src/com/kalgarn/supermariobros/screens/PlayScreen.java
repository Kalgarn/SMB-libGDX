package com.kalgarn.supermariobros.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kalgarn.supermariobros.SuperMarioBros;
import com.kalgarn.supermariobros.scenes.Hud;

/**
 * Created by Jerome on 13/01/2016.
 */
public class PlayScreen implements Screen {

    private SuperMarioBros smbGame; //Reference to our Game, used to set Screens

    private OrthographicCamera cam;
    private Viewport gamePort;
    private Hud hud;

    Texture texture;

    public PlayScreen(SuperMarioBros game){
        this.smbGame = game;

        cam = new OrthographicCamera();
        gamePort = new FitViewport(800, 480, cam); // ScreenViewPort
        texture = new Texture("badlogic.jpg");

        hud = new Hud(game.batch);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        smbGame.batch.setProjectionMatrix(cam.combined);
        smbGame.batch.begin();
        smbGame.batch.draw(texture, 10, 10);
        smbGame.batch.end();

        //Set our batch to now draw what the Hud camera sees.
        smbGame.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
