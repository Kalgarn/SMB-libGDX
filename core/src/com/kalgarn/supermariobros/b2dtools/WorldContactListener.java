package com.kalgarn.supermariobros.b2dtools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.kalgarn.supermariobros.SuperMarioBros;
import com.kalgarn.supermariobros.enemies.Enemy;
import com.kalgarn.supermariobros.items.Item;
import com.kalgarn.supermariobros.others.FireBall;
import com.kalgarn.supermariobros.sprites.Mario;
import com.kalgarn.supermariobros.sprites.tileObjects.InteractiveTileObject;

/**
 * Created by Jerome on 13/01/2016.
 */
public class WorldContactListener implements ContactListener {
    @Override
    // use when 2 fixtures begin to connection/collide
    public void beginContact(Contact contact) {
        Gdx.app.log("begin contact","");
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        //if (fixA.getUserData() =="head" || fixB.getUserData() =="head")


        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

        switch (cDef){
            case SuperMarioBros.MARIO_HEAD_BIT | SuperMarioBros.BRICK_BIT:
            case SuperMarioBros.MARIO_HEAD_BIT | SuperMarioBros.COIN_BIT:
                if(fixA.getFilterData().categoryBits == SuperMarioBros.MARIO_HEAD_BIT)
                    ((InteractiveTileObject) fixB.getUserData()).onHeadHit((Mario) fixA.getUserData());
                else
                    ((InteractiveTileObject) fixA.getUserData()).onHeadHit((Mario) fixB.getUserData());
                break;
            case SuperMarioBros.ENEMY_HEAD_BIT | SuperMarioBros.MARIO_BIT:
                if(fixA.getFilterData().categoryBits == SuperMarioBros.ENEMY_HEAD_BIT)
                    ((Enemy)fixA.getUserData()).hitOnHead((Mario) fixB.getUserData());
                else
                    ((Enemy)fixB.getUserData()).hitOnHead((Mario) fixA.getUserData());
                break;
            case SuperMarioBros.ENEMY_BIT | SuperMarioBros.OBJECT_BIT:
                if(fixA.getFilterData().categoryBits == SuperMarioBros.ENEMY_BIT)
                    ((Enemy)fixA.getUserData()).reverseVelocity(true, false);
                else
                    ((Enemy)fixB.getUserData()).reverseVelocity(true, false);
                break;
            case SuperMarioBros.MARIO_BIT | SuperMarioBros.ENEMY_BIT:
                if(fixA.getFilterData().categoryBits == SuperMarioBros.MARIO_BIT)
                    ((Mario) fixA.getUserData()).hit((Enemy)fixB.getUserData());
                else
                    ((Mario) fixB.getUserData()).hit((Enemy)fixA.getUserData());
                break;
            case SuperMarioBros.ENEMY_BIT | SuperMarioBros.ENEMY_BIT:
                ((Enemy)fixA.getUserData()).hitByEnemy((Enemy)fixB.getUserData());
                ((Enemy)fixB.getUserData()).hitByEnemy((Enemy)fixA.getUserData());
                break;
            case SuperMarioBros.ITEM_BIT | SuperMarioBros.OBJECT_BIT:
                if(fixA.getFilterData().categoryBits == SuperMarioBros.ITEM_BIT)
                    ((Item)fixA.getUserData()).reverseVelocity(true, false);
                else
                    ((Item)fixB.getUserData()).reverseVelocity(true, false);
                break;
            case SuperMarioBros.ITEM_BIT | SuperMarioBros.MARIO_BIT:
                if(fixA.getFilterData().categoryBits == SuperMarioBros.ITEM_BIT)
                    ((Item)fixA.getUserData()).use((Mario) fixB.getUserData());
                else
                    ((Item)fixB.getUserData()).use((Mario) fixA.getUserData());
                break;
            case SuperMarioBros.FIREBALL_BIT | SuperMarioBros.OBJECT_BIT:
                if(fixA.getFilterData().categoryBits == SuperMarioBros.FIREBALL_BIT)
                    ((FireBall)fixA.getUserData()).setToDestroy();
                else
                    ((FireBall)fixB.getUserData()).setToDestroy();
                break;
        }
    }

    @Override
    public void endContact(Contact contact) {
        Gdx.app.log("end contact","");
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
       // Gdx.app.log("presolve","");
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
       // Gdx.app.log("postsolve","");
    }
}
