package com.kalgarn.supermariobros.b2dtools;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.kalgarn.supermariobros.SuperMarioBros;
import com.kalgarn.supermariobros.enemies.Enemy;
import com.kalgarn.supermariobros.enemies.Goomba;
import com.kalgarn.supermariobros.enemies.Koopa;
import com.kalgarn.supermariobros.screens.PlayScreen;
import com.kalgarn.supermariobros.sprites.mapTileObjects.Brick;
import com.kalgarn.supermariobros.sprites.mapTileObjects.Coin;
import com.kalgarn.supermariobros.sprites.mapTileObjects.InteractiveTileObject;
import com.kalgarn.supermariobros.sprites.mapTileObjects.MetalBlock;
import com.kalgarn.supermariobros.sprites.mapTileObjects.Pipe;
import com.kalgarn.supermariobros.sprites.mapTileObjects.Rock;

/**
 * Created by Jerome on 13/01/2016.
 */
public class B2WorldCreator {
    private Array<Goomba> goombas;
    private Array<Koopa> turtles;

    private Array<InteractiveTileObject> mapTileObjects;
    private Vector2 startPosition;



    public B2WorldCreator(PlayScreen screen, TiledMap tiledMap){
       // World world = screen.getWorld();
       // TiledMap map = screen.getMap();
        mapTileObjects = new Array<InteractiveTileObject>();
        turtles = new Array<Koopa>();
        goombas = new Array<Goomba>();
        //create body and fixture variables
//        BodyDef bdef = new BodyDef();
//        PolygonShape shape = new PolygonShape();
//        FixtureDef fdef = new FixtureDef();
//        Body body;

        //create ground bodies/fixtures
//        for(MapObject object : tiledMap.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
//            Rectangle rect = ((RectangleMapObject) object).getRectangle();

//            bdef.type = BodyDef.BodyType.StaticBody;
//            bdef.position.set((rect.getX() + rect.getWidth() / 2) / SuperMarioBros.PPM, (rect.getY() + rect.getHeight() / 2) / SuperMarioBros.PPM);

          //  body = world.createBody(bdef);

            //shape.setAsBox(rect.getWidth() / 2 / SuperMarioBros.PPM, rect.getHeight() / 2 / SuperMarioBros.PPM);
            //fdef.shape = shape;
            //body.createFixture(fdef);
     //   }
        MapLayer mapLayer = tiledMap.getLayers().get("Rocks");
        if (mapLayer != null) {
            for (MapObject mapObject : mapLayer.getObjects()) {
                float x = ((TiledMapTileMapObject) mapObject).getX();
                float y = ((TiledMapTileMapObject) mapObject).getY();

                mapTileObjects.add(new Rock(screen, (x + 8) / SuperMarioBros.PPM, (y + 8) / SuperMarioBros.PPM, (TiledMapTileMapObject) mapObject));
            }
        }

        mapLayer = tiledMap.getLayers().get("MetalBlocks");
        if (mapLayer != null) {
            for (MapObject mapObject : mapLayer.getObjects()) {
                float x = ((TiledMapTileMapObject) mapObject).getX();
                float y = ((TiledMapTileMapObject) mapObject).getY();

                mapTileObjects.add(new MetalBlock(screen, (x + 8) / SuperMarioBros.PPM, (y + 8) / SuperMarioBros.PPM, (TiledMapTileMapObject) mapObject));
            }
        }

        mapLayer = tiledMap.getLayers().get("Pipes");
        if (mapLayer != null) {
            for (MapObject mapObject : mapLayer.getObjects()) {
                float x = ((TiledMapTileMapObject) mapObject).getX();
                float y = ((TiledMapTileMapObject) mapObject).getY();

                mapTileObjects.add(new Pipe(screen, (x + 8) / SuperMarioBros.PPM, (y + 8) / SuperMarioBros.PPM, (TiledMapTileMapObject) mapObject));
            }
        }
        //create pipe bodies/fixtures
//        for(MapObject object : tiledMap.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
//            Rectangle rect = ((RectangleMapObject) object).getRectangle();

//            bdef.type = BodyDef.BodyType.StaticBody;
//            bdef.position.set((rect.getX() + rect.getWidth() / 2) / SuperMarioBros.PPM, (rect.getY() + rect.getHeight() / 2) / SuperMarioBros.PPM);

//            body = world.createBody(bdef);
//
//            shape.setAsBox(rect.getWidth() / 2 / SuperMarioBros.PPM, rect.getHeight() / 2 / SuperMarioBros.PPM);
//            fdef.shape = shape;
//            fdef.filter.categoryBits = SuperMarioBros.OBJECT_BIT;
//            body.createFixture(fdef);
       // }

        //create brick bodies/fixtures
//        for(MapObject object : tiledMap.getLayers().get(5).getObjects()){
//            float x = tiledMap.
//            float y = ((TiledMapTileMapObject) object).getY();
//            new Brick(screen, (x + 8) / SuperMarioBros.PPM, (y + 8) / SuperMarioBros.PPM, (TiledMapTileMapObject) object);
//        }
        mapLayer = tiledMap.getLayers().get("Bricks");
        for (MapObject mapObject : mapLayer.getObjects()) {
            float x = ((TiledMapTileMapObject) mapObject).getX();
            float y = ((TiledMapTileMapObject) mapObject).getY();

            mapTileObjects.add(new Brick(screen, (x + 8) / SuperMarioBros.PPM, (y + 8) / SuperMarioBros.PPM, (TiledMapTileMapObject) mapObject));
        }
        //create coin bodies/fixtures
//        for(MapObject object : tiledMap.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
//            float x = ((TiledMapTileMapObject) object).getX();
//            float y = ((TiledMapTileMapObject) object).getY();
//            new Coin(screen, (x+8) / SuperMarioBros.PPM, (y + 8) / SuperMarioBros.PPM, (TiledMapTileMapObject) object);
        mapLayer = tiledMap.getLayers().get("CoinBlocks");
            for (MapObject mapObject : mapLayer.getObjects()) {
                float x = ((TiledMapTileMapObject) mapObject).getX();
                float y = ((TiledMapTileMapObject) mapObject).getY();

                mapTileObjects.add(new Coin(screen, (x + 8) / SuperMarioBros.PPM, (y + 8) / SuperMarioBros.PPM, (TiledMapTileMapObject) mapObject));
        }

        //create all goombas

//        for(MapObject object : tiledMap.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)){
//            Rectangle rect = ((RectangleMapObject) object).getRectangle();
//            goombas.add(new Goomba(screen, rect.getX() / SuperMarioBros.PPM, rect.getY() / SuperMarioBros.PPM));
//        }
        //create koopas

//        for(MapObject object : tiledMap.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)){
//            Rectangle rect = ((RectangleMapObject) object).getRectangle();
//            turtles.add(new Koopa(screen, rect.getX() / SuperMarioBros.PPM, rect.getY() / SuperMarioBros.PPM));
//        }
        mapLayer = tiledMap.getLayers().get("Goombas");
        if (mapLayer != null) {
            for (MapObject mapObject : mapLayer.getObjects()) {
                float x = ((TiledMapTileMapObject) mapObject).getX();
                float y = ((TiledMapTileMapObject) mapObject).getY();

                goombas.add(new Goomba(screen, (x + 8) / SuperMarioBros.PPM, (y + 8) / SuperMarioBros.PPM));
            }
        }

        mapLayer = tiledMap.getLayers().get("Koopas");
        if (mapLayer != null) {
            for (MapObject mapObject : mapLayer.getObjects()) {
                float x = ((TiledMapTileMapObject) mapObject).getX();
                float y = ((TiledMapTileMapObject) mapObject).getY();

                turtles.add(new Koopa(screen, (x + 8) / SuperMarioBros.PPM, (y + 8) / SuperMarioBros.PPM));
            }
        }


        startPosition = new Vector2(64.0f, 64.0f);

        mapLayer = tiledMap.getLayers().get("Start");
        if (mapLayer != null) {
            if (mapLayer.getObjects().getCount() > 0) {
                float x = ((TiledMapTileMapObject) mapLayer.getObjects().get(0)).getX();
                float y = ((TiledMapTileMapObject) mapLayer.getObjects().get(0)).getY();

                startPosition = new Vector2(x, y);
            }
        }

    }

    public Array<InteractiveTileObject> getMapTileObjects() {
        return mapTileObjects;
    }

    public Array<Enemy> getEnemies(){
        Array<Enemy> enemies = new Array<Enemy>();
        enemies.addAll(goombas);
        enemies.addAll(turtles);
        return enemies;
    }
    public Vector2 getStartPosition() {
        return startPosition;
    }

}
