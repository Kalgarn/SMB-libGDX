package com.kalgarn.supermariobros.sprites.mapTileObjects;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.kalgarn.supermariobros.SuperMarioBros;
import com.kalgarn.supermariobros.screens.PlayScreen;
import com.kalgarn.supermariobros.sprites.Mario;
import com.kalgarn.supermariobros.sprites.RigidBody;

/**
 * Created by Jerome on 13/01/2016.
 */
public abstract class InteractiveTileObject extends RigidBody{
    //protected World world;
    protected TiledMap map;
    //protected Rectangle bounds;
    ///protected Body body;
    protected PlayScreen screen;
    //protected MapObject object;

    protected Fixture fixture;

    protected TiledMapTileMapObject mapObject;

    public InteractiveTileObject(PlayScreen screen, float x, float y, TiledMapTileMapObject mapObject){
        super(screen, x, y);
        this.mapObject = mapObject;
       // this.screen = screen;
      //  this.world = screen.getWorld();
       // this.map = screen.getMap();
        //this.bounds = ((RectangleMapObject) object).getRectangle();

        setRegion(mapObject.getTextureRegion());

        float width = 16 / SuperMarioBros.PPM;
        float height = 16 / SuperMarioBros.PPM;

        setBounds(x - width / 2, y - height / 2, width, height);

//        BodyDef bdef = new BodyDef();
//        FixtureDef fdef = new FixtureDef();
//        PolygonShape shape = new PolygonShape();
//
//        bdef.type = BodyDef.BodyType.StaticBody;
//        bdef.position.set((bounds.getX() + bounds.getWidth() / 2) / SuperMarioBros.PPM, (bounds.getY() + bounds.getHeight() / 2) / SuperMarioBros.PPM);
//
//        body = world.createBody(bdef);
//
//        shape.setAsBox(bounds.getWidth() / 2 / SuperMarioBros.PPM, bounds.getHeight() / 2 / SuperMarioBros.PPM);
//        fdef.shape = shape;
//        fixture = body.createFixture(fdef);

    }

 //   public abstract void onHeadHit(Mario mario);
    public void setCategoryFilter(short filterBit){
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
//        fixture.setFilterData(filter);
    }
//
    public TiledMapTileLayer.Cell getCell(){
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);
        return layer.getCell((int)(b2body.getPosition().x * SuperMarioBros.PPM / 16),
                (int)(b2body.getPosition().y * SuperMarioBros.PPM / 16));
    }
@Override
public void update(float delta) {

}
}
