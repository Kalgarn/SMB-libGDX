package com.kalgarn.supermariobros.items;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Jerome on 14/01/2016.
 */
public class ItemDef {
    public Vector2 position;
    public Class<?> type;

    public ItemDef(Vector2 position, Class<?> type){
        this.position = position;
        this.type = type;
    }
}
