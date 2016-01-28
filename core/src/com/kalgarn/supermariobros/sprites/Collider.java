package com.kalgarn.supermariobros.sprites;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;

/**
 * Created by Jerome on 27/01/2016.
 */
public class Collider {

    Fixture fixture;

    public Collider(Fixture fixture) {
        this.fixture = fixture;
    }

    public RigidBody getUserData() {
        return (RigidBody) fixture.getUserData();
    }

    public Body getBody() {
        return fixture.getBody();
    }

    public Filter getFilter() {
        return fixture.getFilterData();
    }

    public Fixture getFixture() {
        return fixture;
    }
}
