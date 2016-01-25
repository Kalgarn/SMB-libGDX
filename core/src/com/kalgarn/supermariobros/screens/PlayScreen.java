package com.kalgarn.supermariobros.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kalgarn.supermariobros.SuperMarioBros;
import com.kalgarn.supermariobros.enemies.Enemy;
import com.kalgarn.supermariobros.items.Item;
import com.kalgarn.supermariobros.items.ItemDef;
import com.kalgarn.supermariobros.items.Mushroom;
import com.kalgarn.supermariobros.scenes.Hud;
import com.kalgarn.supermariobros.sprites.Mario;
import com.kalgarn.supermariobros.b2dtools.B2WorldCreator;
import com.kalgarn.supermariobros.b2dtools.WorldContactListener;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Jerome on 13/01/2016.
 */
public class PlayScreen implements Screen {

    private SuperMarioBros smbGame; //Reference to our Game, used to set Screens
    private TextureAtlas atlas;
    public static boolean alreadyDestroyed = false;

    //basic playscreen variables
    private OrthographicCamera cam;
    private Viewport gameViewport;
    private Hud hud;

    //Tiled map variables
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //Box2d variables
    private World world;
    private Box2DDebugRenderer b2dr;
    private B2WorldCreator creator;

    //sprites
    private Mario player;

    private Music music;

    private Array<Item> items;
    private LinkedBlockingQueue<ItemDef> itemsToSpawn;

    Texture texture;

    private float mapWidth;

    public PlayScreen(SuperMarioBros game){
        this.smbGame = game;

        atlas = new TextureAtlas("Mario_and_Enemies.pack");
        cam = new OrthographicCamera(); //create cam used to follow mario through cam world
        //create a FitViewport to maintain virtual aspect ratio despite screen size
        // = new FitViewport(800, 480, cam); // ScreenViewPort
        gameViewport = new FitViewport(SuperMarioBros.VIEWPORT_WIDTH / SuperMarioBros.PPM, SuperMarioBros.VIEWPORT_HEIGHT / SuperMarioBros.PPM);
        gameViewport.setCamera(cam);
        //texture = new Texture("badlogic.jpg");

        hud = new Hud(game.batch); //create our game HUD for scores/timers/level info

        //Load our map and setup our map renderer
        maploader = new TmxMapLoader();
        map = maploader.load("level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1  / SuperMarioBros.PPM);

        //initially set our gamcam to be centered correctly at the start of the map
        cam.position.set((SuperMarioBros.VIEWPORT_WIDTH /2) /SuperMarioBros.PPM, (SuperMarioBros.VIEWPORT_HEIGHT / 2) / SuperMarioBros.PPM, 0);

        //create our Box2D world, setting no gravity in X, -10 gravity in Y, and allow bodies to sleep
        world = new World(new Vector2(0, -10), true);
        //allows for debug lines of our box2d world.
        b2dr = new Box2DDebugRenderer();

        creator = new B2WorldCreator(this);

        //create mario in our game world
        player = new Mario(this);

        world.setContactListener(new WorldContactListener());

        music = SuperMarioBros.manager.get("audio/music/mario_music.ogg", Music.class);
        music.setLooping(true);
        music.setVolume(0.3f);
        music.play();

        items = new Array<Item>();
        itemsToSpawn = new LinkedBlockingQueue<ItemDef>();

        mapWidth = ((TiledMapTileLayer) map.getLayers().get(0)).getWidth();
    }

    public void spawnItem(ItemDef idef){
        itemsToSpawn.add(idef);
    }

    public void handleSpawningItems(){
        if(!itemsToSpawn.isEmpty()){
            ItemDef idef = itemsToSpawn.poll();
            if(idef.type == Mushroom.class){
                items.add(new Mushroom(this, idef.position.x, idef.position.y));
            }
        }
    }

    public TextureAtlas getAtlas(){
        return atlas;
    }

    @Override
    public void show() {

    }

    public void handleInput(float dt){
        //control our player using immediate impulses
        if(player.currentState != Mario.State.DEAD) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP))
                player.jump();
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 2)
                player.b2body.applyLinearImpulse(new Vector2(0.1f, 0), player.b2body.getWorldCenter(), true);
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -2)
                player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2body.getWorldCenter(), true);
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
                player.fire();

        }

    }

    public void update(float dt){
        //handle user input first
        handleInput(dt);
        handleSpawningItems();

        //takes 1 step in the physics simulation(60 times per second)
        world.step(1 / 60f, 6, 2);
        //update ennemy
        player.update(dt);
        for(Enemy enemy : creator.getEnemies()) {
            enemy.update(dt);
            if(enemy.getX() < player.getX() + 224 / SuperMarioBros.PPM) {
                enemy.b2body.setActive(true);
            }
        }
        // update item
        for(Item item : items)
            item.update(dt);

        hud.update(dt);

        //attach our gamecam to our players.x coordinate
        float posX = cam.position.x;
        if(player.currentState != Mario.State.DEAD) {
            cam.position.x = player.b2body.getPosition().x;
          posX = MathUtils.clamp(player.b2body.getPosition().x, (SuperMarioBros.VIEWPORT_WIDTH / 2) / SuperMarioBros.PPM, (mapWidth - SuperMarioBros.VIEWPORT_WIDTH / 2) / SuperMarioBros.PPM);

        }
        cam.position.x = MathUtils.lerp(cam.position.x, posX, 0.1f);
        if (Math.abs(cam.position.x - posX) < 0.1f) {
            cam.position.x = posX;
        }

        //
//        if ((cam.position.x - (SuperMarioBros.V_WIDTH /2) <= 0)){
//            cam.position.x = (SuperMarioBros.V_WIDTH /2) / SuperMarioBros.PPM;
//        }
        //update our gamecam with correct coordinates after changes
        cam.update();
        //tell our renderer to draw only what our camera can see in our game world.
        renderer.setView(cam);


    }

    @Override
    public void render(float delta) {
        //separate our update logic from render
        update(delta);

        //Clear the game screen with Black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //render our game map
        renderer.render();

        //renderer our green Box2DDebugLines
        b2dr.render(world, cam.combined);

        smbGame.batch.setProjectionMatrix(cam.combined);
        smbGame.batch.begin();
        player.draw(smbGame.batch);
        for (Enemy enemy : creator.getEnemies())
            enemy.draw(smbGame.batch);
        for (Item item : items)
            item.draw(smbGame.batch);
        smbGame.batch.end();

        //Set our batch to now draw what the Hud camera sees.
        smbGame.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

        if(gameOver()){
            smbGame.setScreen(new GameOverScreen(smbGame));
            dispose();
        }

    }

    public boolean gameOver(){
        if(player.currentState == Mario.State.DEAD && player.getStateTimer() > 3){
            return true;
        }
        return false;
    }

    @Override
    public void resize(int width, int height) {
        //updated our game viewport
        gameViewport.update(width, height);
    }

    public TiledMap getMap(){
        return map;
    }
    public World getWorld(){
        return world;
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
        //dispose of all our opened resources
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
    }

    public Hud getHud(){ return hud; }

    public float getMapWidth() {
        return mapWidth;
    }
}
