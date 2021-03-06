package com.kalgarn.supermariobros.enemies;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import com.kalgarn.supermariobros.SuperMarioBros;
import com.kalgarn.supermariobros.screens.PlayScreen;
import com.kalgarn.supermariobros.sprites.Mario;

/**
 * Created by Jerome on 14/01/2016.
 */
public class Goomba extends Enemy {
    private float stateTime;
    private Animation walkAnimation;
    private Array<TextureRegion> frames;
    private boolean setToDestroy;
    private boolean destroyed;
    float angle;


    public Goomba(PlayScreen screen, float x, float y) {
        super(screen, x, y);
        frames = new Array<TextureRegion>();
        for(int i = 0; i < 2; i++)
            frames.add(new TextureRegion(screen.getAtlas().findRegion("goomba"), i * 16, 0, 16, 16));
        walkAnimation = new Animation(0.4f, frames);
        stateTime = 0;
        setBounds(getX() - 8 / SuperMarioBros.PPM, getY() - 8 / SuperMarioBros.PPM, 16 / SuperMarioBros.PPM, 16 / SuperMarioBros.PPM);
        setToDestroy = false;
        destroyed = false;
        angle = 0;
    }

    public void update(float dt){
        stateTime += dt;
        if(setToDestroy && !destroyed){
            world.destroyBody(b2body);
            destroyed = true;
            setRegion(new TextureRegion(playScreen.getAtlas().findRegion("goomba"), 32, 0, 16, 16));
            stateTime = 0;
        }
        else if(!destroyed) {
            b2body.setLinearVelocity(velocity);
            setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
            setRegion(walkAnimation.getKeyFrame(stateTime, true));
        }
        //  goomba walk
        if (playScreen.getPlayerPosition().x + SuperMarioBros.VIEWPORT_WIDTH /2 > b2body.getPosition().x){
            b2body.setActive(true);
        }
        
    }

    @Override
    protected void defineBody() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX(), getY());
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(6 / SuperMarioBros.PPM);
        fdef.filter.categoryBits = SuperMarioBros.ENEMY_BIT;
        fdef.filter.maskBits = SuperMarioBros.GROUND_BIT |
                SuperMarioBros.COIN_BIT |
                SuperMarioBros.BRICK_BIT |
                SuperMarioBros.ENEMY_BIT |
                SuperMarioBros.OBJECT_BIT |
                SuperMarioBros.MARIO_BIT ;

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);

        //Create the Head here:
        PolygonShape head = new PolygonShape();
        Vector2[] vertice = new Vector2[4]; // vertex is singular form
        vertice[0] = new Vector2(-7f, 7f).scl(1 / SuperMarioBros.PPM);
        vertice[1] = new Vector2(7f, 7f).scl(1 / SuperMarioBros.PPM);
        vertice[2] = new Vector2(-2f, -2f).scl(1 / SuperMarioBros.PPM);
        vertice[3] = new Vector2(2f, -2f).scl(1 / SuperMarioBros.PPM);
        head.set(vertice);

        fdef.shape = head;
        fdef.restitution = 0.5f;
        fdef.filter.categoryBits = SuperMarioBros.ENEMY_HEAD_BIT;
        b2body.createFixture(fdef).setUserData(this);

        shape.dispose();
        head.dispose();


    }

    public void draw(Batch batch){
        if(!destroyed || stateTime < 1)
            super.draw(batch);
    }



    @Override
    public void hitOnHead(Mario mario) {
        setToDestroy = true;
        SuperMarioBros.manager.get("audio/sounds/stomp.wav", Sound.class).play();
    }

    @Override
    public void hitByEnemy(Enemy enemy) {
        if(enemy instanceof Koopa && ((Koopa) enemy).currentState == Koopa.State.MOVING_SHELL)
            setToDestroy = true;
        else
            reverseVelocity(true, false);
    }
}
