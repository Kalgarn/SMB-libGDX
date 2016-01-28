package com.kalgarn.supermariobros.enemies;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.kalgarn.supermariobros.screens.PlayScreen;
import com.kalgarn.supermariobros.sprites.Mario;
import com.kalgarn.supermariobros.sprites.RigidBody;

/**
 * Created by Jerome on 14/01/2016.
 */
public abstract class Enemy extends RigidBody{
   // protected World world;
 //   protected PlayScreen screen;
  //  public Body b2body;
    public Vector2 velocity;

    public Enemy(PlayScreen screen, float x, float y){
        super(screen, x, y);
        this.world = screen.getWorld();
   //     this.screen = screen;
        setPosition(x, y);
   //     defineEnemy();
        defineBody();
        velocity = new Vector2(-1, -2);
        b2body.setActive(false);
    }

 //   protected abstract void defineEnemy();
    public abstract void update(float dt);
    public abstract void hitOnHead(Mario mario);
    public abstract void hitByEnemy(Enemy enemy);

    // walk
    public void reverseVelocity(boolean x, boolean y){
        if(x)
            velocity.x = -velocity.x;
        if(y)
            velocity.y = -velocity.y;
    }
}
