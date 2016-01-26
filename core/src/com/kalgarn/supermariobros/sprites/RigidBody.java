package com.kalgarn.supermariobros.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.kalgarn.supermariobros.screens.PlayScreen;

/**
 * Created by Jerome on 25/01/2016.
 */
public abstract class RigidBody extends Sprite {
    protected PlayScreen playScreen;
    protected World world;
    protected Body b2body;

    protected boolean toBeDestroyed;
    protected boolean destroyed;

    public RigidBody(PlayScreen screen, float x, float y) {
        this.playScreen = screen;
        this.world = playScreen.world;

        toBeDestroyed = false;
        destroyed = false;

        setPosition(x, y);
        defineBody();
    }

    protected abstract void defineBody();
    public abstract void update(float dt);

    public void queueDestroy() {
        toBeDestroyed = true;
    }

    public boolean isDestroyed() {
        return destroyed;
    }
}
