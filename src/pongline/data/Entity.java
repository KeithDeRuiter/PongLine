/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pongline.data;

import pongline.data.matlib.Vector2f;
import pongline.display.Asset;



/**
 * Class representing a moveable entity in the game world.
 * @author Keith
 */
public abstract class Entity {
    /**
     * The position, represented using floats.
     */
    protected Vector2f position;

    /**
     * The velocity, represented using floats.  Not a unit vector.
     */
    protected Vector2f velocity;
    
    /**
     * The asset that should be used for this entity.
     */
    protected Asset asset;

    
    public Entity(Vector2f pos, Vector2f vel, Asset asset) {
        this.position = pos;
        this.velocity = vel;
        this.asset = asset;
    }
    
    
    public Vector2f getPosition() {
        return position;
    }

    public void setPosition(Vector2f position) {
        this.position = position;
    }

    public Vector2f getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2f velocity) {
        this.velocity = velocity;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }
    
    public void translate(Vector2f delta) {
        this.position.add(delta);
    }
}

