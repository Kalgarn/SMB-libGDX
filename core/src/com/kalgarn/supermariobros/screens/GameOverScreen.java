package com.kalgarn.supermariobros.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kalgarn.supermariobros.SuperMarioBros;

/**
 * Created by Jerome on 15/01/2016.
 */
public class GameOverScreen implements Screen {
    private Viewport viewport;
    private Stage stage;

    private Game game;
    private Music music;

    public GameOverScreen(Game game){
        this.game = game;
        viewport = new FitViewport(SuperMarioBros.WINDOW_WIDTH, SuperMarioBros.WINDOW_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((SuperMarioBros) game).batch);

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(Gdx.files.internal("fonts/smb_font.fnt")), Color.WHITE);

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        Label gameOverLabel = new Label("GAME OVER", font);
        Label playAgainLabel = new Label("Click to Play Again", font);

        table.add(gameOverLabel).expandX();
        table.row();
        table.add(playAgainLabel).expandX().padTop(10f);

        stage.addActor(table);

        music = SuperMarioBros.manager.get("audio/music/game_over.ogg", Music.class);
        music.setVolume(0.3f);
        music.play();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(Gdx.input.justTouched()) {
            game.setScreen(new PlayScreen((SuperMarioBros) game));
            dispose();
        }
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

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
        stage.dispose();
    }
}
